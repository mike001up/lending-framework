package com.pig4cloud.pig.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogTypeEnum {

	NORMAL("0", "正常日志"),

	ERROR("9", "错误日志");

	@EnumValue
	private final String value;

	private final String description;

}