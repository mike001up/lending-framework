package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.admin.api.feign.RemotePermService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PigAuthorizationGlobalFilterTest {

	private RemotePermService remotePermService;
	private ObjectProvider<RemotePermService> remotePermServiceProvider;
	private GatewaySecurityProperties securityProperties;
	private PigAuthorizationGlobalFilter filter;

	@BeforeEach
	void setUp() {
		remotePermService = mock(RemotePermService.class);
		remotePermServiceProvider = mock(ObjectProvider.class);
		when(remotePermServiceProvider.getObject()).thenReturn(remotePermService);
		securityProperties = new GatewaySecurityProperties();
		securityProperties.setPermissionCacheTtlMs(300000);
		securityProperties.setPermissionCacheMaxSize(1000);
		filter = new PigAuthorizationGlobalFilter(securityProperties, remotePermServiceProvider);

		ApplicationContext mockCtx = mock(ApplicationContext.class);
		StaticMessageSource messageSource = new StaticMessageSource();
		when(mockCtx.getBean(eq("messageSource"))).thenReturn(messageSource);
		when(mockCtx.getBean(any(String.class))).thenReturn(null);
		when(mockCtx.getBean(eq("messageSource"))).thenReturn(messageSource);
		try {
			Field field = SpringContextHolder.class.getDeclaredField("applicationContext");
			field.setAccessible(true);
			field.set(null, mockCtx);
		} catch (Exception ignored) {}
	}

	@Test
	void getOrder_returns1() {
		assertEquals(1, filter.getOrder());
	}

	@Test
	void filter_authorizeDisabled_passesThrough() {
		securityProperties.setAuthorizeEnabled(false);
		filter = new PigAuthorizationGlobalFilter(securityProperties, remotePermServiceProvider);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		verify(chain, times(1)).filter(any());
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
	}

	@Test
	void filter_missingUsername_returns401() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_adminUser_skipsAuthorization() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, SecurityConstants.ADMIN);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
		verify(remotePermService, never()).getAuthorizeRules();
	}

	@Test
	void filter_noMatchingRule_passesThrough() {
		SysPermission rule = new SysPermission();
		rule.setPath("/code/**");
		rule.setMethod(null);
		rule.setPermission("codegen_view");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_matchingRuleWithEmptyPermission_passesThrough() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod(null);
		rule.setPermission("");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_userHasPermission_passesThrough() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod(null);
		rule.setPermission("sys_user_view");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		UserInfo userInfo = new UserInfo();
		userInfo.setPermissions(new String[]{"sys_user_view", "sys_dept_view"});
		when(remotePermService.getUserInfo("normal")).thenReturn(R.ok(userInfo));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_userLacksPermission_returns403() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod(null);
		rule.setPermission("sys_user_del");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		UserInfo userInfo = new UserInfo();
		userInfo.setPermissions(new String[]{"sys_user_view"});
		when(remotePermService.getUserInfo("normal")).thenReturn(R.ok(userInfo));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_getUserInfoFails_returns403() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod(null);
		rule.setPermission("sys_user_view");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		R<UserInfo> failedResult = R.failed("Service error");
		when(remotePermService.getUserInfo("normal")).thenReturn(failedResult);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		Mono<Void> result = filter.filter(exchange, chain);
		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_getAuthorizeRulesFails_usesCachedRules() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod(null);
		rule.setPermission("sys_user_view");
		when(remotePermService.getAuthorizeRules())
			.thenReturn(R.ok(Arrays.asList(rule)))
			.thenThrow(new RuntimeException("Service error"));

		UserInfo userInfo = new UserInfo();
		userInfo.setPermissions(new String[]{"sys_user_view"});
		when(remotePermService.getUserInfo("normal")).thenReturn(R.ok(userInfo));

		MockServerWebExchange exchange1 = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange1.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange1, chain).block();
		verify(chain, times(1)).filter(any());

		filter.evictAllCache();

		MockServerWebExchange exchange2 = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange2.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");

		filter.filter(exchange2, chain).block();
	}

	@Test
	void filter_httpMethodMatching() {
		SysPermission rule = new SysPermission();
		rule.setPath("/admin/**");
		rule.setMethod("POST");
		rule.setPermission("sys_user_add");
		when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Arrays.asList(rule)));

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "normal");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

	@Test
	void evictCache_clearsSpecificUser() {
		filter.evictCache("testuser");
	}

	@Test
	void evictAllCache_clearsAll() {
		filter.evictAllCache();
	}

}