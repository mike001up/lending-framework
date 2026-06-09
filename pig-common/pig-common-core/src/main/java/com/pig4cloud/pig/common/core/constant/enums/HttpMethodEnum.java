package com.pig4cloud.pig.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpMethodEnum {

	GET("GET", "GET请求"),
	POST("POST", "POST请求"),
	PUT("PUT", "PUT请求"),
	DELETE("DELETE", "DELETE请求"),
	PATCH("PATCH", "PATCH请求"),
	HEAD("HEAD", "HEAD请求"),
	OPTIONS("OPTIONS", "OPTIONS请求");

	@EnumValue
	private final String value;

	private final String description;

}