package com.pig4cloud.pig.common.security.util;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * RegisteredClient 构建器
 */
public class RegisteredClientBuilder {

    private String clientId;
    private String clientSecret;
    private List<String> authorizedGrantTypes;
    private List<String> redirectUris;
    private List<String> scopes;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private Boolean autoapprove;

    private static final int DEFAULT_ACCESS_TOKEN_VALIDITY = 60 * 60 * 12;
    private static final int DEFAULT_REFRESH_TOKEN_VALIDITY = 60 * 60 * 24 * 30;

    // ----- 基础设置 -----
    public RegisteredClientBuilder clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public RegisteredClientBuilder clientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    // ----- 授权类型：支持 List<String> 和 String（逗号分隔） -----
    public RegisteredClientBuilder authorizedGrantTypes(List<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public RegisteredClientBuilder authorizedGrantTypes(String authorizedGrantTypesStr) {
        this.authorizedGrantTypes = splitString(authorizedGrantTypesStr);
        return this;
    }

    // ----- 回调地址：支持 List<String> 和 String（逗号分隔） -----
    public RegisteredClientBuilder redirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

    public RegisteredClientBuilder redirectUris(String redirectUrisStr) {
        this.redirectUris = splitString(redirectUrisStr);
        return this;
    }

    // ----- Scope：支持 List<String> 和 String（逗号分隔） -----
    public RegisteredClientBuilder scopes(List<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public RegisteredClientBuilder scopes(String scopesStr) {
        this.scopes = splitString(scopesStr);
        return this;
    }

    // ----- 有效期设置 -----
    public RegisteredClientBuilder accessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        return this;
    }

    public RegisteredClientBuilder refreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
        return this;
    }

    public RegisteredClientBuilder autoapprove(Boolean autoapprove) {
        this.autoapprove = autoapprove;
        return this;
    }

    // ----- 构建 RegisteredClient -----
    public RegisteredClient build() {
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("clientId must not be empty");
        }
        if (clientSecret == null) {
            clientSecret = "";
        }

        RegisteredClient.Builder builder = RegisteredClient.withId(clientId)
                .clientId(clientId)
                .clientSecret(SecurityConstants.NOOP + clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

        // 授权类型
        if (authorizedGrantTypes != null && !authorizedGrantTypes.isEmpty()) {
            authorizedGrantTypes.stream()
                    .filter(type -> type != null && !type.trim().isEmpty())
                    .forEach(type -> builder.authorizationGrantType(new AuthorizationGrantType(type.trim())));
        } else {
            // 默认授权类型
            builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN);
        }

        // 回调地址
        if (redirectUris != null) {
            redirectUris.stream()
                    .filter(uri -> uri != null && !uri.trim().isEmpty())
                    .forEach(builder::redirectUri);
        }

        // Scope
        if (scopes != null && !scopes.isEmpty()) {
            scopes.stream()
                    .filter(s -> s != null && !s.trim().isEmpty())
                    .forEach(builder::scope);
        } else {
            builder.scope("server");
        }

        int accessTokenTtl = Optional.ofNullable(accessTokenValidity).orElse(DEFAULT_ACCESS_TOKEN_VALIDITY);
        int refreshTokenTtl = Optional.ofNullable(refreshTokenValidity).orElse(DEFAULT_REFRESH_TOKEN_VALIDITY);

        builder.tokenSettings(TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                .accessTokenTimeToLive(Duration.ofSeconds(accessTokenTtl))
                .refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenTtl))
                .build());

        boolean requireConsent = !Boolean.TRUE.equals(autoapprove);
        builder.clientSettings(ClientSettings.builder()
                .requireAuthorizationConsent(requireConsent)
                .build());

        return builder.build();
    }

    // ----- 静态工厂方法（从实体直接构建） -----
    public static RegisteredClient fromSysOauthClientDetails(
            String clientId,
            String clientSecret,
            String authorizedGrantTypesStr,
            String redirectUrisStr,
            String scopesStr,
            Integer accessTokenValidity,
            Integer refreshTokenValidity,
            Boolean autoapprove) {

        return new RegisteredClientBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizedGrantTypes(authorizedGrantTypesStr)
                .redirectUris(redirectUrisStr)
                .scopes(scopesStr)
                .accessTokenValidity(accessTokenValidity)
                .refreshTokenValidity(refreshTokenValidity)
                .autoapprove(autoapprove)
                .build();
    }

    // ----- 辅助方法：分割逗号分隔字符串 -----
    private List<String> splitString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(str.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
