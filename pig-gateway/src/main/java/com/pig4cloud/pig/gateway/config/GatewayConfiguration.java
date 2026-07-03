package com.pig4cloud.pig.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.gateway.filter.PigAuditLogFilter;
import com.pig4cloud.pig.gateway.filter.PigAuthenticationFilter;
import com.pig4cloud.pig.gateway.filter.PigAuthorizationFilter;
import com.pig4cloud.pig.gateway.filter.PigIpLimitFilter;
import com.pig4cloud.pig.gateway.filter.PigPathStripFilter;
import com.pig4cloud.pig.gateway.filter.PigRequestCleanFilter;
import com.pig4cloud.pig.gateway.filter.PigWhitelistFilter;
import com.pig4cloud.pig.admin.api.feign.RemoteIPLimitService;
import com.pig4cloud.pig.admin.api.feign.RemotePermService;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;
import com.pig4cloud.pig.gateway.handler.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

	@Bean
	public PigRequestCleanFilter requestCleanGlobalFilter() {
		return new PigRequestCleanFilter();
	}

	@Bean
	public PigWhitelistFilter whitelistGlobalFilter(GatewaySecurityProperties securityProperties) {
		return new PigWhitelistFilter(securityProperties);
	}

	@Bean
	public PigAuthenticationFilter pigAuthenticationFilter(ObjectProvider<RemoteTokenService> userServiceProvider) {
		return new PigAuthenticationFilter(userServiceProvider);
	}

	@Bean
	public PigIpLimitFilter ipLimitGlobalFilter(ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider,
			GatewaySecurityProperties securityProperties) {
		return new PigIpLimitFilter(remoteIPLimitServiceProvider, securityProperties);
	}

	@Bean
	public PigAuthorizationFilter pigAuthorizationFilter(
			GatewaySecurityProperties securityProperties,
			ObjectProvider<RemotePermService> remotePermServiceProvider,
			RedisTemplate<String, Object> redisTemplate,
			ObjectMapper objectMapper) {
		return new PigAuthorizationFilter(securityProperties, remotePermServiceProvider, redisTemplate, objectMapper);
	}

	@Bean
	public PigPathStripFilter pigRequestGlobalFilter() {
		return new PigPathStripFilter();
	}

	@Bean
	public PigAuditLogFilter gatewayAuditLogFilter(ObjectProvider<RemoteLogService> remoteLogServiceProvider) {
		return new PigAuditLogFilter(remoteLogServiceProvider);
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
