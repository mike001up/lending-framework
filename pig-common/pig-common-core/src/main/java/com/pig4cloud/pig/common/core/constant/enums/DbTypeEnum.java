package com.pig4cloud.pig.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 数据库类型枚举
 *
 * @author lengleng
 */
@Getter
@RequiredArgsConstructor
public enum DbTypeEnum {
    /**
     * 正常状态
     */
    MYSQL("0", "mysql 数据库"),

    /**
     * 锁定状态
     */
    ORACLE("1", "oracle 数据库");

    private final String value;
    private final String description;

    /**
     * 根据value获取枚举
     *
     * @param value 枚举值
     * @return 枚举对象，未找到返回null
     */
    public static DbTypeEnum fromValue(String value) {
        for (DbTypeEnum flag : DbTypeEnum.values()) {
            if (flag.value.equals(value)) {
                return flag;
            }
        }
        return null;
    }
}
