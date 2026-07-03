/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.common.core.constant;

/**
 * @author lengleng
 * @date 2019/2/1
 */
public interface SecurityConstants {

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";

	/**
	 * 前缀
	 */
	String PROJECT_PREFIX = "pig";

	/**
	 * 项目的license
	 */
	String PROJECT_LICENSE = "https://pig4cloud.com";

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 外部（网关进入）
	 */
	String FROM_OUT = "N";

	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 默认登录URL
	 */
	String OAUTH_TOKEN_URL = "/oauth2/token";

	/**
	 * grant_type
	 */
	String REFRESH_TOKEN = "refresh_token";

	/**
	 * password 模式
	 */
	String PASSWORD = "password";

	/**
	 * 授权码
	 */
	String AUTHORIZATION_CODE = "authorization_code";

	/**
	 * 手机号登录
	 */
	String MOBILE = "mobile";

	/**
	 * {bcrypt} 加密的特征码
	 */
	String BCRYPT = "{bcrypt}";

	/**
	 * {noop} 加密的特征码
	 */
	String NOOP = "{noop}";

	/**
	 * 用户名
	 */
	String USERNAME = "username";

	/**
	 * 用户信息
	 */
	String DETAILS_USER = "user_info";

	/**
	 * 用户ID
	 */
	String DETAILS_USER_ID = "user_id";

	/**
	 * 协议字段
	 */
	String DETAILS_LICENSE = "license";

	/**
	 * 验证码有效期,默认 60秒
	 */
	long CODE_TIME = 60;

	/**
	 * 验证码长度
	 */
	String CODE_SIZE = "6";

	/**
	 * 客户端模式
	 */
	String CLIENT_CREDENTIALS = "client_credentials";

	/**
	 * 客户端ID
	 */
	String CLIENT_ID = "clientId";

	/**
	 * 短信登录 参数名称
	 */
	String SMS_PARAMETER_NAME = "mobile";

	/**
	 * 授权码模式confirm
	 */
	String CUSTOM_CONSENT_PAGE_URI = "/oauth2/confirm_access";
	/**
	 * admin账号
	 */
	String ADMIN = "admin";
	/**
	 * 滞纳金参数(滞纳金收取比率)
	 */
	String LATE_PAYMENT_PENALTY_RATE = "LATE_PAYMENT_PENALTY_RATE";
	/**
	 * 豁免天数
	 */
	String EXEMPTION_DAYS = "EXEMPTION_DAYS";
	/**
	 * 币种
	 */
	String CURRENCY = "CURRENCY";

	String AUTHORIZATION = "Authorization";

	String BEARER = "Bearer ";

	String BASIC = "Basic";

	String SERVICE_AUTHORIZATION = "X-Service-Authorization";

	String CALLER_SERVICE_ID = "X-Caller-Service-Id";

	// String CALLER_SERVICE_ID_VALIDATED = "X-Caller-Service-Id-Validated";

	String TENANT_ID = "X-Tenant-Id";
//---------------------------------------TOKEN 解析
	String TOKEN_PAY_LOAD_SUB      = "sub";
    String TOKEN_PAY_LOAD_AUD      = "aud";
    String TOKEN_PAY_LOAD_ISS      = "iss";
    String TOKEN_PAY_LOAD_EXP      = "exp";
    String TOKEN_PAY_LOAD_NBF      = "nbf";
    String TOKEN_PAY_LOAD_IAT      = "iat";
    String TOKEN_PAY_LOAD_JTI      = "jti";
    String TOKEN_PAY_LOAD_CLIENTID = "clientId";
    String TOKEN_PAY_LOAD_SCOPE    = "scope";
    String TOKEN_PAY_LOAD_LICENSE  = "license";
    // String TOKEN_PAY_LOAD_USERINFO = "userInfo";
    String TOKEN_PAY_LOAD_USERID   = "user_id";
    String TOKEN_PAY_LOAD_USERNAME = "username";
	String TOKEN_PAY_LOAD_USER_INFO = "user_info";
	// 自定义请求头常量（用于向下游传递用户信息）
    String HEADER_USER_ID = "X-User-Id";
	String HEADER_USERNAME = "X-Username";
	String HEADER_PRINCIPAL = "X-Principal";
	String HEADER_TENANT_ID = "X-Tenant-Id";
	String HEADER_GRANT_TYPE = "X-Grant-Type";
	String HEADER_CLIENT_ID = "X-Client-Id";

	//----------------------------gateway
	String REDIS_KEY_IP_LIMIT_RESULT = "gateway:ip_limit:result:%s_%s";


}
