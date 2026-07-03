// package com.pig4cloud.pig.common.security.feign;

// import cn.hutool.core.collection.CollUtil;
// import cn.hutool.core.util.StrUtil;
// import com.pig4cloud.pig.common.core.constant.SecurityConstants;
// import com.pig4cloud.pig.common.core.service.ServiceTokenCacheService;

// // import com.pig4cloud.pig.common.security.service.ServiceTokenCacheService;
// import feign.RequestInterceptor;
// import feign.RequestTemplate;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.ObjectProvider;

// import java.util.Collection;

// @Slf4j
// @RequiredArgsConstructor
// public class InternalFeignRequestInterceptor implements RequestInterceptor {

// 	private final ObjectProvider<ServiceTokenCacheService> tokenCacheServiceProvider;

// 	private final String serviceId;

// 	@Override
// 	public void apply(RequestTemplate template) {
//         log.debug("Feign url: {} {}, headers: {}", template.method(), template.url(), template.headers());
// 		template.header(SecurityConstants.FROM, SecurityConstants.FROM_IN);

// 		Collection<String> fromHeaders = template.headers().get(SecurityConstants.FROM);
// 		if (CollUtil.isNotEmpty(fromHeaders) && fromHeaders.contains(SecurityConstants.FROM_IN)) {
// 			ServiceTokenCacheService tokenCacheService = tokenCacheServiceProvider.getIfAvailable();
// 			if (tokenCacheService != null && !template.headers().containsKey(SecurityConstants.SERVICE_AUTHORIZATION)) {
// 				String serviceToken = tokenCacheService.getOrRefreshToken();
// 				if (StrUtil.isNotBlank(serviceToken)) {
// 					template.header(SecurityConstants.SERVICE_AUTHORIZATION, "Bearer " + serviceToken);
// 				}
// 			}
// 		}

// 		if (StrUtil.isNotBlank(serviceId)) {
// 			template.header(SecurityConstants.CALLER_SERVICE_ID, serviceId);
// 		}
// 	}

// }
