package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.gateway.fegin.RemoteIPLimitService;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 下面会看到针对 code = 401 或 code = 403 进行处理
 * 当 code = 401 或者 code = 403 后台VUE 会立刻 跳转登录页进行 登录
 */
@Component
public class WhiteGlobalFilter implements GlobalFilter, Ordered {

    private final ObjectProvider<RemoteUserService> userServiceProvider;
    private final ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider;

    private final String KEY_QUERY_PARAM_USER_NAME = "username";
    private final String KEY_QUERY_PARAM_ADMIN = "admin";
    private final String TOKEN_DEL = "token_del";
    private final String CLIENT = "client";
    private final String BMS = "bms";
    private final String BASIC = "Basic";
    private final String AUTHORIZATION = "Authorization";
    private final String UNAUTHORIZED = "Unauthorized";
    private final String APP = "app";
    private static final List<String> URLS = new ArrayList<>();

    static {
        URLS.add("/auth/token/logout");
        URLS.add("/admin/user/details");
        URLS.add("/auth/code/image");
        URLS.add("/admin/sys-file");
        URLS.add("/admin/user/info");
        URLS.add("/admin/user/check");
        URLS.add("/auth/token/check_token");
    }

    private static final Logger log = LoggerFactory.getLogger(WhiteGlobalFilter.class);

    public WhiteGlobalFilter(ObjectProvider<RemoteUserService> userServiceProvider, ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider) {
        this.userServiceProvider = userServiceProvider;
        this.remoteIPLimitServiceProvider = remoteIPLimitServiceProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 白名单 URL 不拦截
        if (URLS.stream().anyMatch(path -> request.getURI().getPath().startsWith(path))) return chain.filter(exchange);
        String client = request.getHeaders().getFirst(CLIENT);
        //后台必须传一个参数(client:bms), 如果不传默认是 app,不拦截
        if (StrUtil.isBlank(client)) {
            return chain.filter(exchange);
        }
        return parseUserNameFromReq(request).flatMap(userName -> {
            if (StrUtil.isNotBlank(userName) && userName.equalsIgnoreCase(TOKEN_DEL)) {
                return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), UNAUTHORIZED, HttpStatus.FORBIDDEN);
            }
            // 不是 admin 就校验 IP
            if (StrUtil.isEmpty(userName) || !StrUtil.equals(userName, KEY_QUERY_PARAM_ADMIN)) {
                String remoteIP = IpUtil.getIpAddress(request);
                return validIp(request, remoteIP, exchange).then(chain.filter(exchange));
            }
            return chain.filter(exchange);
        });
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * 从请求里解析 username
     * 可能会调用远程服务，因此切换到 boundedElastic
     */
    private Mono<String> parseUserNameFromReq(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(AUTHORIZATION);
        String client = request.getHeaders().getFirst(CLIENT);
        // Basic 认证直接取 header
        if (!StringUtils.isEmpty(token) && token.startsWith(BASIC) && client.equalsIgnoreCase(BMS)) {
            String username = request.getHeaders().getFirst(KEY_QUERY_PARAM_USER_NAME);
            //针对后台移除token接口马上返回403的处理
            if (StringUtils.isEmpty(username)) {
                return Mono.just(TOKEN_DEL);
            }
            return Mono.just(username);
        }
        // 其他情况需要远程调用
        if (!StringUtils.isEmpty(token)) {
            RemoteUserService userService = userServiceProvider.getIfAvailable();
            if (userService != null) {
                return Mono.fromCallable(() -> {
                    Map<String, Object> user = userService.getUser(SecurityConstants.FROM_IN, token);
                    return user != null ? user.get(KEY_QUERY_PARAM_USER_NAME).toString() : null;
                }).subscribeOn(Schedulers.boundedElastic()).onErrorResume(ex -> {
                    if (ex instanceof FeignException fe && (fe.status() == 403 || fe.status() == 401)) {
                        log.warn("远程 userService 返回 401");
                        return Mono.just(TOKEN_DEL);
                    }
                    log.error("调用 userService.getUser 出错", ex);
                    return Mono.just(TOKEN_DEL);
                });
            }
        }
        return Mono.just(KEY_QUERY_PARAM_ADMIN);
    }

    /**
     * 校验 IP 是否在白名单里
     * 远程调用也丢到 boundedElastic
     */
    private Mono<Void> validIp(ServerHttpRequest request, String remoteIP, ServerWebExchange exchange) {
        String client = request.getHeaders().getFirst(CLIENT);
        if (StringUtils.isEmpty(client) || !client.equalsIgnoreCase(BMS)) {
            return Mono.empty();
        }
        RemoteIPLimitService ipLimitService = remoteIPLimitServiceProvider.getIfAvailable();
        if (ipLimitService != null) {
            return Mono.fromCallable(() -> ipLimitService.isValidIP(SecurityConstants.FROM_IN, remoteIP)).subscribeOn(Schedulers.boundedElastic()).flatMap(isValidIP -> {
                if (!isValidIP) {
                    log.warn("IP 校验失败，拒绝访问: {}", remoteIP);
                    // 1. 从请求头获取语言
                    String acceptLang = request.getHeaders().getFirst("Accept-Language");
                    if (acceptLang == null || acceptLang.isEmpty() || acceptLang.equals("zh-cn")) {
                        acceptLang = "zh_CN"; // 默认语言
                    }
                    return writeErrorResponse(exchange, HttpStatus.FORBIDDEN.value(), MsgUtils.getMessageByLang(ErrorCodes.IP_NOT_EXISTS_SYSTEM, acceptLang, remoteIP), HttpStatus.FORBIDDEN);
                }
                return Mono.empty();
            }).onErrorResume(ex -> {
                log.error("调用 ipLimitService.isValidIP 出错", ex);
                return writeErrorResponse(exchange, 1, "调用 ipLimitService.isValidIP 出错: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            });
        }
        return Mono.empty();
    }

    private Mono<Void> writeErrorResponse(ServerWebExchange exchange, int code, String msg, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg.replace("\"", "\\\""));
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

}
