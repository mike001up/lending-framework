package com.pig4cloud.pig.common.core.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IsDelEnum {

	NO("NO", "正常"),
	YES("YES", "已删除");

	@EnumValue
	private final String value;

	private final String description;

}