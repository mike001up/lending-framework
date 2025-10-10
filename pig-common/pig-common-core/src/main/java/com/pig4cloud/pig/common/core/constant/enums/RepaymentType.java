package com.pig4cloud.pig.common.core.constant.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  RepaymentType {

    FIXED_PRINCIPAL_INTEREST("FIXED_PRINCIPAL_INTEREST", "固定本金 + 固定利息"),
    EQUAL_PRINCIPAL_INTEREST("EQUAL_PRINCIPAL_INTEREST", "等额本息"),
    INTEREST_ONLY_LAST_PRINCIPAL("INTEREST_ONLY_LAST_PRINCIPAL", "先息后本");


    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;
}
