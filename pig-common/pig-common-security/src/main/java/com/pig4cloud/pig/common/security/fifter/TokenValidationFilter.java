package com.pig4cloud.pig.common.security.fifter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Token 校验过滤器(校验token是否过期或被删除)
 */
@Slf4j
public class TokenValidationFilter extends OncePerRequestFilter {

    private final OAuth2AuthorizationService authorizationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TokenValidationFilter(OAuth2AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("TokenValidationFilter URL: {}", request.getRequestURI());
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
            String token = authorizationHeader.substring(7); // 去掉 "Bearer "
            OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
            if (authorization == null) {
                sendUnauthorized(response, "Token is invalid or expired");
                return;
            }
        }
        // token 有效，继续执行
        filterChain.doFilter(request, response);
    }

    /**
     * 返回 401 JSON 响应
     */
    private void sendUnauthorized(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, Object> body = Map.of("code", 401, "data", null, "msg", msg);
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
