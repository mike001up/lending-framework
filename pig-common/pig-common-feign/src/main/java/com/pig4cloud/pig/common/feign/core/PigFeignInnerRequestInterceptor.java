package com.pig4cloud.pig.common.feign.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;
import com.pig4cloud.pig.common.feign.annotation.NoToken;
// import com.pig4cloud.pig.common.security.service.ServiceTokenCacheService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class PigFeignInnerRequestInterceptor implements RequestInterceptor, Ordered {

	private final ObjectProvider<ServiceTokenCacheService> tokenCacheServiceProvider;

	public PigFeignInnerRequestInterceptor(ObjectProvider<ServiceTokenCacheService> tokenCacheServiceProvider) {
		this.tokenCacheServiceProvider = tokenCacheServiceProvider;
	}

	@Override
	public void apply(RequestTemplate template) {
		Method method = template.methodMetadata().method();
		
		InternalFeign internalFeign = method.getAnnotation(InternalFeign.class);
		if (internalFeign == null) {
			internalFeign = method.getDeclaringClass().getAnnotation(InternalFeign.class);
		}
		if(template.url().contains("/permission/authorize-rules")){
			log.debug("Method declaring class: {}", method.getDeclaringClass().getName());
			log.debug("Method name: {}", method.getName());
			log.debug("Method annotations: {}", Arrays.toString(method.getAnnotations()));
			log.debug("Is bridge? {}", method.isBridge());
			log.debug("Is synthetic? {}", method.isSynthetic());
		}
		if (internalFeign != null) {
			template.header(SecurityConstants.FROM, SecurityConstants.FROM_IN);

			ServiceTokenCacheService tokenCacheService = tokenCacheServiceProvider.getIfAvailable();
			if (tokenCacheService != null
					&& !template.headers().containsKey(SecurityConstants.SERVICE_AUTHORIZATION)) {
				String serviceToken = tokenCacheService.getOrRefreshToken();
				if (StrUtil.isNotBlank(serviceToken)) {
					template.header(SecurityConstants.SERVICE_AUTHORIZATION, "Bearer " + serviceToken);
				}
			}
		}
		log.debug("Feign request: {} {}, headers: {}", template.method(), template.url(), template.headers());
	}

	// private String resolveClientFromCurrentRequest() {
	// 	try {
	// 		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	// 		if (attrs != null) {
	// 			return attrs.getRequest().getHeader(CommonConstants.CLIENT);
	// 		}
	// 	}
	// 	catch (Exception ex) {
	// 		log.warn("获取当前请求 client header 失败", ex);
	// 	}
	// 	return null;
	// }

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
