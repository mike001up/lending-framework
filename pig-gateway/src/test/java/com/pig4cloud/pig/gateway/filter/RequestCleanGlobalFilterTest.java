package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RequestCleanGlobalFilterTest {

	private RequestCleanGlobalFilter filter;

	@BeforeEach
	void setUp() {
		filter = new RequestCleanGlobalFilter();
	}

	@Test
	void getOrder_returnsNegative3() {
		assertEquals(-3, filter.getOrder());
	}

	@Test
	void filter_missingClientHeader_returns401() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
		verify(chain, never()).filter(any());
	}

	@Test
	void filter_blankClientHeader_returns401() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info")
			.header(CommonConstants.CLIENT, " ")
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
	}

	@Test
	void filter_validClientHeader_passesThrough() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info")
			.header(CommonConstants.CLIENT, "test-app")
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		StepVerifier.create(result).verifyComplete();
		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_setsGatewayClientAttr() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info")
			.header(CommonConstants.CLIENT, "my-client")
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		assertEquals("my-client", exchange.getAttribute(GatewayAttrConstants.GATEWAY_CLIENT_ATTR));
	}

	@Test
	void filter_removesFromHeaderAndSetsFromOut() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info")
			.header(CommonConstants.CLIENT, "app")
			.header(SecurityConstants.FROM, SecurityConstants.FROM_IN)
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> capturedExchange = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(invocation -> {
			capturedExchange.set(invocation.getArgument(0));
			return Mono.empty();
		});

		filter.filter(exchange, chain).block();

		ServerWebExchange mutatedExchange = capturedExchange.get();
		assertNotNull(mutatedExchange);
		HttpHeaders headers = mutatedExchange.getRequest().getHeaders();
		assertEquals(SecurityConstants.FROM_OUT, headers.getFirst(SecurityConstants.FROM));
	}

	@Test
	void filter_setsRequestStartTimeHeader() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/admin/user/info")
			.header(CommonConstants.CLIENT, "app")
			.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		AtomicReference<ServerWebExchange> capturedExchange = new AtomicReference<>();
		when(chain.filter(any())).thenAnswer(invocation -> {
			capturedExchange.set(invocation.getArgument(0));
			return Mono.empty();
		});

		long before = System.currentTimeMillis();
		filter.filter(exchange, chain).block();
		long after = System.currentTimeMillis();

		ServerWebExchange mutatedExchange = capturedExchange.get();
		String startTimeStr = mutatedExchange.getRequest().getHeaders()
			.getFirst(CommonConstants.REQUEST_START_TIME);
		assertNotNull(startTimeStr);
		long startTime = Long.parseLong(startTimeStr);
		assertTrue(startTime >= before && startTime <= after);
	}

}