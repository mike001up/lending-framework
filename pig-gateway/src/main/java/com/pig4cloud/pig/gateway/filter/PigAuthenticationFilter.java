package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import feign.FeignException;
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
import java.util.Map;

public class PigAuthenticationFilter implements GlobalFilter, Ordered {

	private final ObjectProvider<RemoteUserService> userServiceProvider;

	private final GatewaySecurityProperties securityProperties;

	private static final String KEY_QUERY_PARAM_USER_NAME = "username";
	private static final String TOKEN_DEL = "token_del";
	private static final String CLIENT = "client";
	private static final String BMS = "bms";
	private static final String BASIC = "Basic";
	private static final String AUTHORIZATION = "Authorization";
	private static final String UNAUTHORIZED = "Unauthorized";

	private static final Logger log = LoggerFactory.getLogger(PigAuthenticationFilter.class);

	public PigAuthenticationFilter(ObjectProvider<RemoteUserService> userServiceProvider,
			GatewaySecurityProperties securityProperties) {
		this.userServiceProvider = userServiceProvider;
		this.securityProperties = securityProperties;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String requestPath = request.getURI().getPath();
		if (isIgnoreUrl(requestPath)) {
			return chain.filter(exchange);
		}
		String client = request.getHeaders().getFirst(CLIENT);
		if (StrUtil.isBlank(client)) {
			return chain.filter(exchange);
		}
		return parseUserNameFromReq(request).flatMap(userName -> {
			if (StrUtil.isNotBlank(userName) && userName.equalsIgnoreCase(TOKEN_DEL)) {
				return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), UNAUTHORIZED, HttpStatus.FORBIDDEN);
			}
			if (StrUtil.isNotBlank(userName)) {
				exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_USERNAME_ATTR, userName);
			}
			return chain.filter(exchange);
		});
	}

	@Override
	public int getOrder() {
		return -1;
	}

	private boolean isIgnoreUrl(String requestPath) {
		for (String ignoreUrl : securityProperties.getIgnoreUrls()) {
			if (requestPath.startsWith(ignoreUrl)) {
				return true;
			}
		}
		return false;
	}

	private Mono<String> parseUserNameFromReq(ServerHttpRequest request) {
		String token = request.getHeaders().getFirst(AUTHORIZATION);
		String client = request.getHeaders().getFirst(CLIENT);
		if (!StringUtils.isEmpty(token) && token.startsWith(BASIC) && client.equalsIgnoreCase(BMS)) {
			String username = request.getHeaders().getFirst(KEY_QUERY_PARAM_USER_NAME);
			if (StringUtils.isEmpty(username)) {
				return Mono.just(TOKEN_DEL);
			}
			return Mono.just(username);
		}
		if (!StringUtils.isEmpty(token) && token.length() > 18) {
			RemoteUserService userService = userServiceProvider.getIfAvailable();
			if (userService != null) {
				return Mono.fromCallable(() -> {
					Map<String, Object> user = userService.getUser(SecurityConstants.FROM_IN, token);
					return user != null ? user.get(KEY_QUERY_PARAM_USER_NAME).toString() : null;
				}).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
					if (ex instanceof FeignException fe && (fe.status() == 403 || fe.status() == 401)) {
						log.warn("远程 userService 返回 401");
						return Mono.just(TOKEN_DEL);
					}
					log.error("调用 userService.getUser 出错", ex);
					return Mono.just(TOKEN_DEL);
				});
			}
		}
		return Mono.just(SecurityConstants.ADMIN);
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

}
