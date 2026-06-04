package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.admin.api.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.gateway.fegin.RemoteIPLimitService;
import com.pig4cloud.pig.gateway.fegin.RemotePermService;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

		ObjectProvider<RemoteUserService> userServiceProvider = mock(ObjectProvider.class);
		RemoteUserService remoteUserService = mock(RemoteUserService.class);
		when(userServiceProvider.getIfAvailable()).thenReturn(remoteUserService);

		ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
		RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
		when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);
		when(remoteIPLimitService.isValidIP(any())).thenReturn(true);

		ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
		RemotePermService remotePermService = mock(RemotePermService.class);
		when(permServiceProvider.getObject()).thenReturn(remotePermService);
		when(remotePermService.getAuthorizeRules()).thenReturn(com.pig4cloud.pig.common.core.util.R.ok(List.of()));

		ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
		when(logServiceProvider.getIfAvailable()).thenReturn(null);

		RequestCleanGlobalFilter requestCleanFilter = new RequestCleanGlobalFilter();
		WhitelistGlobalFilter whitelistFilter = new WhitelistGlobalFilter(securityProperties);
		PigAuthenticationFilter authFilter = new PigAuthenticationFilter(userServiceProvider);
		IpLimitGlobalFilter ipLimitFilter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		PigAuthorizationGlobalFilter authorizationFilter = new PigAuthorizationGlobalFilter(securityProperties,
				permServiceProvider);
		GatewayAuditLogFilter auditLogFilter = new GatewayAuditLogFilter(logServiceProvider);
		PigRequestGlobalFilter requestFilter = new PigRequestGlobalFilter();

		List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
				authorizationFilter, auditLogFilter, requestFilter);
		List<GlobalFilter> sortedFilters = filters.stream()
			.sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
			.toList();

		assertEquals(7, sortedFilters.size());
		assertEquals(-3, ((Ordered) sortedFilters.get(0)).getOrder());
		assertEquals(-2, ((Ordered) sortedFilters.get(1)).getOrder());
		assertEquals(-1, ((Ordered) sortedFilters.get(2)).getOrder());
		assertEquals(0, ((Ordered) sortedFilters.get(3)).getOrder());
		assertEquals(1, ((Ordered) sortedFilters.get(4)).getOrder());
		assertEquals(5, ((Ordered) sortedFilters.get(5)).getOrder());
		assertEquals(10, ((Ordered) sortedFilters.get(6)).getOrder());

		assertInstanceOf(RequestCleanGlobalFilter.class, sortedFilters.get(0));
		assertInstanceOf(WhitelistGlobalFilter.class, sortedFilters.get(1));
		assertInstanceOf(PigAuthenticationFilter.class, sortedFilters.get(2));
		assertInstanceOf(IpLimitGlobalFilter.class, sortedFilters.get(3));
		assertInstanceOf(PigAuthorizationGlobalFilter.class, sortedFilters.get(4));
		assertInstanceOf(GatewayAuditLogFilter.class, sortedFilters.get(5));
		assertInstanceOf(PigRequestGlobalFilter.class, sortedFilters.get(6));

		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/list")
			.header(CommonConstants.CLIENT, "web")
			.header("Authorization", "Bearer valid-token")
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		java.util.Map<String, Object> userMap = new java.util.HashMap<>();
		userMap.put("username", "admin");
		userMap.put("userId", "1");
		when(remoteUserService.getUser(any())).thenReturn(userMap);

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

		ObjectProvider<RemoteUserService> userServiceProvider = mock(ObjectProvider.class);
		RemoteUserService remoteUserService = mock(RemoteUserService.class);
		when(userServiceProvider.getIfAvailable()).thenReturn(remoteUserService);

		ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
		RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
		when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);

		ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
		RemotePermService remotePermService = mock(RemotePermService.class);
		when(permServiceProvider.getObject()).thenReturn(remotePermService);

		ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
		when(logServiceProvider.getIfAvailable()).thenReturn(null);

		RequestCleanGlobalFilter requestCleanFilter = new RequestCleanGlobalFilter();
		WhitelistGlobalFilter whitelistFilter = new WhitelistGlobalFilter(securityProperties);
		PigAuthenticationFilter authFilter = new PigAuthenticationFilter(userServiceProvider);
		IpLimitGlobalFilter ipLimitFilter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		PigAuthorizationGlobalFilter authorizationFilter = new PigAuthorizationGlobalFilter(securityProperties,
				permServiceProvider);
		GatewayAuditLogFilter auditLogFilter = new GatewayAuditLogFilter(logServiceProvider);
		PigRequestGlobalFilter requestFilter = new PigRequestGlobalFilter();

		List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
				authorizationFilter, auditLogFilter, requestFilter);
		List<GlobalFilter> sortedFilters = filters.stream()
			.sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
			.toList();

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
		assertFalse(actualExecution.contains("WhitelistGlobalFilter"),
				"WhitelistGlobalFilter 不应执行");
		assertFalse(actualExecution.contains("PigAuthenticationFilter"),
				"PigAuthenticationFilter 不应执行");
		assertFalse(actualExecution.contains("IpLimitGlobalFilter"),
				"IpLimitGlobalFilter 不应执行");
		assertFalse(actualExecution.contains("PigAuthorizationGlobalFilter"),
				"PigAuthorizationGlobalFilter 不应执行");
		assertFalse(actualExecution.contains("GatewayAuditLogFilter"),
				"GatewayAuditLogFilter 不应执行");
		assertFalse(actualExecution.contains("PigRequestGlobalFilter"),
				"PigRequestGlobalFilter 不应执行");
		assertFalse(actualExecution.contains("terminal"),
				"终端 chain.filter 不应被调用");
		assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR),
				"gateway_whitelist 属性不应被设置");
		assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_CLIENT_ATTR),
				"gateway_client 属性不应被设置");
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

		ObjectProvider<RemoteUserService> userServiceProvider = mock(ObjectProvider.class);
		RemoteUserService remoteUserService = mock(RemoteUserService.class);
		when(userServiceProvider.getIfAvailable()).thenReturn(remoteUserService);

		ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
		RemoteIPLimitService remoteIPLimitService = mock(RemoteIPLimitService.class);
		when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);
		when(remoteIPLimitService.isValidIP(any())).thenReturn(true);

		ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
		RemotePermService remotePermService = mock(RemotePermService.class);
		when(permServiceProvider.getObject()).thenReturn(remotePermService);

		ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);
		when(logServiceProvider.getIfAvailable()).thenReturn(null);

		RequestCleanGlobalFilter requestCleanFilter = new RequestCleanGlobalFilter();
		WhitelistGlobalFilter whitelistFilter = new WhitelistGlobalFilter(securityProperties);
		PigAuthenticationFilter authFilter = new PigAuthenticationFilter(userServiceProvider);
		IpLimitGlobalFilter ipLimitFilter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		PigAuthorizationGlobalFilter authorizationFilter = new PigAuthorizationGlobalFilter(securityProperties,
				permServiceProvider);
		GatewayAuditLogFilter auditLogFilter = new GatewayAuditLogFilter(logServiceProvider);
		PigRequestGlobalFilter requestFilter = new PigRequestGlobalFilter();

		List<GlobalFilter> filters = List.of(requestCleanFilter, whitelistFilter, authFilter, ipLimitFilter,
				authorizationFilter, auditLogFilter, requestFilter);
		List<GlobalFilter> sortedFilters = filters.stream()
			.sorted(Comparator.comparingInt(f -> ((Ordered) f).getOrder()))
			.toList();

		MockServerHttpRequest request = MockServerHttpRequest.get("/auth/token/check_token")
			.header(CommonConstants.CLIENT, "web")
			.remoteAddress(new java.net.InetSocketAddress("127.0.0.1", 8080))
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
				"WhitelistGlobalFilter 应标记 GATEWAY_WHITELIST_ATTR");
		assertTrue(actualExecution.contains("WhitelistGlobalFilter"),
				"WhitelistGlobalFilter 应执行");
		assertTrue(actualExecution.contains("PigAuthenticationFilter"),
				"PigAuthenticationFilter 应执行（但跳过认证逻辑）");
		assertTrue(actualExecution.contains("PigAuthorizationGlobalFilter"),
				"PigAuthorizationGlobalFilter 应执行（但跳过鉴权逻辑）");
		assertTrue(actualExecution.contains("terminal"),
				"请求应成功到达终端 chain");

		verify(remoteUserService, never()).getUser(any());
		verify(remotePermService, never()).getAuthorizeRules();
		verify(remotePermService, never()).getUserInfo(any());

		assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR),
				"白名单请求不应设置 GATEWAY_USERNAME_ATTR");
		assertNull(exchange.getResponse().getStatusCode(),
				"白名单请求响应状态码应为 null（无错误）");
	}

	@Test
	void eachFilterOrderIsDistinctAndCorrect() {
		GatewaySecurityProperties securityProperties = new GatewaySecurityProperties();

		ObjectProvider<RemoteUserService> userServiceProvider = mock(ObjectProvider.class);
		ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider = mock(ObjectProvider.class);
		ObjectProvider<RemotePermService> permServiceProvider = mock(ObjectProvider.class);
		ObjectProvider<RemoteLogService> logServiceProvider = mock(ObjectProvider.class);

		RequestCleanGlobalFilter f1 = new RequestCleanGlobalFilter();
		WhitelistGlobalFilter f2 = new WhitelistGlobalFilter(securityProperties);
		PigAuthenticationFilter f3 = new PigAuthenticationFilter(userServiceProvider);
		IpLimitGlobalFilter f4 = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		PigAuthorizationGlobalFilter f5 = new PigAuthorizationGlobalFilter(securityProperties, permServiceProvider);
		GatewayAuditLogFilter f6 = new GatewayAuditLogFilter(logServiceProvider);
		PigRequestGlobalFilter f7 = new PigRequestGlobalFilter();

		int[] orders = { f1.getOrder(), f2.getOrder(), f3.getOrder(), f4.getOrder(), f5.getOrder(), f6.getOrder(),
				f7.getOrder() };
		int[] expected = { -3, -2, -1, 0, 1, 5, 10 };

		assertArrayEquals(expected, orders, "Filter 执行顺序应为: RequestClean(-3) → Whitelist(-2) → Authentication(-1) → IpLimit(0) → Authorization(1) → AuditLog(5) → Request(10)");
	}

}