package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import com.pig4cloud.pig.admin.api.feign.RemotePermService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PigAuthorizationGlobalFilter implements GlobalFilter, Ordered {

	private final GatewaySecurityProperties securityProperties;

	private final ObjectProvider<RemotePermService> remotePermServiceProvider;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	private final Cache<String, Set<String>> permissionCache;

	private volatile List<SysPermission> authorizeRules = Collections.emptyList();

	private volatile long authorizeRulesLastLoadTime = 0;

	public PigAuthorizationGlobalFilter(GatewaySecurityProperties securityProperties,
			ObjectProvider<RemotePermService> remotePermServiceProvider) {
		this.securityProperties = securityProperties;
		this.remotePermServiceProvider = remotePermServiceProvider;
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

		if (StrUtil.isBlank(username)) {
			log.warn("鉴权失败: 请求缺少用户标识, path: {}", exchange.getRequest().getURI().getPath());
			return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
					HttpStatus.UNAUTHORIZED);
		}

		if (username.equalsIgnoreCase(SecurityConstants.ADMIN)) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
		String requestPath = request.getURI().getPath();
		HttpMethod httpMethod = request.getMethod();

		return getOrRefreshAuthorizeRules().flatMap(rules -> {
			SysPermission matchedRule = findMatchedRule(rules, requestPath, httpMethod);
			if (matchedRule == null) {
				return chain.filter(exchange);
			}

			String requiredPermission = matchedRule.getPermission();
			if (StrUtil.isBlank(requiredPermission)) {
				return chain.filter(exchange);
			}

			return getUserPermissions(username).flatMap(userPermissions -> {
				if (userPermissions == null) {
					log.warn("鉴权失败: 获取用户[{}]权限信息异常, 拒绝访问路径[{}]", username, requestPath);
					return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
							"Access Denied: unable to verify permissions", HttpStatus.FORBIDDEN);
				}
				if (userPermissions.contains(requiredPermission)) {
					return chain.filter(exchange);
				}
				log.warn("鉴权失败: 用户[{}]无权访问路径[{}], 需要权限[{}], 拥有权限[{}]",
						username, requestPath, requiredPermission, userPermissions);
				return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(),
						"Access Denied: insufficient permissions", HttpStatus.FORBIDDEN);
			});
		});
	}

	private SysPermission findMatchedRule(List<SysPermission> rules, String requestPath, HttpMethod httpMethod) {
		for (SysPermission rule : rules) {
			if (pathMatcher.match(rule.getPath(), requestPath)) {
				if (StrUtil.isBlank(rule.getMethod())
						|| rule.getMethod().equalsIgnoreCase(httpMethod.name())) {
					return rule;
				}
			}
		}
		return null;
	}

	private Mono<List<SysPermission>> getOrRefreshAuthorizeRules() {
		long now = System.currentTimeMillis();
		if (now - authorizeRulesLastLoadTime < securityProperties.getPermissionCacheTtlMs()
				&& !authorizeRules.isEmpty()) {
			return Mono.just(authorizeRules);
		}

		return Mono.fromCallable(() -> {
			RemotePermService remotePermService = remotePermServiceProvider.getIfAvailable();
			if (remotePermService == null) {
				return authorizeRules;
			}
			R<List<SysPermission>> result = remotePermService.getAuthorizeRules();
			if (result != null && result.getData() != null) {
				authorizeRules = result.getData();
				authorizeRulesLastLoadTime = System.currentTimeMillis();
				permissionCache.invalidateAll();
			}
			return authorizeRules;
		}).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
			log.error("获取授权规则失败, 使用缓存规则", ex);
			return Mono.just(authorizeRules);
		});
	}

	private Mono<Set<String>> getUserPermissions(String username) {
		Set<String> cached = permissionCache.getIfPresent(username);
		if (cached != null) {
			return Mono.just(cached);
		}

		return Mono.fromCallable(() -> {
			RemotePermService remotePermService = remotePermServiceProvider.getIfAvailable();
			if (remotePermService == null) {
				return null;
			}
			R<UserInfo> result = remotePermService.getUserInfo(username);
			Set<String> permissions = new HashSet<>();
			if (result != null && result.getData() != null) {
				String[] perms = result.getData().getPermissions();
				if (perms != null) {
					Arrays.stream(perms).forEach(permissions::add);
				}
			}
			if (!permissions.isEmpty()) {
				permissionCache.put(username, permissions);
			}
			return permissions;
		}).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
			log.error("获取用户[{}]权限信息失败", username, ex);
			return Mono.just(null);
		});
	}

	public void evictCache(String username) {
		permissionCache.invalidate(username);
	}

	public void evictAllCache() {
		permissionCache.invalidateAll();
		authorizeRulesLastLoadTime = 0;
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
