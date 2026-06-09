package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.admin.api.feign.RemoteIPLimitService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IpLimitGlobalFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider;

	private final Cache<String, Boolean> ipCache;

	public IpLimitGlobalFilter(ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider,
			GatewaySecurityProperties securityProperties) {
		this.remoteIPLimitServiceProvider = remoteIPLimitServiceProvider;
		this.ipCache = Caffeine.newBuilder()
			.expireAfterWrite(securityProperties.getIpCacheTtlSeconds(), TimeUnit.SECONDS)
			.maximumSize(securityProperties.getIpCacheMaxSize())
			.build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String username = exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR);
		if (StrUtil.isNotBlank(username) && username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
		String remoteIP = IpUtil.getIpAddress(request);

		Boolean cached = ipCache.getIfPresent(remoteIP);
		if (cached != null) {
			if (cached) {
				return chain.filter(exchange);
			}
			log.warn("IP 校验失败(缓存), 拒绝访问: {}", remoteIP);
			return writeForbiddenResponse(request, exchange, remoteIP);
		}

		return validateIpRemotely(request, remoteIP, exchange, chain);
	}

	@Override
	public int getOrder() {
		return 0;
	}

	private Mono<Void> validateIpRemotely(ServerHttpRequest request, String remoteIP, ServerWebExchange exchange,
			GatewayFilterChain chain) {
		RemoteIPLimitService ipLimitService = remoteIPLimitServiceProvider.getIfAvailable();
		if (ipLimitService == null) {
			log.warn("RemoteIPLimitService 不可用, 降级放行: {}", remoteIP);
			return chain.filter(exchange);
		}

		return Mono.fromCallable(() -> ipLimitService.isValidIP(remoteIP))
			.subscribeOn(Schedulers.boundedElastic())
			.flatMap(isValidIP -> {
				ipCache.put(remoteIP, isValidIP);
				if (!isValidIP) {
					log.warn("IP 校验失败, 拒绝访问: {}", remoteIP);
					return writeForbiddenResponse(request, exchange, remoteIP);
				}
				return chain.filter(exchange);
			})
			.onErrorResume(ex -> {
				log.error("调用 ipLimitService.isValidIP 出错", ex);
				return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
						"IP validation failed", HttpStatus.FORBIDDEN);
			});
	}

	private Mono<Void> writeForbiddenResponse(ServerHttpRequest request, ServerWebExchange exchange,
			String remoteIP) {
		String acceptLang = request.getHeaders().getFirst("Accept-Language");
		if (acceptLang == null || acceptLang.isEmpty() || acceptLang.equals("zh-cn")) {
			acceptLang = "zh_CN";
		}
		return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
				MsgUtils.getMessageByLang(ErrorCodes.IP_NOT_EXISTS_SYSTEM, acceptLang, remoteIP),
				HttpStatus.FORBIDDEN);
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
