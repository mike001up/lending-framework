package com.pig4cloud.pig.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IpActionEnum {

	DENY("DENY", "黑名单"),
	ALLOW("ALLOW", "白名单");

	@EnumValue
	private final String value;

	private final String description;

}