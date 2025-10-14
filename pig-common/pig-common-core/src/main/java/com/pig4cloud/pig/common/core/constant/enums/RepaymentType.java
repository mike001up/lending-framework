package com.pig4cloud.pig.common.core.constant.enums;


import lombok.*;

@Getter
public enum  RepaymentType {


    FIXED_PRINCIPAL_INTEREST("FIXED_PRINCIPAL_INTEREST", "固定利息", "Fixed Interest"),
    EQUAL_PRINCIPAL_INTEREST("EQUAL_PRINCIPAL_INTEREST", "等额本息", "Equal Principal and Interest"),
    INTEREST_ONLY_LAST_PRINCIPAL("INTEREST_ONLY_LAST_PRINCIPAL", "先息后本", "Interest First, Principal Last"),
    EQUAL_PRINCIPAL("EQUAL_PRINCIPAL", "等额本金", "Equal Principal");

    /**
     * 类型
     */
    private final String type;

    /**
     * 中文描述
     */
    private final String description;

    /**
     * 英文描述
     */
    private final String englishDescription;

    RepaymentType(String type, String description, String englishDescription) {
        this.type = type;
        this.description = description;
        this.englishDescription = englishDescription;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    /**
     * 根据 type 获取枚举，未匹配则返回默认值（EQUAL_PRINCIPAL）
     */
    public static RepaymentType fromType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return EQUAL_PRINCIPAL; // 默认值
        }
        for (RepaymentType repaymentType : RepaymentType.values()) {
            if (repaymentType.getType().equalsIgnoreCase(type)) {
                return repaymentType;
            }
        }
        return EQUAL_PRINCIPAL; // 未匹配也返回默认值
    }

    /**
     * 根据 type 获取中文描述（找不到则返回默认描述）
     */
    public static String getDescriptionByType(String type) {
        return fromType(type).getDescription();
    }

    /**
     * 根据 type 获取英文描述（找不到则返回默认描述）
     */
    public static String getEnglishDescriptionByType(String type) {
        return fromType(type).getEnglishDescription();
    }
}
