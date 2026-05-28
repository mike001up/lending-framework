package com.pig4cloud.pig.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.gateway.filter.IpLimitGlobalFilter;
import com.pig4cloud.pig.gateway.filter.PigAuthenticationFilter;
import com.pig4cloud.pig.gateway.filter.PigAuthorizationGlobalFilter;
import com.pig4cloud.pig.gateway.filter.PigRequestGlobalFilter;
import com.pig4cloud.pig.gateway.fegin.RemoteIPLimitService;
import com.pig4cloud.pig.gateway.fegin.RemotePermService;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import com.pig4cloud.pig.gateway.handler.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

	@Bean
	public PigAuthenticationFilter pigAuthenticationFilter(ObjectProvider<RemoteUserService> userServiceProvider,
			GatewaySecurityProperties securityProperties) {
		return new PigAuthenticationFilter(userServiceProvider, securityProperties);
	}

	@Bean
	public IpLimitGlobalFilter ipLimitGlobalFilter(ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider) {
		return new IpLimitGlobalFilter(remoteIPLimitServiceProvider);
	}

	@Bean
	public PigAuthorizationGlobalFilter pigAuthorizationGlobalFilter(GatewaySecurityProperties securityProperties,
			RemotePermService remotePermService) {
		return new PigAuthorizationGlobalFilter(securityProperties, remotePermService);
	}

	@Bean
	public PigRequestGlobalFilter pigRequestGlobalFilter() {
		return new PigRequestGlobalFilter();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
	}

}
