package com.pig4cloud.pig.admin.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.pig4cloud.pig.common.security.component.HeaderSecurityContextRepository;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public HttpFirewall allowSemicolonFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowBackSlash(true);
        return firewall;
    }

        /**
     * 创建用户上下文过滤器 Bean
     * 仅当配置 pigx.security.user-context-enabled=true 时生效（默认开启）
     */
    @Bean
    // @ConditionalOnProperty(name = "pigx.security.user-context-enabled", havingValue = "true", matchIfMissing = true)
    public HeaderSecurityContextRepository headerSecurityContextRepository() {
        return new HeaderSecurityContextRepository();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           HeaderSecurityContextRepository headerRepository) throws Exception {
        // 放行所有请求（网关已鉴权）
        http.securityContext(securityContext -> securityContext
                .securityContextRepository(headerRepository))
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable())
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable())
            .anonymous(anonymous -> anonymous.disable());

        return http.build();
    }

    // 通过 WebSecurityCustomizer 设置防火墙
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.httpFirewall(allowSemicolonFirewall());
    }
}