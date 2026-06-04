package com.pig4cloud.pig.gateway.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.common.core.util.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

	private ObjectMapper objectMapper;
	private GlobalExceptionHandler handler;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		handler = new GlobalExceptionHandler(objectMapper);
	}

	@Test
	void handle_responseStatusException_setsCorrectStatusCode() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");

		Mono<Void> result = handler.handle(exchange, ex);

		StepVerifier.create(result).verifyComplete();
		assertEquals(HttpStatus.NOT_FOUND, exchange.getResponse().getStatusCode());
	}

	@Test
	void handle_runtimeException_responseIsJson() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		RuntimeException ex = new RuntimeException("Internal Error");

		Mono<Void> result = handler.handle(exchange, ex);

		StepVerifier.create(result).verifyComplete();
		assertEquals(MediaType.APPLICATION_JSON, exchange.getResponse().getHeaders().getContentType());
	}

	@Test
	void handle_committedResponse_propagatesError() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getResponse().setComplete().block();
		RuntimeException ex = new RuntimeException("Error after commit");

		Mono<Void> result = handler.handle(exchange, ex);

		StepVerifier.create(result).expectError(RuntimeException.class).verify();
	}

	@Test
	void handle_setsContentTypeToJson() {
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		RuntimeException ex = new RuntimeException("Test error");

		handler.handle(exchange, ex).block();

		assertEquals(MediaType.APPLICATION_JSON, exchange.getResponse().getHeaders().getContentType());
	}

}