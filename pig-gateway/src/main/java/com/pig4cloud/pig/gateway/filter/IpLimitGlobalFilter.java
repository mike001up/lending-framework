package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.gateway.fegin.RemoteIPLimitService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class IpLimitGlobalFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider;

	private static final String CLIENT = "client";
	private static final String BMS = "bms";

	private static final Logger log = LoggerFactory.getLogger(IpLimitGlobalFilter.class);

	public IpLimitGlobalFilter(ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider) {
		this.remoteIPLimitServiceProvider = remoteIPLimitServiceProvider;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String client = request.getHeaders().getFirst(CLIENT);
		if (StringUtils.isEmpty(client) || !client.equalsIgnoreCase(BMS)) {
			return chain.filter(exchange);
		}
		String username = exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR);
		if (StrUtil.isNotBlank(username) && username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}
		String remoteIP = IpUtil.getIpAddress(request);
		return validIp(request, remoteIP, exchange).then(chain.filter(exchange));
	}

	@Override
	public int getOrder() {
		return -1;
	}

	private Mono<Void> validIp(ServerHttpRequest request, String remoteIP, ServerWebExchange exchange) {
		RemoteIPLimitService ipLimitService = remoteIPLimitServiceProvider.getIfAvailable();
		if (ipLimitService != null) {
			return Mono.fromCallable(() -> ipLimitService.isValidIP(SecurityConstants.FROM_IN, remoteIP))
					.subscribeOn(Schedulers.boundedElastic())
					.flatMap(isValidIP -> {
						if (!isValidIP) {
							log.warn("IP 校验失败，拒绝访问: {}", remoteIP);
							String acceptLang = request.getHeaders().getFirst("Accept-Language");
							if (acceptLang == null || acceptLang.isEmpty() || acceptLang.equals("zh-cn")) {
								acceptLang = "zh_CN";
							}
							return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
									MsgUtils.getMessageByLang(ErrorCodes.IP_NOT_EXISTS_SYSTEM, acceptLang, remoteIP),
									HttpStatus.FORBIDDEN);
						}
						return Mono.empty();
					}).onErrorResume(ex -> {
						log.error("调用 ipLimitService.isValidIP 出错", ex);
						return writeErrorResponse(exchange, 1,
								"调用 ipLimitService.isValidIP 出错: " + ex.getMessage(),
								HttpStatus.INTERNAL_SERVER_ERROR);
					});
		}
		return Mono.empty();
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
