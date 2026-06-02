package com.pig4cloud.pig.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "gateway.security")
public class GatewaySecurityProperties {

	private List<String> ignoreUrls = new ArrayList<>(Arrays.asList(
			"/auth/token/check_token"
	));

	private boolean authorizeEnabled = true;

	private long permissionCacheTtlMs = 300000;

	private long ipCacheTtlSeconds = 300;

	private long ipCacheMaxSize = 10000;

	private long permissionCacheMaxSize = 1000;

}
