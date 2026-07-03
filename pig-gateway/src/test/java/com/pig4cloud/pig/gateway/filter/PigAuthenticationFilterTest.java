package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PigAuthenticationFilterTest {

    private RemoteTokenService remoteTokenService;
    private ObjectProvider<RemoteTokenService> tokenServiceProvider;
    private PigAuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        remoteTokenService = mock(RemoteTokenService.class);
        tokenServiceProvider = mock(ObjectProvider.class);
        when(tokenServiceProvider.getIfAvailable()).thenReturn(remoteTokenService);
        filter = new PigAuthenticationFilter(tokenServiceProvider);
    }

    @Test
    void getOrder_returnsAuthenticationOrder() {
        assertEquals(GatewayAttrConstants.GATEWAY_ORDER_FILTER_AUTHENTICATION, filter.getOrder());
    }

    @Test
    void filter_whitelistRequest_passesThrough() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/auth/token/check_token").build());
        exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR, Boolean.TRUE);
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(any());
        verify(remoteTokenService, never()).parsingToken(any());
    }

    @Test
    void filter_noAuthorizationHeader_returns401() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        Mono<Void> result = filter.filter(exchange, chain);
        StepVerifier.create(result).verifyComplete();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_basicAuth_returns401() {
        String credentials = Base64.getEncoder().encodeToString("admin:password".getBytes(StandardCharsets.UTF_8));
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Basic " + credentials).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_validUserToken_setsHeadersAndProceeds() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder()
                .username("testuser")
                .userId(123L)
                .tenantId(42L)
                .clientId("test-client")
                .grantType(GrantTypeEnum.PASSWORD)
                .build();
        R<TokenPayloadDTO> successResponse = R.ok(payload);

        when(remoteTokenService.parsingToken(any())).thenReturn(successResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer valid-token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        AtomicReference<ServerWebExchange> capturedExchange = new AtomicReference<>();
        when(chain.filter(any())).thenAnswer(inv -> {
            capturedExchange.set(inv.getArgument(0));
            return Mono.empty();
        });

        filter.filter(exchange, chain).block();

        ServerWebExchange mutatedExchange = capturedExchange.get();
        assertNotNull(mutatedExchange);
        // 验证请求头
        var headers = mutatedExchange.getRequest().getHeaders();
        assertEquals("testuser", headers.getFirst(SecurityConstants.HEADER_PRINCIPAL));
        assertEquals("123", headers.getFirst(SecurityConstants.HEADER_USER_ID));
        assertEquals("testuser", headers.getFirst(SecurityConstants.HEADER_USERNAME));
        assertEquals("42", headers.getFirst(SecurityConstants.HEADER_TENANT_ID));
        assertEquals("PASSWORD", headers.getFirst(SecurityConstants.HEADER_GRANT_TYPE));
        assertEquals("test-client", headers.getFirst(SecurityConstants.HEADER_CLIENT_ID));
        // Authorization 头应被移除
        assertNull(headers.getFirst(SecurityConstants.AUTHORIZATION));

        verify(chain, times(1)).filter(any());
    }

    @Test
    void filter_bearerToken_validClientToken_setsHeadersAndProceeds() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder()
                .clientId("my-client")
                .grantType(GrantTypeEnum.CLIENT_CREDENTIALS)
                .build();
        R<TokenPayloadDTO> successResponse = R.ok(payload);

        when(remoteTokenService.parsingToken(any())).thenReturn(successResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer valid-token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        AtomicReference<ServerWebExchange> capturedExchange = new AtomicReference<>();
        when(chain.filter(any())).thenAnswer(inv -> {
            capturedExchange.set(inv.getArgument(0));
            return Mono.empty();
        });

        filter.filter(exchange, chain).block();

        ServerWebExchange mutatedExchange = capturedExchange.get();
        assertNotNull(mutatedExchange);
        var headers = mutatedExchange.getRequest().getHeaders();
        assertEquals("my-client", headers.getFirst(SecurityConstants.HEADER_PRINCIPAL));
        assertNull(headers.getFirst(SecurityConstants.HEADER_USER_ID));
        assertNull(headers.getFirst(SecurityConstants.HEADER_USERNAME));
        assertNull(headers.getFirst(SecurityConstants.HEADER_TENANT_ID));
        assertEquals("CLIENT_CREDENTIALS", headers.getFirst(SecurityConstants.HEADER_GRANT_TYPE));
        assertEquals("my-client", headers.getFirst(SecurityConstants.HEADER_CLIENT_ID));
        assertNull(headers.getFirst(SecurityConstants.AUTHORIZATION));
    }

    @Test
    void filter_bearerToken_nullResponse_returns401() {
        when(remoteTokenService.parsingToken(any())).thenReturn(null);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_responseNotSuccess_returns401() {
        R<TokenPayloadDTO> errorResponse = R.failed("Token invalid");
        when(remoteTokenService.parsingToken(any())).thenReturn(errorResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_dataNull_returns401() {
        R<TokenPayloadDTO> response = R.ok(null);
        when(remoteTokenService.parsingToken(any())).thenReturn(response);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_missingGrantType_returns401() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder().build(); // grantType null
        R<TokenPayloadDTO> response = R.ok(payload);
        when(remoteTokenService.parsingToken(any())).thenReturn(response);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_userTokenMissingUsernameAndUserId_returns401() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder()
                .grantType(GrantTypeEnum.PASSWORD)
                .build(); // no username or userId
        R<TokenPayloadDTO> response = R.ok(payload);
        when(remoteTokenService.parsingToken(any())).thenReturn(response);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_clientTokenMissingClientId_returns401() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder()
                .grantType(GrantTypeEnum.CLIENT_CREDENTIALS)
                .build(); // no clientId
        R<TokenPayloadDTO> response = R.ok(payload);
        when(remoteTokenService.parsingToken(any())).thenReturn(response);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_unsupportedGrantType_returns401() {
        TokenPayloadDTO payload = TokenPayloadDTO.builder()
                .grantType(GrantTypeEnum.PASSWORD) // 假设存在，但不存在则用 null，但测试需要模拟一个未知值
                .build();
        // 为了测试，我们可以不设置 grantType，或者使用一个非 PASSWORD/CLIENT_CREDENTIALS 的值，但枚举只有这两个，所以可以 mock 一个 null
        // 测试不支持的类型，我们直接使用 null
        payload.setGrantType(null);
        R<TokenPayloadDTO> response = R.ok(payload);
        when(remoteTokenService.parsingToken(any())).thenReturn(response);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_feignException401_returns401() {
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(401);
        when(remoteTokenService.parsingToken(any())).thenThrow(feignException);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_feignException403_returns403() {
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(403);
        when(remoteTokenService.parsingToken(any())).thenThrow(feignException);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_otherException_returns500() {
        when(remoteTokenService.parsingToken(any())).thenThrow(new RuntimeException("Unexpected error"));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_bearerToken_remoteServiceUnavailable_returns503() {
        ObjectProvider<RemoteTokenService> emptyProvider = mock(ObjectProvider.class);
        when(emptyProvider.getIfAvailable()).thenReturn(null);
        PigAuthenticationFilter filterNoService = new PigAuthenticationFilter(emptyProvider);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Bearer token").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filterNoService.filter(exchange, chain).block();

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_unsupportedAuthScheme_returns401() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header("Authorization", "Digest realm=test").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }
}