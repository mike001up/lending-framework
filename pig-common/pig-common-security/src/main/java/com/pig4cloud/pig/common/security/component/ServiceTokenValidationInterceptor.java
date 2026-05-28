package com.pig4cloud.pig.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@ConditionalOnProperty(name = "pigx.service-token.enabled", havingValue = "true")
public class ServiceTokenValidationInterceptor implements HandlerInterceptor {

	private final ObjectProvider<JwtDecoder> jwtDecoderProvider;

	public ServiceTokenValidationInterceptor(ObjectProvider<JwtDecoder> jwtDecoderProvider) {
		this.jwtDecoderProvider = jwtDecoderProvider;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String serviceToken = extractServiceToken(request);
		if (StrUtil.isBlank(serviceToken)) {
			String from = request.getHeader(SecurityConstants.FROM);
			if (SecurityConstants.FROM_IN.equals(from)) {
				log.warn("内部调用缺少服务Token, URI: {}, Caller: {}",
						request.getRequestURI(), request.getHeader(SecurityConstants.CALLER_SERVICE_ID));
			}
			return true;
		}

		JwtDecoder jwtDecoder = jwtDecoderProvider.getIfAvailable();
		if (jwtDecoder == null) {
			log.debug("JwtDecoder未配置, 跳过服务Token校验");
			return true;
		}

		try {
			Jwt jwt = jwtDecoder.decode(serviceToken);
			String callerServiceId = jwt.getClaimAsString("sub");
			if (StrUtil.isNotBlank(callerServiceId)) {
				request.setAttribute(SecurityConstants.CALLER_SERVICE_ID_VALIDATED, callerServiceId);
			}
			return true;
		}
		catch (JwtException ex) {
			log.warn("服务Token校验失败: {}", ex.getMessage());
			return true;
		}
	}

	private String extractServiceToken(HttpServletRequest request) {
		String header = request.getHeader(SecurityConstants.SERVICE_AUTHORIZATION);
		if (StrUtil.isNotBlank(header) && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}

}
