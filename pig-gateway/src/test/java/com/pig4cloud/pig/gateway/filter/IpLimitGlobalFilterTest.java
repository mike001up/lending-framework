package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.admin.api.feign.RemoteIPLimitService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PigIpLimitFilterTest {

    private RemoteIPLimitService remoteIPLimitService;
    private ObjectProvider<RemoteIPLimitService> ipLimitServiceProvider;
    private GatewaySecurityProperties securityProperties;

    @BeforeEach
    void setUp() {
        remoteIPLimitService = mock(RemoteIPLimitService.class);
        ipLimitServiceProvider = mock(ObjectProvider.class);
        when(ipLimitServiceProvider.getIfAvailable()).thenReturn(remoteIPLimitService);
        securityProperties = new GatewaySecurityProperties();
        securityProperties.setIpCacheTtlSeconds(300);
        securityProperties.setIpCacheMaxSize(10000);

        // 模拟 MessageSource
        ApplicationContext mockCtx = mock(ApplicationContext.class);
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("sys.ip.not.exists", java.util.Locale.CHINA, "IP {0} not in whitelist");
        messageSource.addMessage("sys.ip.not.exists", java.util.Locale.US, "IP {0} not in whitelist");
        when(mockCtx.getBean(eq("messageSource"))).thenReturn(messageSource);
        when(mockCtx.getBean(any(String.class))).thenReturn(null);
        try {
            Field field = SpringContextHolder.class.getDeclaredField("applicationContext");
            field.setAccessible(true);
            field.set(null, mockCtx);
        } catch (Exception ignored) {}
    }

    @Test
    void getOrder_returnsIpLimitOrder() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        assertEquals(GatewayAttrConstants.GATEWAY_ORDER_FILTER_IP_LIMIT, filter.getOrder());
    }

    @Test
    void filter_adminUser_skipsIpCheck() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, SecurityConstants.ADMIN)
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, "test-client")
                        .remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);
        StepVerifier.create(result).verifyComplete();
        verify(remoteIPLimitService, never()).check(any(), any());
        verify(chain, times(1)).filter(any());
    }

    @Test
    void filter_missingClientId_returns403() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();

        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_ipCacheHitValid_passesThrough() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        String clientId = "test-client";
        String remoteIp = "192.168.1.1";
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        // 第一次调用，缓存未命中，调用远程服务
        R<Boolean> successResponse = R.ok(true);
        when(remoteIPLimitService.check(clientId, remoteIp)).thenReturn(successResponse);

        filter.filter(exchange, chain).block();
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp);
        verify(chain, times(1)).filter(any());

        // 第二次调用，缓存命中
        reset(chain);
        MockServerWebExchange exchange2 = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        when(chain.filter(any())).thenReturn(Mono.empty());
        filter.filter(exchange2, chain).block();
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp); // 未再调用
        verify(chain, times(1)).filter(any());
    }

    @Test
    void filter_ipCacheHitInvalid_returns403() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        String clientId = "test-client";
        String remoteIp = "10.0.0.1";
        // 第一次调用，返回 false
        R<Boolean> failResponse = R.ok(false);
        when(remoteIPLimitService.check(clientId, remoteIp)).thenReturn(failResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();
        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp);

        // 第二次调用，缓存命中，仍然返回403
        MockServerWebExchange exchange2 = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        filter.filter(exchange2, chain).block();
        assertEquals(HttpStatus.FORBIDDEN, exchange2.getResponse().getStatusCode());
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp); // 未再调用
    }

    @Test
    void filter_remoteValidationValid_passesAndCaches() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        String clientId = "test-client";
        String remoteIp = "172.16.0.1";
        R<Boolean> successResponse = R.ok(true);
        when(remoteIPLimitService.check(clientId, remoteIp)).thenReturn(successResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp);
        verify(chain, times(1)).filter(any());
    }

    @Test
    void filter_remoteValidationInvalid_returns403AndCaches() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        String clientId = "test-client";
        String remoteIp = "10.0.0.100";
        R<Boolean> failResponse = R.ok(false);
        when(remoteIPLimitService.check(clientId, remoteIp)).thenReturn(failResponse);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();
        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(remoteIPLimitService, times(1)).check(clientId, remoteIp);
    }

    @Test
    void filter_remoteServiceUnavailable_returns403() {
        ObjectProvider<RemoteIPLimitService> emptyProvider = mock(ObjectProvider.class);
        when(emptyProvider.getIfAvailable()).thenReturn(null);
        PigIpLimitFilter filter = new PigIpLimitFilter(emptyProvider, securityProperties);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, "test-client")
                        .remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();
        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }

    @Test
    void filter_remoteCallException_returns403() {
        PigIpLimitFilter filter = new PigIpLimitFilter(ipLimitServiceProvider, securityProperties);
        String clientId = "test-client";
        String remoteIp = "192.168.1.1";
        when(remoteIPLimitService.check(clientId, remoteIp)).thenThrow(new RuntimeException("Service error"));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(CommonConstants.GATEWAY_CLIENT_ATTR, clientId)
                        .remoteAddress(new InetSocketAddress(remoteIp, 1234)).build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        filter.filter(exchange, chain).block();
        assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
        verify(chain, never()).filter(any());
    }
}