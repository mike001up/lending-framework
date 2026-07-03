package com.pig4cloud.pig.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.admin.api.feign.RemoteIPLimitService;
import com.pig4cloud.pig.admin.api.feign.RemotePermService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FilterChainOrderTest {

    private List<String> executionOrder;

    @BeforeEach
    void setUp() {
        executionOrder = new ArrayList<>();
    }

    @Test
    void tc_chain_001_sevenFiltersExecuteInCorrectOrder() {
        GatewaySecurityProperties securityProperties = new GatewaySecurityProperties();

        // ---------- Authentication Filter dependencies ----------
        ObjectProvider<RemoteTokenService> tokenServiceProvider = mock(ObjectProvider.class);
        RemoteTokenService remoteTokenService = mock(RemoteTokenService.class);
        when(tokenServiceProvider.getIfAvailable()).thenReturn(remoteTokenService);

        // ---------- IP Limit Filter dependencies ----------
        ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
        RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
        when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);
        when(remoteIPLimitService.check(any(), any())).thenReturn(R.ok(true));

        // ---------- Authorization Filter dependencies ----------
        ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
        RemotePermService remotePermService = mock(RemotePermService.class);
        when(permServiceProvider.getIfAvailable()).thenReturn(remotePermService);
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(List.of()));

        RedisTemplate<String, Object> redisTemplate = mock(RedisTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();

        // ---------- Audit Log Filter dependencies ----------
        ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
        when(logServiceProvider.getIfAvailable()).thenReturn(null);

        // ---------- Instantiate all filters ----------
        PigRequestCleanFilter requestCleanFilter = new PigRequestCleanFilter();
        PigWhitelistFilter whitelistFilter = new PigWhitelistFilter(securityProperties);
        PigAuthenticationFilter authFilter = new PigAuthenticationFilter(tokenServiceProvider);
        PigIpLimitFilter ipLimitFilter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        PigAuthorizationFilter authorizationFilter = new PigAuthorizationFilter(securityProperties,
                permServiceProvider, redisTemplate, objectMapper);
        PigAuditLogFilter auditLogFilter = new PigAuditLogFilter(logServiceProvider);
        PigPathStripFilter pathStripFilter = new PigPathStripFilter();

        // ---------- Sort by order ----------
        List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
                authorizationFilter, auditLogFilter, pathStripFilter);
        List<GlobalFilter> sortedFilters = filters.stream()
                .sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
                .toList();

        // ---------- Verify order values ----------
        assertEquals(7, sortedFilters.size());
        assertEquals(-3, ((Ordered) sortedFilters.get(0)).getOrder());
        assertEquals(-2, ((Ordered) sortedFilters.get(1)).getOrder());
        assertEquals(-1, ((Ordered) sortedFilters.get(2)).getOrder());
        assertEquals(0, ((Ordered) sortedFilters.get(3)).getOrder());
        assertEquals(1, ((Ordered) sortedFilters.get(4)).getOrder());
        assertEquals(5, ((Ordered) sortedFilters.get(5)).getOrder());
        assertEquals(10, ((Ordered) sortedFilters.get(6)).getOrder());

        // ---------- Verify types ----------
        assertInstanceOf(PigRequestCleanFilter.class, sortedFilters.get(0));
        assertInstanceOf(PigWhitelistFilter.class, sortedFilters.get(1));
        assertInstanceOf(PigAuthenticationFilter.class, sortedFilters.get(2));
        assertInstanceOf(PigIpLimitFilter.class, sortedFilters.get(3));
        assertInstanceOf(PigAuthorizationFilter.class, sortedFilters.get(4));
        assertInstanceOf(PigAuditLogFilter.class, sortedFilters.get(5));
        assertInstanceOf(PigPathStripFilter.class, sortedFilters.get(6));

        // ---------- Test execution chain ----------
        MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/list")
                .header(CommonConstants.GATEWAY_CLIENT_ATTR, "web")
                .header("Authorization", "Bearer valid-token")
                .build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);

        TokenPayloadDTO tokenPayload = TokenPayloadDTO.builder()
                .username("testuser")
                .userId(1L)
                .grantType(com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum.PASSWORD)
                .clientId("web")
                .build();
        when(remoteTokenService.parsingToken(any())).thenReturn(R.ok(tokenPayload));

        AtomicReference<ServerWebExchange> finalExchange = new AtomicReference<>();
        GatewayFilterChain terminalChain = mock(GatewayFilterChain.class);
        when(terminalChain.filter(any())).thenAnswer(inv -> {
            finalExchange.set(inv.getArgument(0));
            return Mono.empty();
        });

        GatewayFilterChain chain = buildChain(sortedFilters, terminalChain);

        sortedFilters.get(0).filter(exchange, chain).block();

        assertNotNull(finalExchange.get());
    }

    private GatewayFilterChain buildChain(List<GlobalFilter> filters, GatewayFilterChain terminalChain) {
        GatewayFilterChain current = terminalChain;
        for (int i = filters.size() - 1; i >= 1; i--) {
            final GlobalFilter filter = filters.get(i);
            final GatewayFilterChain next = current;
            current = mock(GatewayFilterChain.class);
            when(current.filter(any())).thenAnswer(inv -> {
                ServerWebExchange ex = inv.getArgument(0);
                return filter.filter(ex, next);
            });
        }
        return current;
    }

    @Test
    void tc_chain_002_requestCleanRejects_whitelistAndSubsequentFiltersNotExecuted() {
        GatewaySecurityProperties securityProperties = new GatewaySecurityProperties();

        ObjectProvider<RemoteTokenService> tokenServiceProvider = mock(ObjectProvider.class);
        RemoteTokenService remoteTokenService = mock(RemoteTokenService.class);
        when(tokenServiceProvider.getIfAvailable()).thenReturn(remoteTokenService);

        ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
        RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
        when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);

        ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
        RemotePermService remotePermService = mock(RemotePermService.class);
        when(permServiceProvider.getIfAvailable()).thenReturn(remotePermService);

        RedisTemplate<String, Object> redisTemplate = mock(RedisTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
        when(logServiceProvider.getIfAvailable()).thenReturn(null);

        PigRequestCleanFilter requestCleanFilter = new PigRequestCleanFilter();
        PigWhitelistFilter whitelistFilter = new PigWhitelistFilter(securityProperties);
        PigAuthenticationFilter authFilter = new PigAuthenticationFilter(tokenServiceProvider);
        PigIpLimitFilter ipLimitFilter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        PigAuthorizationFilter authorizationFilter = new PigAuthorizationFilter(securityProperties,
                permServiceProvider, redisTemplate, objectMapper);
        PigAuditLogFilter auditLogFilter = new PigAuditLogFilter(logServiceProvider);
        PigPathStripFilter pathStripFilter = new PigPathStripFilter();

        List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
                authorizationFilter, auditLogFilter, pathStripFilter);
        List<GlobalFilter> sortedFilters = filters.stream()
                .sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
                .toList();

        // Request without Authorization header → RequestCleanFilter should reject (401)
        MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info").build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);

        List<String> actualExecution = new ArrayList<>();

        GatewayFilterChain terminalChain = mock(GatewayFilterChain.class);
        when(terminalChain.filter(any())).thenAnswer(inv -> {
            actualExecution.add("terminal");
            return Mono.empty();
        });

        GatewayFilterChain chain = buildChainWithTracking(sortedFilters, terminalChain, actualExecution);

        Mono<Void> result = sortedFilters.get(0).filter(exchange, chain);
        StepVerifier.create(result).verifyComplete();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        assertFalse(actualExecution.contains("PigWhitelistFilter"),
                "PigWhitelistFilter 不应执行");
        assertFalse(actualExecution.contains("PigAuthenticationFilter"),
                "PigAuthenticationFilter 不应执行");
        assertFalse(actualExecution.contains("PigIpLimitFilter"),
                "PigIpLimitFilter 不应执行");
        assertFalse(actualExecution.contains("PigAuthorizationFilter"),
                "PigAuthorizationFilter 不应执行");
        assertFalse(actualExecution.contains("PigAuditLogFilter"),
                "PigAuditLogFilter 不应执行");
        assertFalse(actualExecution.contains("PigPathStripFilter"),
                "PigPathStripFilter 不应执行");
        assertFalse(actualExecution.contains("terminal"),
                "终端 chain.filter 不应被调用");
        assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR),
                "gateway_whitelist 属性不应被设置");
        assertNull(exchange.getAttribute(SecurityConstants.HEADER_USERNAME),
                "gateway_username 属性不应被设置");
    }

    private GatewayFilterChain buildChainWithTracking(List<GlobalFilter> filters, GatewayFilterChain terminalChain,
                                                      List<String> executionTracker) {
        GatewayFilterChain current = terminalChain;
        for (int i = filters.size() - 1; i >= 1; i--) {
            final GlobalFilter filter = filters.get(i);
            final GatewayFilterChain next = current;
            current = mock(GatewayFilterChain.class);
            when(current.filter(any())).thenAnswer(inv -> {
                executionTracker.add(filter.getClass().getSimpleName());
                ServerWebExchange ex = inv.getArgument(0);
                return filter.filter(ex, next);
            });
        }
        return current;
    }

    @Test
    void tc_chain_003_whitelistRequest_skipsAuthenticationAndAuthorization() {
        GatewaySecurityProperties securityProperties = new GatewaySecurityProperties();

        ObjectProvider<RemoteTokenService> tokenServiceProvider = mock(ObjectProvider.class);
        RemoteTokenService remoteTokenService = mock(RemoteTokenService.class);
        when(tokenServiceProvider.getIfAvailable()).thenReturn(remoteTokenService);

        ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
        RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
        when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);
        when(remoteIPLimitService.check(any(), any())).thenReturn(R.ok(true));

        ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
        RemotePermService remotePermService = mock(RemotePermService.class);
        when(permServiceProvider.getIfAvailable()).thenReturn(remotePermService);

        RedisTemplate<String, Object> redisTemplate = mock(RedisTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
        when(logServiceProvider.getIfAvailable()).thenReturn(null);

        PigRequestCleanFilter requestCleanFilter = new PigRequestCleanFilter();
        PigWhitelistFilter whitelistFilter = new PigWhitelistFilter(securityProperties);
        PigAuthenticationFilter authFilter = new PigAuthenticationFilter(tokenServiceProvider);
        PigIpLimitFilter ipLimitFilter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        PigAuthorizationFilter authorizationFilter = new PigAuthorizationFilter(securityProperties,
                permServiceProvider, redisTemplate, objectMapper);
        PigAuditLogFilter auditLogFilter = new PigAuditLogFilter(logServiceProvider);
        PigPathStripFilter pathStripFilter = new PigPathStripFilter();

        List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
                authorizationFilter, auditLogFilter, pathStripFilter);
        List<GlobalFilter> sortedFilters = filters.stream()
                .sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
                .toList();

        // Whitelist URL: /auth/token/check_token (should skip authentication)
        MockServerHttpRequest request = MockServerHttpRequest.get("/auth/token/check_token")
                .header(CommonConstants.GATEWAY_CLIENT_ATTR, "web")
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                .build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);

        List<String> actualExecution = new ArrayList<>();

        GatewayFilterChain terminalChain = mock(GatewayFilterChain.class);
        when(terminalChain.filter(any())).thenAnswer(inv -> {
            actualExecution.add("terminal");
            return Mono.empty();
        });

        GatewayFilterChain chain = buildChainWithTracking(sortedFilters, terminalChain, actualExecution);

        Mono<Void> result = sortedFilters.get(0).filter(exchange, chain);
        StepVerifier.create(result).verifyComplete();

        assertEquals(Boolean.TRUE, exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR),
                "PigWhitelistFilter 应标记 GATEWAY_WHITELIST_ATTR");
        assertTrue(actualExecution.contains("PigWhitelistFilter"),
                "PigWhitelistFilter 应执行");
        assertTrue(actualExecution.contains("PigAuthenticationFilter"),
                "PigAuthenticationFilter 应执行（但跳过认证逻辑）");
        assertTrue(actualExecution.contains("PigAuthorizationFilter"),
                "PigAuthorizationFilter 应执行（但跳过鉴权逻辑）");
        assertTrue(actualExecution.contains("terminal"),
                "请求应成功到达终端 chain");

        verify(remoteTokenService, never()).parsingToken(any());
        verify(remotePermService, never()).getAuthorizeRules();
        verify(remotePermService, never()).getUserInfo(any());
        verify(remoteIPLimitService, never()).check(any(), any());

        assertNull(exchange.getAttribute(SecurityConstants.HEADER_USERNAME),
                "白名单请求不应设置 GATEWAY_USERNAME_ATTR");
        assertNull(exchange.getResponse().getStatusCode(),
                "白名单请求响应状态码应为 null（无错误）");
    }

    @Test
    void eachFilterOrderIsDistinctAndCorrect() {
        GatewaySecurityProperties securityProperties = new GatewaySecurityProperties();

        ObjectProvider<RemoteTokenService> tokenServiceProvider = mock(ObjectProvider.class);
        ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
        ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
        RedisTemplate<String, Object> redisTemplate = mock(RedisTemplate.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);

        PigRequestCleanFilter f1 = new PigRequestCleanFilter();
        PigWhitelistFilter f2 = new PigWhitelistFilter(securityProperties);
        PigAuthenticationFilter f3 = new PigAuthenticationFilter(tokenServiceProvider);
        PigIpLimitFilter f4 = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        PigAuthorizationFilter f5 = new PigAuthorizationFilter(securityProperties, permServiceProvider, redisTemplate, objectMapper);
        PigAuditLogFilter f6 = new PigAuditLogFilter(logServiceProvider);
        PigPathStripFilter f7 = new PigPathStripFilter();

        int[] orders = { f1.getOrder(), f2.getOrder(), f3.getOrder(), f4.getOrder(), f5.getOrder(), f6.getOrder(), f7.getOrder() };
        int[] expected = { -3, -2, -1, 0, 1, 5, 10 };

        assertArrayEquals(expected, orders,
                "Filter 执行顺序应为: RequestClean(-3) → Whitelist(-2) → Authentication(-1) → IpLimit(0) → Authorization(1) → AuditLog(5) → PathStrip(10)");
    }
}