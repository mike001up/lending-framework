package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties.AuthorizeRule;
import com.pig4cloud.pig.gateway.fegin.RemotePermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PigAuthorizationGlobalFilter implements GlobalFilter, Ordered {

	private static final String CLIENT = "client";
	private static final String BMS = "bms";

	private final GatewaySecurityProperties securityProperties;

	private final RemotePermService remotePermService;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	private final ConcurrentHashMap<String, PermissionCacheEntry> userPermissionCache = new ConcurrentHashMap<>();

	public PigAuthorizationGlobalFilter(GatewaySecurityProperties securityProperties,
			RemotePermService remotePermService) {
		this.securityProperties = securityProperties;
		this.remotePermService = remotePermService;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (!securityProperties.isAuthorizeEnabled()) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
		String client = request.getHeaders().getFirst(CLIENT);
		if (StrUtil.isBlank(client) || !client.equalsIgnoreCase(BMS)) {
			return chain.filter(exchange);
		}

		String username = exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR);
		if (StrUtil.isBlank(username)) {
			return chain.filter(exchange);
		}

		if (username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}

		String requestPath = request.getURI().getPath();
		HttpMethod httpMethod = request.getMethod();

		AuthorizeRule matchedRule = findMatchedRule(requestPath, httpMethod);
		if (matchedRule == null) {
			return chain.filter(exchange);
		}

		List<String> requiredPermissions = matchedRule.getPermissions();
		if (requiredPermissions == null || requiredPermissions.isEmpty()) {
			return chain.filter(exchange);
		}

		return getUserPermissions(username).flatMap(userPermissions -> {
			if (userPermissions == null) {
				return chain.filter(exchange);
			}
			boolean authorized = requiredPermissions.stream().anyMatch(userPermissions::contains);
			if (authorized) {
				return chain.filter(exchange);
			}
			log.warn("鉴权失败: 用户[{}]无权访问路径[{}], 需要权限[{}], 拥有权限[{}]",
					username, requestPath, requiredPermissions, userPermissions);
			return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
					"Access Denied: insufficient permissions", HttpStatus.FORBIDDEN);
		});
	}

	private AuthorizeRule findMatchedRule(String requestPath, HttpMethod httpMethod) {
		List<AuthorizeRule> rules = securityProperties.getAuthorizeRules();
		for (AuthorizeRule rule : rules) {
			if (pathMatcher.match(rule.getPath(), requestPath)) {
				if (StrUtil.isBlank(rule.getMethod())
						|| rule.getMethod().equalsIgnoreCase(httpMethod.name())) {
					return rule;
				}
			}
		}
		return null;
	}

	private Mono<Set<String>> getUserPermissions(String username) {
		PermissionCacheEntry cached = userPermissionCache.get(username);
		if (cached != null && !cached.isExpired()) {
			return Mono.just(cached.getPermissions());
		}

		return Mono.fromCallable(() -> {
			Map<String, Object> result = remotePermService.getUserPermissions(SecurityConstants.FROM_IN, username);
			Set<String> permissions = new HashSet<>();
			if (result != null) {
				Object permsObj = result.get("permissions");
				if (permsObj instanceof String[] perms) {
					Arrays.stream(perms).forEach(permissions::add);
				}
				else if (permsObj instanceof List<?> permsList) {
					permsList.forEach(p -> permissions.add(String.valueOf(p)));
				}
			}
			long ttl = securityProperties.getPermissionCacheTtlMs();
			userPermissionCache.put(username, new PermissionCacheEntry(permissions, System.currentTimeMillis() + ttl));
			return permissions;
		}).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
			log.error("获取用户[{}]权限信息失败, 放行请求由下游服务鉴权", username, ex);
			return Mono.just(null);
		});
	}

	public void evictCache(String username) {
		userPermissionCache.remove(username);
	}

	public void evictAllCache() {
		userPermissionCache.clear();
	}

	private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

	@Override
	public int getOrder() {
		return 0;
	}

	private static class PermissionCacheEntry {

		private final Set<String> permissions;

		private final long expiresAt;

		PermissionCacheEntry(Set<String> permissions, long expiresAt) {
			this.permissions = permissions;
			this.expiresAt = expiresAt;
		}

		Set<String> getPermissions() {
			return permissions;
		}

		boolean isExpired() {
			return System.currentTimeMillis() > expiresAt;
		}

	}

}
