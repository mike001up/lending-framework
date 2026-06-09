package com.pig4cloud.pig.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TenantStatusEnum {

	ENABLED("ENABLED", "启用"),
	DISABLED("DISABLED", "禁用");

	@EnumValue
	private final String value;

	private final String description;

}