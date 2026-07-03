package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.core.constant.enums.HttpMethodEnum;
import com.pig4cloud.pig.common.core.constant.enums.LogTypeEnum;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.filter.FeignRequestContext;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PigAuditLogFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteLogService> remoteLogServiceProvider;

	public PigAuditLogFilter(ObjectProvider<RemoteLogService> remoteLogServiceProvider) {
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

			Map<String, String> headers = new HashMap<>();
			List<String> headerKeys = Arrays.asList(
				SecurityConstants.HEADER_PRINCIPAL,
				SecurityConstants.HEADER_USER_ID,
				SecurityConstants.HEADER_USERNAME,
				SecurityConstants.HEADER_TENANT_ID,
				SecurityConstants.HEADER_GRANT_TYPE,
				SecurityConstants.HEADER_CLIENT_ID
			);

			for (String key : headerKeys) {
				String value = request.getHeaders().getFirst(key);
				if (StrUtil.isNotBlank(value)) {
					headers.put(key, value);
				}
			}

			RemoteSysLogDTO sysLog = new RemoteSysLogDTO();
			sysLog.setLogType(LogTypeEnum.NORMAL);
			sysLog.setTitle("gateway_access");
			sysLog.setOperationType("GATEWAY_ACCESS");
			sysLog.setRequestUri(request.getURI().getPath());
			sysLog.setMethod(HttpMethodEnum.valueOf(request.getMethod().name()));
			sysLog.setRemoteAddr(IpUtil.getIpAddress(request));
			sysLog.setServiceId("pig-gateway");
			sysLog.setTime(duration);
			sysLog.setCreateBy(headers.get(SecurityConstants.HEADER_USERNAME));
			sysLog.setUserId(headers.get(SecurityConstants.HEADER_USER_ID) == null?null : Long.valueOf(headers.get(SecurityConstants.HEADER_USER_ID)));
			sysLog.setTargetObject(request.getURI().getPath());
			sysLog.setClientName(headers.get(SecurityConstants.HEADER_CLIENT_ID));
			sysLog.setParams("client=" + headers.get(SecurityConstants.HEADER_CLIENT_ID) == null?"":headers.get(SecurityConstants.HEADER_CLIENT_ID));
			 
			int statusCode = exchange.getResponse().getStatusCode() != null
					? exchange.getResponse().getStatusCode().value() : 0;
			sysLog.setResult(statusCode < 400 ? 1 : 0);
			if (statusCode >= 400) {
				sysLog.setLogType(LogTypeEnum.ERROR);
				sysLog.setException("HTTP " + statusCode);
			}

			Mono.fromCallable(() ->{
					FeignRequestContext.setAll(headers);
					R<Boolean> result = logService.saveLog(sysLog);
					return result;
				}).subscribeOn(Schedulers.boundedElastic())
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
		return GatewayAttrConstants.GATEWAY_ORDER_FILTER_LOG;
	}

}