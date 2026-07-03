package com.pig4cloud.pig.gateway.filter;

public interface GatewayAttrConstants {

	// String GATEWAY_USERNAME_ATTR = "gateway_username";

	String GATEWAY_USERID_ATTR = "gateway_userid";

	// String GATEWAY_CLIENT_ATTR = "gateway_client";

	String GATEWAY_WHITELIST_ATTR = "gateway_whitelist";

	String GATEWAY_TENANT_ATTR = "gateway_tenant";

	Integer GATEWAY_ORDER_FILTER_CLEAN = -3;

	Integer GATEWAY_ORDER_FILTER_WHITE_LIST = -2;

	Integer GATEWAY_ORDER_FILTER_AUTHENTICATION = -1;

	Integer GATEWAY_ORDER_FILTER_AUTHORIZATION = 1;

	Integer GATEWAY_ORDER_FILTER_IP_LIMIT = 0;

	Integer GATEWAY_ORDER_FILTER_PATH_STRIP = 10;

	Integer GATEWAY_ORDER_FILTER_LOG = 5;

}
