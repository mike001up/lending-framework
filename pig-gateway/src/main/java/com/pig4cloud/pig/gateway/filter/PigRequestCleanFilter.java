package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
public class PigRequestCleanFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		String client = request.getHeaders().getFirst(CommonConstants.CLIENT);
		if (StrUtil.isBlank(client)) {
			log.warn("请求缺少 client 头, path: {}", request.getURI().getPath());
			return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), 
				"Missing client header",
					HttpStatus.UNAUTHORIZED);
		}

		ServerHttpRequest newRequest = request.mutate().headers(httpHeaders -> {
			httpHeaders.remove(SecurityConstants.FROM);
			httpHeaders.remove(CommonConstants.CLIENT);

			//TODO 增加请求ID

			httpHeaders.put(CommonConstants.REQUEST_START_TIME,
					Collections.singletonList(String.valueOf(System.currentTimeMillis())));

			httpHeaders.put(CommonConstants.GATEWAY_CLIENT_ATTR, 
				Collections.singletonList(client));
		}).build();

		return chain.filter(exchange.mutate().request(newRequest).build());
	}

	@Override
	public int getOrder() {
		return GatewayAttrConstants.GATEWAY_ORDER_FILTER_CLEAN;
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
