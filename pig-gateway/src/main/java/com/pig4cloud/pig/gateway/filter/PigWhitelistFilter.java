package com.pig4cloud.pig.gateway.filter;

import com.pig4cloud.pig.gateway.config.GatewaySecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求uri 如果是白名单列表中，那么次请求链路不需要进行安全请求验证
 * 1，忽略token 验证
 * 2，忽略授权验证
 * 
 */
@Slf4j
public class PigWhitelistFilter implements GlobalFilter, Ordered {

	private final GatewaySecurityProperties securityProperties;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public PigWhitelistFilter(GatewaySecurityProperties securityProperties) {
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
		return GatewayAttrConstants.GATEWAY_ORDER_FILTER_WHITE_LIST;
	}

}