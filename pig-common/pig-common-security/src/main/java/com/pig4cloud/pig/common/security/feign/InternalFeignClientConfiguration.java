// package com.pig4cloud.pig.common.security.feign;

// // import com.pig4cloud.pig.common.security.service.ServiceTokenCacheService;
// import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;
// import feign.RequestInterceptor;
// import org.springframework.beans.factory.ObjectProvider;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class InternalFeignClientConfiguration {

// 	@Bean
// 	public RequestInterceptor internalFeignRequestInterceptor(
// 			ObjectProvider<ServiceTokenCacheService> tokenCacheServiceProvider,
// 			@Value("${spring.application.name:}") String serviceId) {
// 		return new InternalFeignRequestInterceptor(tokenCacheServiceProvider, serviceId);
// 	}

// }
