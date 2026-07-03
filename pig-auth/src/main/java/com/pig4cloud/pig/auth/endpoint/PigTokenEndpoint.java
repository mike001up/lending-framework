/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.auth.endpoint;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.TemporalAccessorUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.api.feign.RemoteClientDetailsService;
import com.pig4cloud.pig.admin.api.vo.TokenVo;
import com.pig4cloud.pig.auth.support.handler.PigAuthenticationFailureEventHandler;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.util.RetOps;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.common.feign.sentinel.handle.GlobalBizExceptionHandler;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.OAuth2EndpointUtils;
import com.pig4cloud.pig.common.security.util.OAuth2ErrorCodesExpand;
import com.pig4cloud.pig.common.security.util.OAuthClientException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2019/2/1 删除token端点
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PigTokenEndpoint {

	private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

	private final AuthenticationFailureHandler authenticationFailureHandler = new PigAuthenticationFailureEventHandler();

	private final OAuth2AuthorizationService authorizationService;

	private final RemoteClientDetailsService clientDetailsService;

	private final RedisTemplate<String, Object> redisTemplate;

	private final CacheManager cacheManager;

	/**
	 * 授权码模式：认证页面
	 * @param modelAndView
	 * @param error 表单登录失败处理回调的错误信息
	 * @return ModelAndView
	 */
	@GetMapping("/token/login")
	public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
		modelAndView.setViewName("ftl/login");
		modelAndView.addObject("error", error);
		return modelAndView;
	}

	/**
	 * 授权码模式：确认页面
	 * @return {@link ModelAndView }
	 */
	@GetMapping("/oauth2/confirm_access")
	public ModelAndView confirm(Principal principal, ModelAndView modelAndView,
			@RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
			@RequestParam(OAuth2ParameterNames.SCOPE) String scope,
			@RequestParam(OAuth2ParameterNames.STATE) String state) {
		SysOauthClientDetails clientDetails = RetOps.of(clientDetailsService.getClientDetailsById(clientId))
			.getData()
			.orElseThrow(() -> new OAuthClientException("clientId 不合法"));

		Set<String> authorizedScopes = StringUtils.commaDelimitedListToSet(clientDetails.getScope());
		modelAndView.addObject("clientId", clientId);
		modelAndView.addObject("state", state);
		modelAndView.addObject("scopeList", authorizedScopes);
		modelAndView.addObject("principalName", principal.getName());
		modelAndView.setViewName("ftl/confirm");
		return modelAndView;
	}

	/**
	 * 注销并删除令牌
	 * @param authHeader auth 标头
	 * @return {@link R }<{@link Boolean }>
	 */
	@DeleteMapping("/token/logout")
	public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return R.ok();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.TokenType.BEARER.getValue(), StrUtil.EMPTY).trim();
		return removeToken(tokenValue);
	}

	/**
	 * 检查令牌
	 * @param token 令 牌
	 * @param response 响应
	 * @param request 请求
	 */
	@SneakyThrows
	@GetMapping("/token/check_token")
	public void checkToken(String token, HttpServletResponse response, HttpServletRequest request) {
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

		if (StrUtil.isBlank(token)) {
			httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.TOKEN_MISSING));
			return;
		}
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);

		// 如果令牌不存在 返回401
		if (authorization == null || authorization.getAccessToken() == null) {
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.INVALID_BEARER_TOKEN));
			return;
		}

		Map<String, Object> claims = authorization.getAccessToken().getClaims();
		OAuth2AccessTokenResponse sendAccessTokenResponse = OAuth2EndpointUtils.sendAccessTokenResponse(authorization,
				claims);
		this.accessTokenHttpResponseConverter.write(sendAccessTokenResponse, MediaType.APPLICATION_JSON, httpResponse);
	}

	/**
	 * 删除令牌
	 * @param token 令 牌
	 * @return {@link R }<{@link Boolean }>
	 */
	@Inner
	@DeleteMapping("/token/remove/{token}")
	public R<Boolean> removeToken(@PathVariable("token") String token) {
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
		if (authorization == null) {
			return R.ok();
		}

		OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
		if (accessToken == null || StrUtil.isBlank(accessToken.getToken().getTokenValue())) {
			return R.ok();
		}
		// 清空用户信息（立即删除）
		cacheManager.getCache(CacheConstants.USER_DETAILS).evictIfPresent(CacheConstants.USER_DETAILS_KEY_PREFIX + authorization.getPrincipalName());
		// 清空access token
		authorizationService.remove(authorization);
		// 处理自定义退出事件，保存相关日志
		SpringContextHolder.publishEvent(new LogoutSuccessEvent(new PreAuthenticatedAuthenticationToken(
				authorization.getPrincipalName(), authorization.getRegisteredClientId())));
		return R.ok();
	}


	@Inner
	@RequireServiceAuth
	@DeleteMapping("/token/removeByUsername/{username}")
	public R<Boolean> removeTokenByUsername(@PathVariable("username") String username) {
		String key = String.format("%s::*", CacheConstants.PROJECT_OAUTH_ACCESS);
		Set<String> keys = redisTemplate.keys(key);
		if (CollUtil.isEmpty(keys)) {
			return R.ok();
		}

		redisTemplate.setValueSerializer(RedisSerializer.java());
		List<Object> authorizations = redisTemplate.opsForValue().multiGet(keys);
		if (CollUtil.isEmpty(authorizations)) {
			return R.ok();
		}

		authorizations.stream()
			.filter(Objects::nonNull)
			.map(obj -> (OAuth2Authorization) obj)
			.filter(auth -> username.equals(auth.getPrincipalName()))
			.forEach(auth -> {
				authorizationService.remove(auth);
				SpringContextHolder.publishEvent(new LogoutSuccessEvent(new PreAuthenticatedAuthenticationToken(
						auth.getPrincipalName(), auth.getRegisteredClientId())));
			});

		cacheManager.getCache(CacheConstants.USER_DETAILS).evictIfPresent(CacheConstants.USER_DETAILS_KEY_PREFIX + username);
		return R.ok();
	}

	@SneakyThrows
	@GetMapping("/token/parsing")
	public R<TokenPayloadDTO> parsingToken(@RequestParam("token") String token) {
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
		if (authorization == null) {
			return R.failed("无效token");
		}
		Map<String, Object> claims = authorization.getAccessToken().getClaims();
		log.debug("user: {}", claims.keySet());
		// 直接从 claims 中取值并转换类型，所有转换内联处理
		TokenPayloadDTO dto = TokenPayloadDTO.builder()
            .sub(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_SUB)).map(Object::toString).orElse(null))
            .aud(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_AUD)).map(Object::toString).orElse(null))
            .iss(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_ISS)).map(Object::toString).orElse(null))
            .exp(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_EXP))
                    .map(v -> v instanceof Instant ? ((Instant) v) : null)
                    .orElse(null))
            .nbf(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_NBF))
                    .map(v -> v instanceof Instant ? ((Instant) v) : null)
                    .orElse(null))
            .iat(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_IAT))
                    .map(v -> v instanceof Instant ? ((Instant) v) : null)
                    .orElse(null))
            .jti(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_JTI)).map(Object::toString).orElse(null))
            .clientId(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_CLIENTID)).map(Object::toString).orElse(null))
            .scope(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_SCOPE)).map(Object::toString).orElse(null))
            .license(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_LICENSE)).map(Object::toString).orElse(null))            
            .userId(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_USERID))
                    .map(v -> v instanceof Number ? ((Number) v).longValue() : Long.valueOf(v.toString()))
                    .orElse(null))
			.username(Optional.ofNullable(claims.get(SecurityConstants.TOKEN_PAY_LOAD_USERNAME)).map(Object::toString).orElse(null))
			.tenantId(claims.get(SecurityConstants.TOKEN_PAY_LOAD_USER_INFO) == null?null:((PigUser)claims.get(SecurityConstants.TOKEN_PAY_LOAD_USER_INFO)).getTenantId())
			.grantType(claims.get(SecurityConstants.TOKEN_PAY_LOAD_USERID) != null ? GrantTypeEnum.PASSWORD : GrantTypeEnum.CLIENT_CREDENTIALS)
            .build();
    	return R.ok(dto);
	}

	/**
	 * 令牌列表
	 * @param params 参数
	 * @return {@link R }<{@link Page }>
	 */
	@Inner
	@PostMapping("/token/page")
	public R<Page> tokenList(@RequestBody Map<String, Object> params) {
		// 根据分页参数获取对应数据
		String key = String.format("%s::*", CacheConstants.PROJECT_OAUTH_ACCESS);
		int current = MapUtil.getInt(params, CommonConstants.CURRENT);
		int size = MapUtil.getInt(params, CommonConstants.SIZE);
		Set<String> keys = redisTemplate.keys(key);
		List<String> pages = keys.stream().skip((current - 1) * size).limit(size).collect(Collectors.toList());
		Page result = new Page(current, size);

		List<TokenVo> tokenVoList = redisTemplate.opsForValue().multiGet(pages).stream().map(obj -> {
			OAuth2Authorization authorization = (OAuth2Authorization) obj;
			TokenVo tokenVo = new TokenVo();
			tokenVo.setClientId(authorization.getRegisteredClientId());
			tokenVo.setId(authorization.getId());
			tokenVo.setUsername(authorization.getPrincipalName());
			OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
			tokenVo.setAccessToken(accessToken.getToken().getTokenValue());

			String expiresAt = TemporalAccessorUtil.format(accessToken.getToken().getExpiresAt(),
					DatePattern.NORM_DATETIME_PATTERN);
			tokenVo.setExpiresAt(expiresAt);

			String issuedAt = TemporalAccessorUtil.format(accessToken.getToken().getIssuedAt(),
					DatePattern.NORM_DATETIME_PATTERN);
			tokenVo.setIssuedAt(issuedAt);
			return tokenVo;
		}).collect(Collectors.toList());
		result.setRecords(tokenVoList);
		result.setTotal(keys.size());
		return R.ok(result);
	}

}
