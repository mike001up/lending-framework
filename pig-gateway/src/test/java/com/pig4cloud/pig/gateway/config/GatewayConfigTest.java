package com.pig4cloud.pig.gateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.test.StepVerifier;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

class GatewaySecurityPropertiesTest {

	@Test
	void defaultValues() {
		GatewaySecurityProperties props = new GatewaySecurityProperties();
		assertEquals(1, props.getIgnoreUrls().size());
		assertEquals("/auth/token/check_token", props.getIgnoreUrls().get(0));
		assertTrue(props.isAuthorizeEnabled());
		assertEquals(300000, props.getPermissionCacheTtlMs());
		assertEquals(300, props.getIpCacheTtlSeconds());
		assertEquals(10000, props.getIpCacheMaxSize());
		assertEquals(1000, props.getPermissionCacheMaxSize());
	}

	@Test
	void customValues() {
		GatewaySecurityProperties props = new GatewaySecurityProperties();
		props.setAuthorizeEnabled(false);
		props.setPermissionCacheTtlMs(60000);
		props.setIpCacheTtlSeconds(60);
		assertFalse(props.isAuthorizeEnabled());
		assertEquals(60000, props.getPermissionCacheTtlMs());
		assertEquals(60, props.getIpCacheTtlSeconds());
	}

}

class RateLimiterConfigurationTest {

	@Test
	void remoteAddrKeyResolver_returnsRemoteIp() {
		RateLimiterConfiguration config = new RateLimiterConfiguration();
		KeyResolver resolver = config.remoteAddrKeyResolver();

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/test")
				.remoteAddress(new InetSocketAddress("192.168.1.1", 1234)).build());

		StepVerifier.create(resolver.resolve(exchange))
			.expectNext("192.168.1.1")
			.verifyComplete();
	}

	@Test
	void remoteAddrKeyResolver_nullRemoteAddress_throwsNPE() {
		RateLimiterConfiguration config = new RateLimiterConfiguration();
		KeyResolver resolver = config.remoteAddrKeyResolver();

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/test").build());

		assertThrows(NullPointerException.class, () -> resolver.resolve(exchange).block());
	}

}

class I18nConfigurationTest {

	@Test
	void messageSource_beanCreated() {
		I18nConfiguration config = new I18nConfiguration();
		assertNotNull(config.messageSource());
	}

	@Test
	void messageSource_isReloadableResourceBundleMessageSource() {
		I18nConfiguration config = new I18nConfiguration();
		MessageSource ms = config.messageSource();
		assertInstanceOf(ReloadableResourceBundleMessageSource.class, ms);
	}

}

class GatewayConfigurationTest {

	@Test
	void requestCleanGlobalFilter_created() {
		GatewayConfiguration config = new GatewayConfiguration();
		assertNotNull(config.requestCleanGlobalFilter());
	}

	@Test
	void whitelistGlobalFilter_created() {
		GatewayConfiguration config = new GatewayConfiguration();
		GatewaySecurityProperties props = new GatewaySecurityProperties();
		assertNotNull(config.whitelistGlobalFilter(props));
	}

	@Test
	void pigRequestGlobalFilter_created() {
		GatewayConfiguration config = new GatewayConfiguration();
		assertNotNull(config.pigRequestGlobalFilter());
	}

}