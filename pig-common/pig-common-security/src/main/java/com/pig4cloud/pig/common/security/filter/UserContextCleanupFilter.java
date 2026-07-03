// package com.pig4cloud.pig.common.security.filter;

// import java.io.IOException;

// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.core.Ordered;

// // @Component
// // @Order(Ordered.LOWEST_PRECEDENCE)
// // @ConditionalOnProperty(name = "pigx.security.user-context-enabled", havingValue = "true", matchIfMissing = true)
// public class UserContextCleanupFilter extends OncePerRequestFilter {
//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain chain) throws ServletException, IOException {
//         try {
//             chain.doFilter(request, response);
//         } finally {
//             FeignRequestContext.clear();
//         }
//     }
// }