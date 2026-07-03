package com.pig4cloud.pig.common.security.service;
import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;
import com.pig4cloud.pig.common.security.dto.OAuth2AccessTokenDTO;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;

// import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;  // 导入接口
import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.client.mqttv3.internal.websocket.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ServiceTokenCacheServiceImpl implements ServiceTokenCacheService {  // 实现接口

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${pigx.service-token.client-id}")
    private String clientId;

    @Value("${pigx.service-token.client-secret}")
    private String clientSecret;

    @Value("${pigx.service-token.token-uri}")
    private String tokenUri;

    @Value("${pigx.service-token.scope}")
    private String scope;

    @Autowired
    private RemoteTokenService remoteTokenService; 

    public ServiceTokenCacheServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取客户端token
     * 1,首先从
     */
    @Override
    public String getOrRefreshToken() {
        // 1. 尝试从缓存（Redis）获取
        String cachedToken = getCachedToken();
        if (StrUtil.isNotBlank(cachedToken)) {
            return cachedToken;
        }

        // 2. 缓存未命中，调用认证服务获取新 Token
        String newToken = fetchTokenFromAuthServer();
        if (StrUtil.isNotBlank(newToken)) {
            // 3. 缓存新 Token（并设置过期时间）
            cacheToken(newToken);
            return newToken;
        }
        return null;
    }

    private String fetchTokenFromAuthServer() {
        try {
            // 注意：这里需要携带 Basic Auth 头，可以在 Feign 拦截器中统一添加
            // 或者通过 @RequestHeader 显式传递
            String basicBear = "Basic "+ Base64.encode(clientId+":"+clientSecret);
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("grant_type", GrantTypeEnum.CLIENT_CREDENTIALS.getVal());
            formData.add("scope", scope);
            remoteTokenService.clientToken(formData, basicBear);
            OAuth2AccessTokenDTO response = remoteTokenService.clientToken(formData, basicBear);
            if (response != null && StrUtil.isNotBlank(response.getAccessToken())) {
                log.debug("成功获取客户端 Token, expires_in: {}", response.getExpiresIn());
                return response.getAccessToken();
            }
        } catch (Exception e) {
            log.error("获取客户端 Token 失败", e);
        }
        return null;
    }

    // 缓存相关方法（可复用 RedisTemplate）
    private String getCachedToken() {
        // 从自定义 key 读取，例如 "service_token:" + clientId
        return redisTemplate.opsForValue().get("service_token:" + clientId);
    }

    private void cacheToken(String token) {
        // 使用过期时间（可从响应中获取 expires_in）
        redisTemplate.opsForValue().set("service_token:" + clientId, token, 3600, TimeUnit.SECONDS);
    }
}