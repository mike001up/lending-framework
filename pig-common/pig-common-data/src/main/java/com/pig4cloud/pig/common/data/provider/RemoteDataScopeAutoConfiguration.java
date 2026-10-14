package com.pig4cloud.pig.common.data.provider;

import com.pig4cloud.pig.common.core.data.DataScopeProvider;
import com.pig4cloud.pig.common.data.feign.DataScopeFeignClient;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 远程数据权限自动配置
 * 满足以下条件时生效：
 *   1. classpath 有 DataScopeFeignClient
 *   2. 配置 data-scope.remote.enabled = true（默认 true）
 *   3. 未提供本地 DataScopeProvider 实现
 */
@AutoConfiguration
@ConditionalOnClass(DataScopeFeignClient.class)
@ConditionalOnProperty(
    name = "pigx.data-scope.remote.enabled",
    havingValue = "true",
    matchIfMissing = true   // 默认开启
)
public class RemoteDataScopeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DataScopeProvider.class)
    public RemoteDataScopeProvider remoteDataScopeProvider(DataScopeFeignClient feignClient) {
        return new RemoteDataScopeProvider(feignClient);
    }
}