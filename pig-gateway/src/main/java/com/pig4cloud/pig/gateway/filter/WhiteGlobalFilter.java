package com.pig4cloud.pig.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.IpUtil;
import com.pig4cloud.pig.gateway.fegin.RemoteIPLimitService;
import com.pig4cloud.pig.gateway.fegin.RemoteUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WhiteGlobalFilter implements GlobalFilter, Ordered {

    private final ObjectProvider<RemoteUserService> userServiceProvider;
    private final ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider;

    private final String KEY_QUERY_PARAM_USER_NAME = "username";

    private final String KEY_QUERY_PARAM_ADMIN = "admin";

    private static final Logger log = LoggerFactory.getLogger(WhiteGlobalFilter.class);

    public WhiteGlobalFilter(ObjectProvider<RemoteUserService> userServiceProvider, ObjectProvider<RemoteIPLimitService> remoteIPLimitServiceProvider) {
        this.userServiceProvider = userServiceProvider;
        this.remoteIPLimitServiceProvider = remoteIPLimitServiceProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // 打印请求日志
        log.info("请求路径: {}", request.getURI().getPath());
        log.info("请求方法: {}", request.getMethod());
        log.info("请求头: {}", headers);
        String userName = parseUserNameFromReq(request);
        if (StrUtil.isEmpty(userName) || !StrUtil.equals(userName, KEY_QUERY_PARAM_ADMIN)) {
            String remoteIP = IpUtil.getIpAddress(request);
            validIp(request, remoteIP, exchange);
        }
        return chain.filter(exchange);
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        return Mono.fromCallable(() -> {
//                    // 原来的逻辑不变
//                    String userName = parseUserNameFromReq(request);
//                    if (StrUtil.isEmpty(userName) || !StrUtil.equals(userName, KEY_QUERY_PARAM_ADMIN)) {
//                        String remoteIP = IpUtil.getIpAddress(request);
//                        validIp(request, remoteIP, exchange);
//                        log.info("remote ip: {}", remoteIP);
//                    }
//                    return true; // 只是为了返回一个对象
//                })
//                .subscribeOn(Schedulers.boundedElastic()) // 把阻塞调用丢到可阻塞线程池
//                .then(chain.filter(exchange)); // 执行下一个过滤器
//    }


    @Override
    public int getOrder() {
        return -1; // 数字越小优先级越高
    }

    private String parseUserNameFromReq(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst("Authorization");
        if (!StringUtils.isEmpty(token)) {
            try {
                RemoteUserService userService = userServiceProvider.getIfAvailable();
                if (userService != null) {
                    Map<String, Object> user = userService.getUser(SecurityConstants.FROM_IN, token);
                    return user.get(KEY_QUERY_PARAM_USER_NAME).toString();
                }
            } catch (Exception ex) {
                return null;
            }
        }
        return request.getQueryParams().getFirst(KEY_QUERY_PARAM_USER_NAME);
    }

    private void validIp(ServerHttpRequest request, String remoteIP, ServerWebExchange exchange) {
        String client = request.getHeaders().getFirst("client");
        if (StringUtils.isEmpty(client)) {
            return;
        }
        if (!StringUtils.isEmpty(client) && client.equalsIgnoreCase("bms")) {
            RemoteIPLimitService ipLimitService = remoteIPLimitServiceProvider.getIfAvailable();
            if (ipLimitService != null) {
                boolean isValidIP = ipLimitService.isValidIP(SecurityConstants.FROM_IN, remoteIP);
                if (!isValidIP) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid ip: " + remoteIP);
                }
            }
        }
    }
}
