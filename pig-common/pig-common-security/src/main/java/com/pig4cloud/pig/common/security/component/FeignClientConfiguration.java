package com.pig4cloud.pig.common.security.component;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.pig4cloud.pig.common.security.feign")
public class FeignClientConfiguration {
}
