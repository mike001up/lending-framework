package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.admin.api.feign.RemoteIPLimitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
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
import static org.mockito.Mockito.*;

class IpLimitGlobalFilterTest {

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

		ApplicationContext mockCtx = mock(ApplicationContext.class);
		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage("sys.ip.not.exists", java.util.Locale.CHINA, "IP {0} not in whitelist");
		messageSource.addMessage("sys.ip.not.exists", java.util.Locale.US, "IP {0} not in whitelist");
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
	void getOrder_returns0() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		assertEquals(0, filter.getOrder());
	}

	@Test
	void filter_adminUser_skipsIpCheck() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, SecurityConstants.ADMIN);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		verify(remoteIPLimitService, never()).isValidIP(any());
	}

	@Test
	void filter_ipCacheHitValid_passesThrough() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
		when(remoteIPLimitService.isValidIP("192.168.1.1")).thenReturn(true);

		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(remoteIPLimitService, times(1)).isValidIP("192.168.1.1");

		MockServerWebExchange exchange2 = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange2, chain).block();

		verify(remoteIPLimitService, times(1)).isValidIP("192.168.1.1");
	}

	@Test
	void filter_ipCacheHitInvalid_returns403() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		when(remoteIPLimitService.isValidIP("10.0.0.1")).thenReturn(false);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("10.0.0.1", 1234)).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());

		MockServerWebExchange exchange2 = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("10.0.0.1", 1234)).build());

		filter.filter(exchange2, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange2.getResponse().getStatusCode());
		verify(remoteIPLimitService, times(1)).isValidIP("10.0.0.1");
	}

	@Test
	void filter_remoteValidationValid_passesAndCaches() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		when(remoteIPLimitService.isValidIP("172.16.0.1")).thenReturn(true);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("172.16.0.1", 1234)).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(remoteIPLimitService, times(1)).isValidIP("172.16.0.1");
		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_remoteValidationInvalid_returns403AndCaches() {
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);
		when(remoteIPLimitService.isValidIP("10.0.0.100")).thenReturn(false);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("10.0.0.100", 1234)).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_remoteServiceUnavailable_returns403() {
		ObjectProvider<RemoteIPLimitService> emptyProvider = mock(ObjectProvider.class);
		when(emptyProvider.getIfAvailable()).thenReturn(null);
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(emptyProvider, securityProperties);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_remoteCallException_returns403() {
		when(remoteIPLimitService.isValidIP(any())).thenThrow(new RuntimeException("Service error"));
		IpLimitGlobalFilter filter = new IpLimitGlobalFilter(ipLimitServiceProvider, securityProperties);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info")
				.remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);

		filter.filter(exchange, chain).block();

		assertEquals(HttpStatus.FORBIDDEN, exchange.getResponse().getStatusCode());
	}

}