package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class WhitelistGlobalFilter implements GlobalFilter, Ordered {

	private final GatewaySecurityProperties securityProperties;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public WhitelistGlobalFilter(GatewaySecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String requestPath = exchange.getRequest().getURI().getPath();

		for (String ignoreUrl : securityProperties.getIgnoreUrls()) {
			if (pathMatcher.match(ignoreUrl, requestPath)) {
				exchange.getAttributes().put(GatewayAttrConstants.GATEWAY_WHITELIST_ATTR, Boolean.TRUE);
				log.debug("请求路径[{}]匹配白名单[{}], 标记为白名单请求", requestPath, ignoreUrl);
				break;
			}
		}

		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -2;
	}

}