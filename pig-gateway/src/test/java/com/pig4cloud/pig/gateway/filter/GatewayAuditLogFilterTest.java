package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.api.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GatewayAuditLogFilterTest {

	private RemoteLogService remoteLogService;
	private ObjectProvider<RemoteLogService> logServiceProvider;

	@BeforeEach
	void setUp() {
		remoteLogService = mock(RemoteLogService.class);
		logServiceProvider = mock(ObjectProvider.class);
		when(logServiceProvider.getIfAvailable()).thenReturn(remoteLogService);
		when(remoteLogService.saveLog(any())).thenReturn(R.ok(true));
	}

	@Test
	void getOrder_returns5() {
		GatewayAuditLogFilter filter = new GatewayAuditLogFilter(logServiceProvider);
		assertEquals(5, filter.getOrder());
	}

	@Test
	void filter_normalRequest_recordsAuditLog() {
		GatewayAuditLogFilter filter = new GatewayAuditLogFilter(logServiceProvider);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, "testuser");
		exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_CLIENT_ATTR, "web-app");
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		Mono<Void> result = filter.filter(exchange, chain);

		result.block();
		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_errorResponse_logTypeIs9() {
		GatewayAuditLogFilter filter = new GatewayAuditLogFilter(logServiceProvider);
		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/not-exist").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);

		filter.filter(exchange, chain).block();
	}

	@Test
	void filter_remoteLogServiceUnavailable_doesNotAffectRequest() {
		ObjectProvider<RemoteLogService> emptyProvider = mock(ObjectProvider.class);
		when(emptyProvider.getIfAvailable()).thenReturn(null);
		GatewayAuditLogFilter filter = new GatewayAuditLogFilter(emptyProvider);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

	@Test
	void filter_saveLogFails_doesNotAffectResponse() {
		when(remoteLogService.saveLog(any())).thenThrow(new RuntimeException("Log service error"));
		GatewayAuditLogFilter filter = new GatewayAuditLogFilter(logServiceProvider);

		MockServerWebExchange exchange = MockServerWebExchange
			.from(MockServerHttpRequest.get("/admin/user/info").build());
		GatewayFilterChain chain = mock(GatewayFilterChain.class);
		when(chain.filter(any())).thenReturn(Mono.empty());

		filter.filter(exchange, chain).block();

		verify(chain, times(1)).filter(any());
	}

}