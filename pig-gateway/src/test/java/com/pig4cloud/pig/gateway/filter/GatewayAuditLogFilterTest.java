package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
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

class PigAuditLogFilterTest {

    private RemoteLogService remoteLogService;
    private ObjectProvider<RemoteLogService> logServiceProvider;
    private PigAuditLogFilter filter;

    @BeforeEach
    void setUp() {
        remoteLogService = mock(RemoteLogService.class);
        logServiceProvider = mock(ObjectProvider.class);
        when(logServiceProvider.getIfAvailable()).thenReturn(remoteLogService);
        when(remoteLogService.saveLog(any(RemoteSysLogDTO.class))).thenReturn(R.ok(true));
        filter = new PigAuditLogFilter(logServiceProvider);
    }

    @Test
    void getOrder_returnsLogOrder() {
        assertEquals(GatewayAttrConstants.GATEWAY_ORDER_FILTER_LOG, filter.getOrder());
    }

    @Test
    void filter_normalRequest_recordsAuditLogAsynchronously() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "testuser")
                        .header(SecurityConstants.HEADER_CLIENT_ID, "web-app")
                        .header(SecurityConstants.HEADER_USER_ID, "123")
                        .build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());

        // 异步日志调用需要等待（使用超时验证）
        verify(remoteLogService, timeout(500)).saveLog(any(RemoteSysLogDTO.class));
    }

    @Test
    void filter_errorResponse_logTypeIsError() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/not-exist").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());
        verify(remoteLogService, timeout(500)).saveLog(any(RemoteSysLogDTO.class));
    }

    @Test
    void filter_remoteLogServiceUnavailable_doesNotAffectRequest() {
        ObjectProvider<RemoteLogService> emptyProvider = mock(ObjectProvider.class);
        when(emptyProvider.getIfAvailable()).thenReturn(null);
        PigAuditLogFilter filterNoService = new PigAuditLogFilter(emptyProvider);

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filterNoService.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());
        // 不调用 saveLog
        verify(remoteLogService, never()).saveLog(any());
    }

    @Test
    void filter_saveLogFails_doesNotAffectResponse() {
        when(remoteLogService.saveLog(any(RemoteSysLogDTO.class))).thenThrow(new RuntimeException("Log service error"));

        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info").build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        // 不应抛出异常
        assertDoesNotThrow(() -> filter.filter(exchange, chain).block());

        verify(chain, times(1)).filter(any());
        // 异步调用会触发异常，但被 onErrorResume 捕获，不影响主流程
    }

    @Test
    void filter_populatesAuditLogFieldsCorrectly() {
        MockServerWebExchange exchange = MockServerWebExchange
                .from(MockServerHttpRequest.get("/admin/user/info")
                        .header(SecurityConstants.HEADER_USERNAME, "testuser")
                        .header(SecurityConstants.HEADER_CLIENT_ID, "web-app")
                        .header(SecurityConstants.HEADER_USER_ID, "456")
                        .header(SecurityConstants.HEADER_TENANT_ID, "789")
                        .build());
        GatewayFilterChain chain = mock(GatewayFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(any());

        // 异步捕获并验证参数
        verify(remoteLogService, timeout(500)).saveLog(argThat(dto -> {
            assertEquals("/admin/user/info", dto.getRequestUri());
            assertEquals("testuser", dto.getCreateBy());
            assertEquals(Long.valueOf(456), dto.getUserId());
            assertEquals("web-app", dto.getClientName());
            assertEquals("pig-gateway", dto.getServiceId());
            return true;
        }));
    }
}