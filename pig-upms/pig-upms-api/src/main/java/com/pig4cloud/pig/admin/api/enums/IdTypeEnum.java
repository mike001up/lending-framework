package com.pig4cloud.pig.admin.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 证件类型枚举
 * 对应实名认证支持的三种证件类型
 *
 * @author nick
 * @date 2026/07/05
 */
@Getter
@AllArgsConstructor
public enum IdTypeEnum {

    /**
     * 身份证
     */
    ID_CARD("1", "身份证"),

    /**
     * 驾驶证
     */
    DRIVING_LICENSE("2", "驾驶证"),

    /**
     * 护照
     */
    PASSPORT("3", "护照");

    /**
     * 证件类型代码（用于存储到数据库及前后端交互）
     */
    private final String code;

    /**
     * 证件类型中文描述（用于前端展示）
     */
    private final String desc;

    /**
     * 根据 code 获取对应的枚举实例
     *
     * @param code 证件类型代码
     * @return 匹配的枚举实例，未匹配到返回 null
     */
    public static IdTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (IdTypeEnum type : IdTypeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 判断给定的 code 是否有效
     *
     * @param code 证件类型代码
     * @return true-有效，false-无效
     */
    public static boolean isValid(String code) {
        return getByCode(code) != null;
    }

    /**
     * 获取所有证件类型的 code 列表（用于前端下拉框或校验）
     *
     * @return code 列表
     */
    public static java.util.List<String> getCodes() {
        return Arrays.stream(IdTypeEnum.values())
                .map(IdTypeEnum::getCode)
                .toList();
    }
}