package com.pig4cloud.pig.common.security.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;
import com.pig4cloud.pig.common.security.service.RemoteServiceAuthClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.common.constant.CommonConstants;
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
@ConditionalOnProperty(name = "pigx.service-auth.enabled", havingValue = "true")
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

		String serviceAuthHeader = request.getHeader(SecurityConstants.SERVICE_AUTHORIZATION);
		if (StrUtil.isBlank(serviceAuthHeader) || !serviceAuthHeader.startsWith("Bearer ")) {
			log.error("缺少有效服务令牌, API={}", request.getRequestURI());
			throw new AccessDeniedException("缺少服务令牌");
		}
		String token = serviceAuthHeader.substring(7);
		// 3. 解析服务令牌（调用认证服务）
		RemoteTokenService remoteTokenService = SpringUtil.getBean(RemoteTokenService.class);
		TokenPayloadDTO tokenPayload;
		try {
			R<TokenPayloadDTO> r = remoteTokenService.parsingToken(token);
			
			if (r == null || r.getCode() != 0 || r.getData() == null) {
				log.warn("服务令牌解析结果为空");
				throw new AccessDeniedException("无效的服务令牌");
			}
			tokenPayload = r.getData();
		} catch (Exception e) {
			log.error("服务令牌解析失败", e);
			throw new AccessDeniedException("无效的服务令牌");
		}
		if (tokenPayload.getGrantType() != GrantTypeEnum.CLIENT_CREDENTIALS) {
			log.warn("服务令牌授权类型不正确: {}", tokenPayload.getGrantType());
			throw new AccessDeniedException("不支持的服务令牌类型");
		}
		String providerServiceId = SpringUtil.getApplicationName();
		String requestPath = request.getRequestURI();

		LocalServiceAuthCache localCache = localCacheProvider.getIfAvailable();
		if (localCache == null)
			throw new AccessDeniedException("服务验证服务没有正确配置");
		boolean allowed = localCache.checkPermission(tokenPayload.getClientId(), providerServiceId, requestPath);
		if (!allowed) {
			log.warn("服务授权校验失败: caller={}, provider={}, path={}", tokenPayload.getClientId(), providerServiceId, requestPath);
			throw new AccessDeniedException("服务间调用授权被拒绝");
		}

		return joinPoint.proceed();
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 2;
	}

}
