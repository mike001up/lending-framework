package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.junit.Assert.fail;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PigAuthorizationFilter implements GlobalFilter, Ordered {

    private final GatewaySecurityProperties securityProperties;
    private final ObjectProvider<RemotePermService> remotePermServiceProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // 本地缓存：用户权限
    private final Cache<String, Set<String>> permissionCache;
    // 本地缓存：授权规则（使用 Caffeine 作为二级缓存）
    private final Cache<String, List<SysPermission>> ruleCache;

    // Redis 缓存键
    // private static final String REDIS_RULE_KEY = CacheConstants.GATEWAY_PREFIX + "authorize_rules";
    // private static final String REDIS_USER_PERM_PREFIX = CacheConstants.GATEWAY_PREFIX + "user_permissions:";

    // 规则加载状态（用于判断是否需要刷新）
    private volatile long authorizeRulesLastLoadTime = 0;

    public PigAuthorizationFilter(GatewaySecurityProperties securityProperties,
                                  ObjectProvider<RemotePermService> remotePermServiceProvider,
                                  RedisTemplate<String, Object> redisTemplate,
                                  ObjectMapper objectMapper) {
        this.securityProperties = securityProperties;
        this.remotePermServiceProvider = remotePermServiceProvider;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;

        this.permissionCache = Caffeine.newBuilder()
                .expireAfterWrite(securityProperties.getPermissionCacheTtlMs(), TimeUnit.MILLISECONDS)
                .maximumSize(securityProperties.getPermissionCacheMaxSize())
                .build();

        this.ruleCache = Caffeine.newBuilder()
                .expireAfterWrite(securityProperties.getPermissionCacheTtlMs(), TimeUnit.MILLISECONDS)
                .maximumSize(1)  // 只缓存一份规则
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

        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        HttpMethod httpMethod = request.getMethod();

        String username = request.getHeaders().getFirst(SecurityConstants.HEADER_USERNAME);
        if (StrUtil.isBlank(username)) {
            log.warn("鉴权失败: 请求缺少用户标识, path: {}", requestPath);
            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "缺少用户身份信息");
        }

        // 获取授权规则（两级缓存）
        return getAuthorizeRules().flatMap(r -> {
            if(r == null || r.getCode() != 0){
                log.warn("鉴权失败: 获取权限错误, path: {}", requestPath);
                return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "鉴权失败: 获取权限错误");
            }

            SysPermission matchedRule = findMatchedRule(r.getData(), requestPath, httpMethod);
			//当前请求资源不在权限列表中
            if (matchedRule == null) {
                return chain.filter(exchange);
            }

            String requiredPermission = matchedRule.getPermission();
            if (StrUtil.isBlank(requiredPermission)) {
                log.warn("权限规则缺少 permission 字段, path: {}", requestPath);
                return chain.filter(exchange);
            }

            return getUserPermissions(username).flatMap(userPermissions -> {
                if (userPermissions == null) {
                    log.warn("鉴权失败: 获取用户[{}]权限信息异常, 拒绝访问路径[{}]", username, requestPath);
                    return writeErrorResponse(exchange, HttpStatus.FORBIDDEN, "权限验证服务异常");
                }

                if (userPermissions.contains(requiredPermission)) {
                    return chain.filter(exchange);
                }

                log.warn("鉴权失败: 用户[{}]无权访问路径[{}], 需要权限[{}], 拥有权限[{}]",
                        username, requestPath, requiredPermission, userPermissions);
                return writeErrorResponse(exchange, HttpStatus.FORBIDDEN, "权限不足");
            });
        });
    }

    /**
     * 获取授权规则（两级缓存：本地 -> Redis -> Feign）
     */
    private Mono<R<List<SysPermission>>> getAuthorizeRules() {
        // 1. 先查本地缓存
        List<SysPermission> localRules = ruleCache.getIfPresent(CacheConstants.CACHE_KEY_UPMS_PERMISSION);
        if (localRules != null) {
            return Mono.just(R.ok(localRules));
        }

        // 2. 查 Redis
        return Mono.fromCallable(() -> {
            Object cached = redisTemplate.opsForValue().get(CacheConstants.CACHE_KEY_UPMS_PERMISSION);
			try {
				if (cached != null) {
					// 反序列化为 List<SysPermission>					
					String json = objectMapper.writeValueAsString(cached);
					CollectionType listType = objectMapper.getTypeFactory()
								.constructCollectionType(List.class, SysPermission.class);
					List<SysPermission> rules = objectMapper.readValue(json, listType);
					if (rules != null) {
						// 更新本地缓存
						ruleCache.put(CacheConstants.CACHE_KEY_UPMS_PERMISSION, rules);
						
					}
					return R.ok(rules);             
				}
			} catch (JsonProcessingException e) {
                log.warn("反序列化 Redis 权限规则失败", e);
            }
            List<SysPermission> empList = new ArrayList<>();
            return R.failed(empList);  
        }).subscribeOn(Schedulers.boundedElastic())
        .flatMap(r -> {
			//rules == null 只在发生异常情况下发生
            if (r != null && r.getCode() == 0) {
                return Mono.just(r);
            }
            // 3. Redis 未命中，调用 Feign 获取
            return fetchRulesFromFeign();
        });
    }

    /**
     * 从 Feign 加载规则，并写入两级缓存
     */
    private Mono<R<List<SysPermission>>> fetchRulesFromFeign() {
        return Mono.fromCallable(() -> {
            RemotePermService remotePermService = remotePermServiceProvider.getIfAvailable();
            if (remotePermService == null) {
				List<SysPermission> emptyList = new ArrayList<>();
                return R.failed(emptyList);
            }
            R<List<SysPermission>> result = remotePermService.getAuthorizeRules();
            return result;
        }).subscribeOn(Schedulers.boundedElastic())
		.flatMap(r -> {
			//错误情况下不需要本地缓存
            if(r == null || r.getCode() != 0 || r.getData() == null){
				List<SysPermission> resultEmpty = new ArrayList<>();
				return Mono.just(r);
			}
            List<SysPermission> rules = r.getData();
            // 按路径长度降序排序
            rules.sort((a, b) -> Integer.compare(b.getPath().length(), a.getPath().length()));           
            // 写入本地缓存
            ruleCache.put(CacheConstants.CACHE_KEY_UPMS_PERMISSION, rules);

			return Mono.just(R.ok(rules));
        })
        .onErrorResume(ex -> {
            log.error("加载授权规则失败", ex);
            // 降级：返回空列表（会导致所有请求被拒绝，但安全）
			List<SysPermission> rules = new ArrayList<>();
            return Mono.just(R.failed(rules));
        });
    }

    /**
     * 获取用户权限（两级缓存：本地 -> Redis -> Feign）
     */
    private Mono<Set<String>> getUserPermissions(String username) {
        // 1. 查本地缓存
        Set<String> localPerms = permissionCache.getIfPresent(username);
        if (localPerms != null) {
            return Mono.just(localPerms);
        }

        // 2. 查 Redis
        return Mono.fromCallable(() -> {
            Object cached = redisTemplate.opsForValue().get(String.format(CacheConstants.CACHE_KEY_UPMS_PERMISSION_USER, username));
            if (cached != null) {
                try {
                    String json = objectMapper.writeValueAsString(cached);
                    Set<String> perms = objectMapper.readValue(json, objectMapper.getTypeFactory()
                            .constructCollectionType(Set.class, String.class));
                    if (perms != null) {
                        // 更新本地缓存
                        permissionCache.put(String.format(CacheConstants.CACHE_KEY_UPMS_PERMISSION_USER, username), perms);
                        return perms;
                    }
                } catch (JsonProcessingException e) {
                    log.warn("反序列化 Redis 用户权限失败, username={}", username, e);
                }
            }
            return null;
        }).subscribeOn(Schedulers.boundedElastic())
        .flatMap(perms -> {
            if (perms != null) {
                return Mono.just(perms);
            }
            // 3. Redis 未命中，调用 Feign
            return fetchUserPermissionsFromFeign(username);
        });
    }

    /**
     * 从 Feign 加载用户权限，并写入两级缓存
     */
    private Mono<Set<String>> fetchUserPermissionsFromFeign(String username) {
        return Mono.fromCallable(() -> {
            RemotePermService remotePermService = remotePermServiceProvider.getIfAvailable();
            if (remotePermService == null) {
                return null;
            }
            R<UserInfo> result = remotePermService.getUserInfo(username);
            if (result == null || result.getCode() != 0 || result.getData() == null) {
                return null;
            }
            String[] perms = result.getData().getPermissions();
            Set<String> permissions = (perms != null && perms.length > 0)
                    ? new HashSet<>(Arrays.asList(perms))
                    : Collections.emptySet();

            // 写入本地缓存
            permissionCache.put(username, permissions);
            return permissions;
        }).subscribeOn(Schedulers.boundedElastic())
        .onErrorResume(ex -> {
            log.error("获取用户[{}]权限信息失败", username, ex);
            return Mono.just(null);
        });
    }

    /**
     * 查找匹配的权限规则
     */
    private SysPermission findMatchedRule(List<SysPermission> rules, String requestPath, HttpMethod httpMethod) {
        for (SysPermission rule : rules) {
            if (pathMatcher.match(rule.getPath(), requestPath)) {
                if (StrUtil.isBlank(rule.getMethod()) || rule.getMethod().equalsIgnoreCase(httpMethod.name())) {
                    return rule;
                }
            }
        }
        return null;
    }

    /**
     * 手动清除指定用户的权限缓存（本地 + Redis）
     */
    public void evictCache(String username) {
        permissionCache.invalidate(username);
        // String redisKey = REDIS_USER_PERM_PREFIX + username;
        // redisTemplate.delete(redisKey);
        // log.info("已清除用户[{}]的权限缓存（本地+Redis）", username);
    }

    /**
     * 清除所有缓存（规则缓存 + 所有用户权限缓存）
     */
    public void evictAllCache() {
        // 清空本地缓存
        permissionCache.invalidateAll();
        ruleCache.invalidateAll();
        // 清空 Redis 规则缓存
        // redisTemplate.delete(REDIS_RULE_KEY);
        // 清空所有用户权限缓存（使用通配符扫描删除，建议使用 Redis SCAN 或存储时使用 hash 结构）
        // 为了简化，这里仅删除规则，用户权限按需单独删除或设置较短的过期时间
        // 也可以通过管理接口批量清除
        log.info("已清除所有缓存（本地+Redis）");
    }

    private Mono<Void> writeErrorResponse(ServerWebExchange exchange, HttpStatus status, String msg) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", status.value(), msg.replace("\"", "\\\""));
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return GatewayAttrConstants.GATEWAY_ORDER_FILTER_AUTHORIZATION;
    }
}