package com.pig4cloud.pig.common.security.component;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "pigx.service-token.enabled", havingValue = "true")
public class ServiceTokenAutoConfig implements WebMvcConfigurer {

	private final ObjectProvider<ServiceTokenValidationInterceptor> interceptorProvider;

	public ServiceTokenAutoConfig(ObjectProvider<ServiceTokenValidationInterceptor> interceptorProvider) {
		this.interceptorProvider = interceptorProvider;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		ServiceTokenValidationInterceptor interceptor = interceptorProvider.getIfAvailable();
		if (interceptor != null) {
			registry.addInterceptor(interceptor).order(Ordered.HIGHEST_PRECEDENCE);
		}
	}

}