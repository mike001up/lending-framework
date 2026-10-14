package com.pig4cloud.pig.common.data.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.pig4cloud.pig.common.core.data.DataScopeProvider;
import com.pig4cloud.pig.common.data.aspect.DataScopeAspect;
import com.pig4cloud.pig.common.data.handler.DataPermissionHandler;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 数据权限自动配置
 * 所有引入 pig-common-data 的模块自动生效
 */
@AutoConfiguration
@ConditionalOnClass(MybatisPlusInterceptor.class)
public class DataScopeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DataPermissionHandler dataPermissionHandler() {
        return new DataPermissionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public DataScopeAspect dataScopeAspect(ObjectProvider<DataScopeProvider> providerProvider) {
        return new DataScopeAspect(providerProvider);
    }

    /**
     * 暴露数据权限拦截器 Bean，由 pig-common-mybatis 的 MybatisPlusConfig 决定是否追加
     */
    @Bean
    @ConditionalOnMissingBean
    public DataPermissionInterceptor dataPermissionInterceptor(
            DataPermissionHandler handler) {
        DataPermissionInterceptor interceptor = new DataPermissionInterceptor(handler);
        return interceptor;
    }
}