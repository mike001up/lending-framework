package com.pig4cloud.pig.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.security.service.PigUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeaderSecurityContextRepository implements SecurityContextRepository {

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        String principal = request.getHeader(SecurityConstants.HEADER_PRINCIPAL);
        String userId = request.getHeader(SecurityConstants.HEADER_USER_ID);
        String username = request.getHeader(SecurityConstants.HEADER_USERNAME);
        String tenantId = request.getHeader(SecurityConstants.HEADER_TENANT_ID);

        if (StrUtil.isNotBlank(principal) || StrUtil.isNotBlank(userId)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            // 根据业务需要可添加权限
            PigUser pigUser = new PigUser(
                StrUtil.isBlank(userId) ? null : Long.valueOf(userId),
                username,
                "",
                null,
                StrUtil.isBlank(tenantId) ? null : Long.valueOf(tenantId),
                true, true, true, true,
                authorities
            );
            Authentication authentication =
                new UsernamePasswordAuthenticationToken(pigUser, null, authorities);
            context.setAuthentication(authentication);
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request,
                            HttpServletResponse response) {
        // 不需要存储，因为认证信息由网关提供，服务端无需保存
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        // 检查请求头中是否包含用户标识，决定是否加载上下文
        return StrUtil.isNotBlank(request.getHeader(SecurityConstants.HEADER_PRINCIPAL))
            || StrUtil.isNotBlank(request.getHeader(SecurityConstants.HEADER_USER_ID));
    }
}