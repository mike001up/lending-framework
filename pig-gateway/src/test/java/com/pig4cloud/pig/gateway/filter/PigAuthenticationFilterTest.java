package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PigAuthenticationFilterTest {

	private RemoteUserService remoteUserService;
	private ObjectProvider<RemoteUserService> userServiceProvider;
	private PigAuthenticationFilter filter;

	@BeforeEach
	void setUp() {
		remoteUserService = mock(RemoteUserService.class);
		userServiceProvider = mock(ObjectProvider.class);
		when(userServiceProvider.getIfAvailable()).thenReturn(remoteUserService);
		filter = new PigAuthenticationFilter(userServiceProvider);
	}

	@Test
	void getOrder_returnsNegative1() {
		assertEquals(-1, filter.getOrder());
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
		verify(remoteUserService, never()).getUser(any());
	}

	@Test
	void filter_noAuthorizationHeader_returns401() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_basicAuth_validCredentials_extractsUsername() {
		String credentials = Base64.getEncoder().encodeToString("admin:password".getBytes(StandardCharsets.UTF_8));
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Basic " + credentials).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertEquals("admin", exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR));
		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_basicAuth_invalidBase64_returns401() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Basic !!invalid!!").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_basicAuth_noColonInCredentials_returns401() {
		String credentials = Base64.getEncoder().encodeToString("nocolonvalue".getBytes(StandardCharsets.UTF_8));
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Basic " + credentials).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_bearerToken_valid_setsUsernameAndTenant() {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("username", "testuser");
		userMap.put("tenantId", "42");
		when(remoteUserService.getUser("Bearer valid-token")).thenReturn(userMap);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer valid-token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertEquals("testuser", exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR));
		assertEquals("42", exchange.getAttribute(GatewayAttrConstants.GATEWAY_TENANT_ATTR));
	}

	@Test
	void filter_bearerToken_nullUser_returns403() {
		when(remoteUserService.getUser(any())).thenReturn(null);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer bad-token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);
		StepVerifier.create(result).verifyComplete();
		verify(chain, never()).filter(any());
	}

	@Test
	void filter_bearerToken_userWithoutUsername_returns403() {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("username", null);
		when(remoteUserService.getUser(any())).thenReturn(userMap);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);
		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_bearerToken_feignException401_returns403() {
		when(remoteUserService.getUser(any())).thenThrow(new RuntimeException("401 Unauthorized"));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer bad-token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);
		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_bearerToken_runtimeException_returns403() {
		when(remoteUserService.getUser(any())).thenThrow(new RuntimeException("Service down"));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);
		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_unsupportedTokenFormat_returns401() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Digest username=admin").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_bearerToken_remoteUserServiceUnavailable_returns401() {
		ObjectProvider<RemoteUserService> emptyProvider = mock(ObjectProvider.class);
		when(emptyProvider.getIfAvailable()).thenReturn(null);
		PigAuthenticationFilter filterNoService = new PigAuthenticationFilter(emptyProvider);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filterNoService.filter(exchange, chain).block();

		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_bearerToken_withTenant_setsTenantHeader() {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("username", "user1");
		userMap.put("tenantId", "99");
		when(remoteUserService.getUser(any())).thenReturn(userMap);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.header("Authorization", "Bearer token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> captured = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(inv -> {
			captured.set(inv.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutated = captured.get();
		assertNotNull(mutated);
		String tenantId = mutated.getRequest().getHeaders().getFirst("X-Tenant-Id");
		assertEquals("99", tenantId);
	}

}