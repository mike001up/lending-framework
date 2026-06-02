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
import java.util.Base64;
import java.util.Map;

@Slf4j
public class PigAuthenticationFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteUserService> userServiceProvider;

	private static final String KEY_QUERY_PARAM_USER_NAME = "username";

	private static final String BASIC = "Basic";

	private static final String BEARER = "Bearer ";

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
			String username = extractUsernameFromBasic(token);
			if (StrUtil.isBlank(username)) {
				log.warn("Basic 认证凭据无效, path: {}", request.getURI().getPath());
				return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
						HttpStatus.UNAUTHORIZED);
			}
			exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, username);
			return chain.filter(exchange);
		}

		if (token.startsWith(BEARER)) {
			RemoteUserService userService = userServiceProvider.getIfAvailable();
			if (userService != null) {
				return Mono.fromCallable(() -> {
				Map<String, Object> user = userService.getUser(token);
					return user;
				}).subscribeOn(Schedulers.boundedElastic()).flatMap(user -> {
					if (user == null || user.get(KEY_QUERY_PARAM_USER_NAME) == null) {
						log.warn("Token 解析无用户信息, path: {}", request.getURI().getPath());
						return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), "Unauthorized",
								HttpStatus.FORBIDDEN);
					}
					String userName = user.get(KEY_QUERY_PARAM_USER_NAME).toString();
					exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, userName);

					Object tenantId = user.get("tenantId");
					if (tenantId != null) {
						exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_TENANT_ATTR, tenantId.toString());
					}

					ServerHttpRequest mutatedRequest = request.mutate().headers(headers -> {
						String tid = exchange.getAttribute(GatewayAttrConstants.GATEWAY_TENANT_ATTR);
						if (StrUtil.isNotBlank(tid)) {
							headers.set(SecurityConstants.TENANT_ID, tid);
						}
					}).build();

					return chain.filter(exchange.mutate().request(mutatedRequest).build());
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

	private String extractUsernameFromBasic(String authorization) {
		try {
			String base64Credentials = authorization.substring(BASIC.length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
			int colonIndex = credentials.indexOf(':');
			return colonIndex > 0 ? credentials.substring(0, colonIndex) : null;
		}
		catch (IllegalArgumentException ex) {
			log.warn("Basic 凭据 Base64 解码失败", ex);
			return null;
		}
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
