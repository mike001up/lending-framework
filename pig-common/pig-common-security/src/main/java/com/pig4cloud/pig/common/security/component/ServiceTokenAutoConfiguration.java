package com.pig4cloud.pig.common.security.component;

import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;
import com.pig4cloud.pig.common.security.service.ServiceTokenCacheServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisTemplate.class) // 仅当 RedisTemplate 存在时才生效
@RequiredArgsConstructor
public class ServiceTokenAutoConfiguration {

    private final RedisTemplate<String, String> redisTemplate;

    @Bean
    public ServiceTokenCacheService serviceTokenCacheService() {
        return new ServiceTokenCacheServiceImpl(redisTemplate);
    }
}
