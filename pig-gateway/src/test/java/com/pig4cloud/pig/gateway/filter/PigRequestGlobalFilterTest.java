package com.pig4cloud.pig.gateway.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PigRequestGlobalFilterTest {

	private PigPathStripFilter filter;

	@BeforeEach
	void setUp() {
		filter = new PigPathStripFilter();
	}

	@Test
	void getOrder_returns10() {
		assertEquals(10, filter.getOrder());
	}

	@Test
	void filter_standardPrefixPath_stripsFirstSegment() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/list").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> captured = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(inv -> {
			captured.set(inv.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutated = captured.get();
		assertNotNull(mutated);
		assertEquals("/user/list", mutated.getRequest().getURI().getRawPath());
	}

	@Test
	void filter_twoSegmentPrefix_stripsFirstSegment() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/auth/token/check_token").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> captured = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(inv -> {
			captured.set(inv.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutated = captured.get();
		assertEquals("/token/check_token", mutated.getRequest().getURI().getRawPath());
	}

	@Test
	void filter_singleSegment_stripsToRoot() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/single").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> captured = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(inv -> {
			captured.set(inv.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutated = captured.get();
		assertEquals("/", mutated.getRequest().getURI().getRawPath());
	}

	@Test
	void filter_rootPath_remainsRoot() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> captured = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(inv -> {
			captured.set(inv.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutated = captured.get();
		assertEquals("/", mutated.getRequest().getURI().getRawPath());
	}

	@Test
	void filter_setsGatewayRequestUrlAttr() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/list").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertNotNull(exchange.getAttributes()
			.get(org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR));
	}

}