package com.pig4cloud.pig.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.common.security.service.RemoteServiceAuthClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "pigx.service-auth.aop-enabled", havingValue = "true")
public class ServiceAuthAspect implements Ordered {

	private final ObjectProvider<RemoteServiceAuthClient> remoteAuthClientProvider;

	private final ObjectProvider<LocalServiceAuthCache> localCacheProvider;

	public ServiceAuthAspect(ObjectProvider<RemoteServiceAuthClient> remoteAuthClientProvider,
			ObjectProvider<LocalServiceAuthCache> localCacheProvider) {
		this.remoteAuthClientProvider = remoteAuthClientProvider;
		this.localCacheProvider = localCacheProvider;
	}

	@Around("@annotation(com.pig4cloud.pig.common.security.annotation.RequireServiceAuth) || "
			+ "@within(com.pig4cloud.pig.common.security.annotation.RequireServiceAuth)")
	public Object checkServiceAuth(ProceedingJoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return joinPoint.proceed();
		}
		HttpServletRequest request = attributes.getRequest();

		String callerServiceId = (String) request.getAttribute(SecurityConstants.CALLER_SERVICE_ID_VALIDATED);
		if (StrUtil.isBlank(callerServiceId)) {
			callerServiceId = request.getHeader(SecurityConstants.CALLER_SERVICE_ID);
		}
		if (StrUtil.isBlank(callerServiceId)) {
			throw new AccessDeniedException("无法识别调用方服务");
		}

		String providerServiceId = SpringContextHolder.getApplicationName();
		String requestPath = request.getRequestURI();

		LocalServiceAuthCache localCache = localCacheProvider.getIfAvailable();
		if (localCache != null) {
			boolean allowed = localCache.checkPermission(callerServiceId, providerServiceId, requestPath);
			if (!allowed) {
				log.warn("服务授权校验失败: caller={}, provider={}, path={}", callerServiceId, providerServiceId, requestPath);
				throw new AccessDeniedException("服务间调用授权被拒绝");
			}
		}

		return joinPoint.proceed();
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 2;
	}

}
