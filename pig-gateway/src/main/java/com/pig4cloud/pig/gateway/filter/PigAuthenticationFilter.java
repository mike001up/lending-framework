package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import feign.FeignException;
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
import java.util.Map;

@Slf4j
public class PigAuthenticationFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteUserService> userServiceProvider;

	private static final String KEY_QUERY_PARAM_USER_NAME = "username";

	private static final String BASIC = "Basic";

	private static final String AUTHORIZATION = "Authorization";

	public PigAuthenticationFilter(ObjectProvider<RemoteUserService> userServiceProvider) {
		this.userServiceProvider = userServiceProvider;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (Boolean.TRUE.equals(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR))) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
		String token = request.getHeaders().getFirst(AUTHORIZATION);

		if (StrUtil.isBlank(token)) {
			log.warn("请求未携带认证信息, path: {}", request.getURI().getPath());
			return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
					HttpStatus.UNAUTHORIZED);
		}

		if (token.startsWith(BASIC)) {
			String username = request.getHeaders().getFirst(KEY_QUERY_PARAM_USER_NAME);
			if (StrUtil.isBlank(username)) {
				log.warn("Basic 认证缺少 username, path: {}", request.getURI().getPath());
				return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
						HttpStatus.UNAUTHORIZED);
			}
			exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, username);
			return chain.filter(exchange);
		}

		if (token.length() > 18) {
			RemoteUserService userService = userServiceProvider.getIfAvailable();
			if (userService != null) {
				return Mono.fromCallable(() -> {
					Map<String, Object> user = userService.getUser(SecurityConstants.FROM_IN, token);
					return user != null ? user.get(KEY_QUERY_PARAM_USER_NAME).toString() : null;
				}).subscribeOn(Schedulers.boundedElastic()).flatMap(userName -> {
					if (StrUtil.isBlank(userName)) {
						log.warn("Token 解析无用户信息, path: {}", request.getURI().getPath());
						return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), "Unauthorized",
								HttpStatus.FORBIDDEN);
					}
					exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, userName);
					return chain.filter(exchange);
				}).onErrorResume(ex -> {
					if (ex instanceof FeignException fe && (fe.status() == 403 || fe.status() == 401)) {
						log.warn("Token 验证失败, 远程 userService 返回 401");
						return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), "Unauthorized",
								HttpStatus.FORBIDDEN);
					}
					log.error("调用 userService.getUser 出错", ex);
					return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), "Unauthorized",
							HttpStatus.FORBIDDEN);
				});
			}
		}

		log.warn("Token 格式不合法, path: {}", request.getURI().getPath());
		return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
				HttpStatus.UNAUTHORIZED);
	}

	@Override
	public int getOrder() {
		return -1;
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
