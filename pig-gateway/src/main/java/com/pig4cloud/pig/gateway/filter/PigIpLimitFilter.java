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
public class PigIpLimitFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider;

	private final Cache<String, Boolean> ipCache;

	//请求ID, 暂时没有实现，
    private String requestId = "";

	public PigIpLimitFilter(ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider,
			GatewaySecurityProperties securityProperties) {
		this.remoteIPLimitServiceProvider = remoteIPLimitServiceProvider;
		this.ipCache = Caffeine.newBuilder()
			.expireAfterWrite(securityProperties.getIpCacheTtlSeconds(), TimeUnit.SECONDS)
			.maximumSize(securityProperties.getIpCacheMaxSize())
			.build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String username = exchange.getRequest().getHeaders().getFirst(SecurityConstants.HEADER_USERNAME);
		if (StrUtil.isNotBlank(username) && username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}
		
		ServerHttpRequest request = exchange.getRequest();
		String remoteIP = IpUtil.getIpAddress(request);
		String clientId = exchange.getRequest().getHeaders().getFirst(CommonConstants.GATEWAY_CLIENT_ATTR);
		if (StrUtil.isBlank(clientId)) {
			log.error(CommonConstants.DEBUG_PREFIX + "禁止访问", 
                                    requestId
                                );
			return writeForbiddenResponse(request, exchange, remoteIP);
		}
		Boolean cached = ipCache.getIfPresent(String.format(SecurityConstants.REDIS_KEY_IP_LIMIT_RESULT, clientId, remoteIP));
		if (cached != null) {
			if (cached) {
				return chain.filter(exchange);
			}
			log.error(CommonConstants.DEBUG_PREFIX + "IP 校验失败(缓存), 拒绝访问: {}", 
                                    requestId,
									remoteIP
                                );
			return writeForbiddenResponse(request, exchange, remoteIP);
		}

		return validateIpRemotely(request, clientId, remoteIP, exchange, chain);
	}

	@Override
	public int getOrder() {
		return GatewayAttrConstants.GATEWAY_ORDER_FILTER_IP_LIMIT;
	}

	private Mono<Void> validateIpRemotely(ServerHttpRequest request, 
			String clientId, 
			String remoteIP, 
			ServerWebExchange exchange,
			GatewayFilterChain chain) {
		RemoteIPLimitService ipLimitService = remoteIPLimitServiceProvider.getIfAvailable();
		if (ipLimitService == null) {
			log.error(CommonConstants.DEBUG_PREFIX + "IP 远程校验服务不可用, 拒绝访问: {}", 
                                    requestId,
									remoteIP
                                );
			return writeForbiddenResponse(request, exchange, remoteIP);
		}

		return Mono.fromCallable(() -> {
			return ipLimitService.check(clientId, remoteIP);
		})
			.subscribeOn(Schedulers.boundedElastic())
			.flatMap(r -> {
				
				if (r == null || r.getCode() != 0 || r.getData() == null || r.getData().equals(Boolean.FALSE)) {
					log.error(CommonConstants.DEBUG_PREFIX + "IP 校验失败, 拒绝访问: {}", 
                                    requestId,
									remoteIP
                                );
					// ipCache.put(String.format(SecurityConstants.REDIS_KEY_IP_LIMIT_RESULT, clientId, remoteIP), Boolean.FALSE);
					return writeForbiddenResponse(request, exchange, remoteIP);
				}
				// ipCache.put(String.format(SecurityConstants.REDIS_KEY_IP_LIMIT_RESULT, clientId, remoteIP), Boolean.TRUE);
				return chain.filter(exchange);
			})
			.onErrorResume(ex -> {
				log.error("调用 ipLimitService.isValidIP 出错", ex);
				log.error(CommonConstants.DEBUG_PREFIX + "IP {} 校验错误: {}", 
                                    requestId,
									remoteIP,
									ex
                                );
				return writeErrorResponse(exchange, HttpStatus.FORBIDDEN,
						"IP validation failed");
			});
	}

	private Mono<Void> writeForbiddenResponse(ServerHttpRequest request, ServerWebExchange exchange,
			String remoteIP) {
		String acceptLang = request.getHeaders().getFirst("Accept-Language");
		if (acceptLang == null || acceptLang.isEmpty() || acceptLang.equals("zh-cn")) {
			acceptLang = "zh_CN";
		}
		return writeErrorResponse(exchange, HttpStatus.FORBIDDEN,
				MsgUtils.getMessageByLang(ErrorCodes.IP_NOT_EXISTS_SYSTEM, acceptLang, remoteIP));
	}

private Mono<Void> writeErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", status.value(), message.replace("\"", "\\\""));
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
