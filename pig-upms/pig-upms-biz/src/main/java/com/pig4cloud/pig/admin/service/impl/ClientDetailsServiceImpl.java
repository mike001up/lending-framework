package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.convertor.ClientConverter;
import com.pig4cloud.pig.admin.service.SysOauthClientDetailsService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.ClientRegisteredDTO;
import com.pig4cloud.pig.common.security.service.ClientDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

// @Primary
@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final SysOauthClientDetailsService clientDetailsService;

    private final ClientConverter clientConverter;

    private final static int refreshTokenValiditySeconds = 60 * 60 * 24 * 30;
    private final static int accessTokenValiditySeconds = 60 * 60 * 12;

    @Override
    public R<ClientRegisteredDTO> loadClientByClientId(String clientId) {
        // 查询客户端实体（需要根据你的实际查询方法调整）
        SysOauthClientDetails clientDetails = clientDetailsService.getOne(
            Wrappers.<SysOauthClientDetails>lambdaQuery()
                .eq(SysOauthClientDetails::getClientId, clientId)
        );
        if (clientDetails == null) {
            // 返回 null 或抛出异常，这里沿用原逻辑抛出 OAuth2 异常
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(
                new OAuth2Error("客户端不存在"), null);
        }
        return R.ok(clientConverter.toClientRegisteredDTO(clientDetails));
        // // ---------- 以下完全复制原转换逻辑 ----------
        // RegisteredClient.Builder builder = RegisteredClient.withId(clientDetails.getClientId())
        //     .clientId(clientDetails.getClientId())
        //     .clientSecret(SecurityConstants.NOOP + clientDetails.getClientSecret())
        //     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        // // for (String authorizedGrantType : clientDetails.getAuthorizedGrantTypes()) {
        // //     builder.authorizationGrantType(new AuthorizationGrantType(authorizedGrantType));
        // // }
        // Optional.ofNullable(clientDetails.getAuthorizedGrantTypes())
        //     .ifPresent(authorizedGrantTypes -> Arrays.stream(authorizedGrantTypes.split(StrUtil.COMMA))
        //         .filter(StrUtil::isNotBlank)
        //         .map(String::trim)
        //         .map(AuthorizationGrantType::new)  
        //         .forEach(builder::authorizationGrantType));

        // Optional.ofNullable(clientDetails.getWebServerRedirectUri())
        //     .ifPresent(redirectUri -> Arrays.stream(redirectUri.split(StrUtil.COMMA))
        //         .filter(StrUtil::isNotBlank)
        //         .forEach(builder::redirectUri));

        // Optional.ofNullable(clientDetails.getScope())
        //     .ifPresent(scope -> Arrays.stream(scope.split(StrUtil.COMMA))
        //         .filter(StrUtil::isNotBlank)
        //         .forEach(builder::scope));

        // builder.tokenSettings(TokenSettings.builder()
        //     .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
        //     .accessTokenTimeToLive(Duration.ofSeconds(
        //         Optional.ofNullable(clientDetails.getAccessTokenValidity()).orElse(accessTokenValiditySeconds)))
        //     .refreshTokenTimeToLive(Duration.ofSeconds(
        //         Optional.ofNullable(clientDetails.getRefreshTokenValidity()).orElse(refreshTokenValiditySeconds)))
        //     .build());

        // builder.clientSettings(ClientSettings.builder()
        //     .requireAuthorizationConsent(!BooleanUtil.toBoolean(clientDetails.getAutoapprove()))
        //     .build());
        // RegisteredClient client = builder.build();
        // return R.ok(client);
    }
}