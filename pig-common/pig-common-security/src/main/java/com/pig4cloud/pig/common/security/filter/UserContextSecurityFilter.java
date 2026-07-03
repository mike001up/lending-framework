// package com.pig4cloud.pig.common.security.filter;

// import cn.hutool.core.util.StrUtil;
// import lombok.extern.slf4j.Slf4j;

// import org.apache.commons.lang3.StringUtils;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.pig4cloud.pig.common.core.constant.SecurityConstants;
// import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
// import com.pig4cloud.pig.common.security.service.PigUser;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// /**
//  * 从网关传递的 X-* 头中提取用户信息，填充到 Spring Security 上下文
//  * 使得业务代码可以通过 SecurityUtils.getUser() 获取当前用户
//  */
// @Slf4j
// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE + 1) // 高优先级，但稍晚于 UserContextFilter（如果存在）
// @ConditionalOnProperty(name = "pigx.security.user-context-enabled", havingValue = "true", matchIfMissing = true)
// public class UserContextSecurityFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain chain) throws ServletException, IOException {
//         try {
//             // 1. 从请求头提取用户信息
//             String principal = request.getHeader(SecurityConstants.HEADER_PRINCIPAL);
//             String userId = request.getHeader(SecurityConstants.HEADER_USER_ID);
//             String username = request.getHeader(SecurityConstants.HEADER_USERNAME);
//             String tenantId = request.getHeader(SecurityConstants.HEADER_TENANT_ID);
//             String grantType = request.getHeader(SecurityConstants.HEADER_GRANT_TYPE);
//             String clientId = request.getHeader(SecurityConstants.HEADER_CLIENT_ID);            


//             // 2. 如果有 principal 或 userId，说明已认证，构建 Authentication
//             if (StrUtil.isNotBlank(principal) || StrUtil.isNotBlank(userId)) {
//                 // 构建权限列表（可根据需要从请求头中解析 authorities，或使用空列表）
//                 List<GrantedAuthority> authorities = new ArrayList<>();
                                
//                 PigUser pigUser = new PigUser(StringUtils.isEmpty(userId)?null:Long.valueOf(userId), 
//                     username,
//                     "", 
//                     null, 
//                     StringUtils.isEmpty(tenantId)?null:Long.valueOf(tenantId), 
//                     true, 
//                     true, 
//                     true,
//                     true, 
//                     authorities);

//                 // 创建 Authentication 对象
//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(pigUser, null, authorities);
//                 // authentication.setAuthenticated(true);
//                 // 填充到 SecurityContextHolder
//                 SecurityContextHolder.getContext().setAuthentication(authentication);

//                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                 log.info("✅ 设置后验证: {}", auth);

//                 // 同时存入 UserContext（如果下游 Feign 调用需要）
//                 FeignRequestContext.setClientId(clientId);

//                 log.debug("SecurityContext 已填充用户: {}", principal);
//             } else {
//                 // 无用户信息，确保 SecurityContext 为空（匿名）
//                 SecurityContextHolder.clearContext();
//                 FeignRequestContext.clear();
//             }

//             chain.doFilter(request, response);
//         } finally {
//             // 注意：不要在 finally 中清理 SecurityContextHolder，因为后续业务代码可能还需要。
//             // 清理工作由框架在请求结束后自动执行（通过 SecurityContextHolderFilter 或我们自己注册的过滤器）。
//             // 但 UserContext 需在请求结束时清理，以避免线程池污染。
//             // 由于我们在 finally 块中清理 UserContext 可能过早（业务代码还在执行），因此不在这里清理。
//             // 我们将清理放在一个更低优先级的过滤器中，或使用 @WebFilter 的 afterCompletion。
//         }
//     }
// }