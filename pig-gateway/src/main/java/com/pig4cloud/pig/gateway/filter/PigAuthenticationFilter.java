package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.security.dto.TokenPayloadDTO;
import com.pig4cloud.pig.common.security.feign.RemoteTokenService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关统一认证过滤器
 * <p>
 * 职责：
 * 1. 只接受 Bearer Token
 * 2. 调用认证服务解析 Token，获取用户/客户端信息
 * 3. 将用户信息通过请求头传递给下游服务
 * 4. 移除原始 Authorization 头，下游服务不再解析 Token
 * 5. 任何认证失败都返回 HTTP 错误，不放行
 *
 * @author Nick
 */


@Slf4j
public class PigAuthenticationFilter implements GlobalFilter, Ordered {

    private final ObjectProvider<RemoteTokenService> tokenServiceProvider;
    //请求ID, 暂时没有实现，
    private String requestId = "";

    public PigAuthenticationFilter(ObjectProvider<RemoteTokenService> tokenServiceProvider) {
        this.tokenServiceProvider = tokenServiceProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 白名单直接放行
        if (Boolean.TRUE.equals(exchange.getAttribute(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR))) {
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION);

        // 2. 无 Token → 401
        if (StrUtil.isBlank(authHeader)) {
            log.error(CommonConstants.DEBUG_PREFIX + "请求未携带认证信息, path: {}", 
                                    requestId,
                                    request.getURI().getPath()
                                );
            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Missing Authorization header");
        }

        // 3. Basic 认证不支持 → 401
        if (authHeader.startsWith(SecurityConstants.BASIC)) {
            log.error(CommonConstants.DEBUG_PREFIX + "Basic 认证不被支持, path: {}", 
                                    requestId,
                                    request.getURI().getPath()
                                );
            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Basic authentication not supported");
        }

        // 4. 处理 Bearer Token
        if (authHeader.startsWith(SecurityConstants.BEARER)) {
            String token = authHeader.substring(SecurityConstants.BEARER.length()).trim();
            if (StrUtil.isBlank(token)) {
                log.error(CommonConstants.DEBUG_PREFIX + "Bearer Token 为空, path: {}", 
                                    requestId,
                                    request.getURI().getPath()
                                );
                return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid token");
            }

            RemoteTokenService tokenService = tokenServiceProvider.getIfAvailable();
            if (tokenService == null) {
                log.error(CommonConstants.DEBUG_PREFIX + "RemoteTokenService 不可用, path: {}", 
                                    requestId,
                                    request.getURI().getPath()
                                );
                return writeErrorResponse(exchange, HttpStatus.SERVICE_UNAVAILABLE, "Authentication service unavailable");
            }
            // 异步调用远程服务解析 Token
            return Mono.fromCallable(() -> {
						return tokenService.parsingToken(token);						
					}).subscribeOn(Schedulers.boundedElastic())
                    .flatMap(r -> {
                        // 5. Token 解析结果为空 → 401
                        if (r == null || r.getCode() !=0 || r.getData() == null) {
                            log.error(CommonConstants.DEBUG_PREFIX + "Token 解析结果为空, token: {}", 
                                    requestId,
                                    token
                                );
                            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid token");
                        }
                        TokenPayloadDTO tokenPayload =  r.getData();
                        // 6. grantType 必须存在且有效 → 否则 401
                        GrantTypeEnum grantType = tokenPayload.getGrantType();
                        if (grantType == null) {
                            log.error(CommonConstants.DEBUG_PREFIX + "Token 缺少 grantType, path: {}", 
                                    requestId,
                                    token
                                );
                            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid token payload");
                        }

                        if (grantType == GrantTypeEnum.PASSWORD) {
                            // 用户 Token 校验
                            if (StrUtil.isBlank(tokenPayload.getUsername()) && tokenPayload.getUserId() == null) {
                                log.error(CommonConstants.DEBUG_PREFIX + "用户 Token 缺少 username 和 userId, path: {}", 
                                    requestId,
                                    token
                                );
                                return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid user token");
                            }
                        } else if (grantType == GrantTypeEnum.CLIENT_CREDENTIALS) {
                            // 客户端 Token 校验
                            if (StrUtil.isBlank(tokenPayload.getClientId())) {
                                log.error(CommonConstants.DEBUG_PREFIX + "客户端 Token 缺少 clientId, path: {}", 
                                    requestId,
                                    request.getURI().getPath()
                                );
                                return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Invalid client token");
                            }
                            
                        } else {
                            // 其他授权类型暂不支持
                            log.error(CommonConstants.DEBUG_PREFIX + "不支持的授权类型: {}, path: {}", 
                                    requestId,
                                    grantType,
                                    request.getURI().getPath()
                                );
                            return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Unsupported grant type");
                        }

                        // 8. 构建新请求，将用户信息放入请求头，移除 Authorization
                        ServerHttpRequest mutatedRequest = request.mutate()
                                .headers(headers -> {
                                    // 身份标识（必有）
                                    headers.set(SecurityConstants.HEADER_PRINCIPAL, grantType == GrantTypeEnum.PASSWORD?tokenPayload.getUsername():tokenPayload.getClientId());
									headers.set(SecurityConstants.HEADER_USER_ID, grantType == GrantTypeEnum.PASSWORD?tokenPayload.getUserId().toString():null);
									headers.set(SecurityConstants.HEADER_USERNAME, grantType == GrantTypeEnum.PASSWORD?tokenPayload.getUsername():null);
									headers.set(SecurityConstants.HEADER_TENANT_ID, grantType == GrantTypeEnum.PASSWORD?tokenPayload.getTenantId() != null?tokenPayload.getTenantId().toString():null:null);
                                    // 授权类型（必有）
                                    headers.set(SecurityConstants.HEADER_GRANT_TYPE, grantType.name());
									headers.set(SecurityConstants.HEADER_CLIENT_ID, tokenPayload.getClientId());
                                    
                                    // 移除原始 Authorization，下游服务不再解析
                                    headers.remove(SecurityConstants.AUTHORIZATION);
                                }).build();

								log.debug(CommonConstants.DEBUG_PREFIX + "token details->{}", 
                                    requestId,
                                    JSONUtil.toJsonStr(tokenPayload)
                                );

                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    })
                    .onErrorResume(ex -> {
                        // 9. Feign 异常处理
                        if (ex instanceof FeignException fe) {
                            int status = fe.status();
                            if (status == 401 || status == 403) {
                                log.error(CommonConstants.DEBUG_PREFIX + "Token 验证失败, 远程服务返回 {}, path: {}", 
                                    requestId,
                                    status,
                                    request.getURI().getPath()
                                );
                                return writeErrorResponse(exchange, HttpStatus.valueOf(status), "Token validation failed");
                            }
                        }
                        log.error(CommonConstants.DEBUG_PREFIX + "调用 RemoteTokenService 异常: {}", 
                                    requestId,
                                    ex
                                );
                        return writeErrorResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Authentication service error");
                    });
        }

        // 10. 不支持的认证方式 → 401
        log.error(CommonConstants.DEBUG_PREFIX + "不支持的认证格式", 
                                    requestId
                                );
        return writeErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Unsupported authentication scheme");
    }

    @Override
    public int getOrder() {
        return GatewayAttrConstants.GATEWAY_ORDER_FILTER_AUTHENTICATION;
    }

    /**
     * 统一错误响应
     *
     * @param exchange 请求上下文
     * @param status   HTTP 状态码
     * @param message  错误消息
     * @return Mono<Void>
     */
    private Mono<Void> writeErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", status.value(), message.replace("\"", "\\\""));
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
