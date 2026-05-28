package com.pig4cloud.pig.common.security.service;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ServiceTokenCacheService {

	private final RedisTemplate<String, String> redisTemplate;

	@Value("${pigx.service-token.client-id:service-client}")
	private String clientId;

	@Value("${pigx.service-token.client-secret:}")
	private String clientSecret;

	@Value("${pigx.service-token.token-uri:}")
	private String tokenUri;

	private volatile String cachedToken;

	private volatile Instant expiresAt;

	public ServiceTokenCacheService(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public String getOrRefreshToken() {
		if (cachedToken != null && expiresAt != null && expiresAt.isAfter(Instant.now())) {
			return cachedToken;
		}
		try {
			String redisKey = "service_token:" + clientId;
			String token = redisTemplate.opsForValue().get(redisKey);
			if (StrUtil.isNotBlank(token)) {
				this.cachedToken = token;
				this.expiresAt = Instant.now().plusSeconds(300);
				return token;
			}
			log.debug("服务Token未缓存或已过期, clientId: {}", clientId);
			return cachedToken;
		}
		catch (Exception ex) {
			log.error("获取服务Token失败", ex);
			return null;
		}
	}

	public void setToken(String token, long ttlSeconds) {
		this.cachedToken = token;
		this.expiresAt = Instant.now().plusSeconds(ttlSeconds);
		try {
			String redisKey = "service_token:" + clientId;
			redisTemplate.opsForValue().set(redisKey, token, ttlSeconds, TimeUnit.SECONDS);
		}
		catch (Exception ex) {
			log.warn("缓存服务Token到Redis失败", ex);
		}
	}

	public void evict() {
		this.cachedToken = null;
		this.expiresAt = null;
		try {
			String redisKey = "service_token:" + clientId;
			redisTemplate.delete(redisKey);
		}
		catch (Exception ex) {
			log.warn("清除服务Token缓存失败", ex);
		}
	}

}
