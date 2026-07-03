package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WhitelistGlobalFilterTest {

	private GatewaySecurityProperties securityProperties;

	@BeforeEach
	void setUp() {
		securityProperties = new GatewaySecurityProperties();
	}

	@Test
	void getOrder_returnsNegative2() {
		PigWhitelistFilter filter = new PigWhitelistFilter(securityProperties);
		assertEquals(-2, filter.getOrder());
	}

	@Test
	void filter_matchesDefaultWhitelist_setsAttr() {
		PigWhitelistFilter filter = new PigWhitelistFilter(securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/auth/token/check_token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		assertEquals(Boolean.TRUE, exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR));
		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_noMatch_doesNotSetAttr() {
		PigWhitelistFilter filter = new PigWhitelistFilter(securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR));
	}

	@Test
	void filter_antPathMatcher_wildcard() {
		securityProperties.setIgnoreUrls(Arrays.asList("/auth/**"));
		PigWhitelistFilter filter = new PigWhitelistFilter(securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/auth/token/login").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertEquals(Boolean.TRUE, exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR));
	}

	@Test
	void filter_emptyIgnoreUrls_noMatch() {
		securityProperties.setIgnoreUrls(Collections.emptyList());
		PigWhitelistFilter filter = new PigWhitelistFilter(securityProperties);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/auth/token/check_token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertNull(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR));
	}

}