package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties.AuthorizeRule;
import com.pig4cloud.pig.gateway.fegin.RemotePermService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
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
import java.util.concurrent.TimeUnit;

@Slf4j
public class PigAuthorizationGlobalFilter implements GlobalFilter, Ordered {

	private final GatewaySecurityProperties securityProperties;

	private final RemotePermService remotePermService;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	private final Cache<String, Set<String>> permissionCache;

	public PigAuthorizationGlobalFilter(GatewaySecurityProperties securityProperties,
			RemotePermService remotePermService) {
		this.securityProperties = securityProperties;
		this.remotePermService = remotePermService;
		this.permissionCache = Caffeine.newBuilder()
			.expireAfterWrite(securityProperties.getPermissionCacheTtlMs(), TimeUnit.MILLISECONDS)
			.maximumSize(securityProperties.getPermissionCacheMaxSize())
			.build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (!securityProperties.isAuthorizeEnabled()) {
			return chain.filter(exchange);
		}

		if (Boolean.TRUE.equals(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR))) {
			return chain.filter(exchange);
		}


		String username = exchange.getAttribute(GatewayAttrConstants.GATEWAY_USERNAME_ATTR);


		if (username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
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
				log.warn("鉴权失败: 无法获取用户[{}]权限信息, 拒绝访问路径[{}]", username, requestPath);
				return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
						"Access Denied: unable to verify permissions", HttpStatus.FORBIDDEN);
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
		Set<String> cached = permissionCache.getIfPresent(username);
		if (cached != null) {
			return Mono.just(cached);
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
			if (!permissions.isEmpty()) {
				permissionCache.put(username, permissions);
			}
			return permissions.isEmpty() ? null : permissions;
		}).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
			log.error("获取用户[{}]权限信息失败, 拒绝请求", username, ex);
			return Mono.just(null);
		});
	}

	public void evictCache(String username) {
		permissionCache.invalidate(username);
	}

	public void evictAllCache() {
		permissionCache.invalidateAll();
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
		return 1;
	}

}
