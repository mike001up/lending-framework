package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.api.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class GatewayAuditLogFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteLogService> remoteLogServiceProvider;

	public GatewayAuditLogFilter(ObjectProvider<RemoteLogService> remoteLogServiceProvider) {
		this.remoteLogServiceProvider = remoteLogServiceProvider;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		long startTime = System.currentTimeMillis();

		return chain.filter(exchange).doFinally(signalType -> {
			long duration = System.currentTimeMillis() - startTime;
			saveAuditLog(exchange, request, duration, signalType);
		});
	}

	private void saveAuditLog(ServerWebExchange exchange, ServerHttpRequest request, long duration,
			Object signalType) {
		try {
			RemoteLogService logService = remoteLogServiceProvider.getIfAvailable();
			if (logService == null) {
				return;
			}

			SysLog sysLog = new SysLog();
			sysLog.setLogType("0");
			sysLog.setTitle("gateway_access");
			sysLog.setOperationType("GATEWAY_ACCESS");
			sysLog.setRequestUri(request.getURI().getPath());
			sysLog.setMethod(request.getMethod().name());
			sysLog.setRemoteAddr(IpUtil.getIpAddress(request));
			sysLog.setUserAgent(request.getHeaders().getFirst(HttpHeaders.USER_AGENT));
			sysLog.setServiceId("pig-gateway");
			sysLog.setTime(duration);

			String username = exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR);
			if (username != null) {
				sysLog.setCreateBy(username);
			}

			Object userId = exchange.getAttribute("gateway_user_id");
			if (userId != null) {
				sysLog.setUserId(Long.valueOf(userId.toString()));
			}

			sysLog.setTargetObject(request.getURI().getPath());

			String client = exchange.getAttribute(GatewayAttrConstants.GATEWAY_CLIENT_ATTR);
			if (client != null) {
				sysLog.setClientName(client);
				sysLog.setParams("client=" + client);
			}

			int statusCode = exchange.getResponse().getStatusCode() != null
					? exchange.getResponse().getStatusCode().value() : 0;
			sysLog.setResult(statusCode < 400 ? 1 : 0);
			if (statusCode >= 400) {
				sysLog.setLogType("9");
				sysLog.setException("HTTP " + statusCode);
			}

			Mono.fromCallable(() -> logService.saveLog(sysLog))
				.subscribeOn(Schedulers.boundedElastic())
				.onErrorResume(ex -> {
					log.warn("保存审计日志失败", ex);
					return Mono.just(R.ok(false));
				})
				.subscribe();

		}
		catch (Exception ex) {
			log.warn("构建审计日志失败", ex);
		}
	}

	@Override
	public int getOrder() {
		return 5;
	}

}