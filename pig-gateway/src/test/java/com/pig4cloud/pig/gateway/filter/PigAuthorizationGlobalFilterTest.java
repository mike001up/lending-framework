package com.pig4cloud.pig.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.admin.api.feign.RemotePermService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PigAuthorizationFilterTest {

    private RemotePermService remotePermService;
    private ObjectProvider<RemotePermService> remotePermServiceProvider;
    private GatewaySecurityProperties securityProperties;
    private RedisTemplate<String, Object> redisTemplate;
    private ObjectMapper objectMapper;
    private PigAuthorizationFilter filter;

    @BeforeEach
    void setUp() {
        remotePermService = mock(RemotePermService.class);
        remotePermServiceProvider = mock(ObjectProvider.class);
        when(remotePermServiceProvider.getIfAvailable()).thenReturn(remotePermService);

        securityProperties = new GatewaySecurityProperties();
        securityProperties.setAuthorizeEnabled(true);
        securityProperties.setPermissionCacheTtlMs(300000);
        securityProperties.setPermissionCacheMaxSize(1000);

        redisTemplate = mock(RedisTemplate.class);
        objectMapper = mock(ObjectMapper.class);

        filter = new PigAuthorizationFilter(securityProperties, remotePermServiceProvider, redisTemplate, objectMapper);
    }

    @Test
    void getOrder_returnsAuthorizationOrder() {
        assertEquals(GatewayAttrConstants.GATEWAY_ORDER_FILTER_AUTHORIZATION, filter.getOrder());
    }

    @Test
    void filter_authorizeDisabled_passesThrough() {
        securityProperties.setAuthorizeEnabled(false);
        filter = new PigAuthorizationFilter(securityProperties, remotePermServiceProvider, redisTemplate, objectMapper);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);
        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(any());
        verify(remotePermService, never()).getAuthorizeRules();
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
        verify(remotePermService, never()).getAuthorizeRules();
    }

    @Test
    void filter_missingUsername_returns401() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_adminUser_doesNotSkipAuth() {
        // 移除了 admin 硬编码跳过，所以 admin 也需要权限校验
        SysPermission rule = new SysPermission();
        rule.setPath("/admin/**");
        rule.setMethod(null);
        rule.setPermission("admin_perm");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        UserInfo userInfo = new UserInfo();
        userInfo.setPermissions(new String[]{"admin_perm"});
        when(remotePermService.getUserInfo("admin")).thenReturn(R.ok(userInfo));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "admin").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());
        verify(remotePermService, times(1)).getAuthorizeRules();
        verify(remotePermService, times(1)).getUserInfo("admin");
    }

    @Test
    void filter_noMatchingRule_passesThrough() {
        SysPermission rule = new SysPermission();
        rule.setPath("/code/**");
        rule.setMethod(null);
        rule.setPermission("codegen_view");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());
        verify(remotePermService, times(1)).getAuthorizeRules();
        verify(remotePermService, never()).getUserInfo(any());
    }

    @Test
    void filter_matchingRuleWithEmptyPermission_passesThrough() {
        SysPermission rule = new SysPermission();
        rule.setPath("/admin/**");
        rule.setMethod(null);
        rule.setPermission("");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());
        verify(remotePermService, times(1)).getAuthorizeRules();
        verify(remotePermService, never()).getUserInfo(any());
    }

    @Test
    void filter_userHasPermission_passesThrough() {
        SysPermission rule = new SysPermission();
        rule.setPath("/admin/**");
        rule.setMethod(null);
        rule.setPermission("sys_user_view");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        UserInfo userInfo = new UserInfo();
        userInfo.setPermissions(new String[]{"sys_user_view", "sys_dept_view"});
        when(remotePermService.getUserInfo("normal")).thenReturn(R.ok(userInfo));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
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
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        UserInfo userInfo = new UserInfo();
        userInfo.setPermissions(new String[]{"sys_user_view"});
        when(remotePermService.getUserInfo("normal")).thenReturn(R.ok(userInfo));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_getUserInfoFails_returns403() {
        SysPermission rule = new SysPermission();
        rule.setPath("/admin/**");
        rule.setMethod(null);
        rule.setPermission("sys_user_view");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        when(remotePermService.getUserInfo("normal")).thenReturn(R.failed("Service error"));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_getAuthorizeRulesFails_returns401() {
        when(remotePermService.getAuthorizeRules()).thenReturn(R.failed("Auth service error"));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_httpMethodMatching() {
        // 规则只匹配 POST，GET 请求应不匹配
        SysPermission rule = new SysPermission();
        rule.setPath("/admin/**");
        rule.setMethod("POST");
        rule.setPermission("sys_user_add");
        when(remotePermService.getAuthorizeRules()).thenReturn(R.ok(Collections.singletonList(rule)));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "normal").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        // 不匹配规则，直接放行
        verify(chain, times(1)).filter(any());
        verify(remotePermService, never()).getUserInfo(any());
    }

    @Test
    void filter_evictCache_clearsSpecificUser() {
        filter.evictCache("testuser");
        // 无返回值，仅验证不抛异常
    }

    @Test
    void filter_evictAllCache_clearsAll() {
        filter.evictAllCache();
        // 无返回值，仅验证不抛异常
    }
}