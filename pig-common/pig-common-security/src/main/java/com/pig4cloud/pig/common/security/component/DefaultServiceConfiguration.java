package com.pig4cloud.pig.common.security.component;

import com.pig4cloud.pig.common.security.feign.RemoteClientDetailsFeignClient;
import com.pig4cloud.pig.common.security.feign.RemoteUserInfoFeignClient;
import com.pig4cloud.pig.common.security.service.ClientDetailsService;
import com.pig4cloud.pig.common.security.service.UserInfoService;
import com.pig4cloud.pig.common.security.service.impl.DefaultClientDetailsService;
import com.pig4cloud.pig.common.security.service.impl.DefaultUserInfoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DefaultServiceConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserInfoService.class)
    public UserInfoService defaultUserInfoService(RemoteUserInfoFeignClient feignClient) {
        return new DefaultUserInfoService(feignClient);
    }

    @Bean
    @ConditionalOnMissingBean(ClientDetailsService.class)
    public ClientDetailsService defaultClientDetailsService(RemoteClientDetailsFeignClient feignClient) {
        return new DefaultClientDetailsService(feignClient);
    }
}