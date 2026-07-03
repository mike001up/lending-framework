package com.pig4cloud.pig.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户锁定状态枚举
 *
 * @author lengleng
 */
@Getter
@RequiredArgsConstructor
public enum LockFlagEnum {
    /**
     * 正常状态
     */
    NORMAL("0", "正常"),

    /**
     * 锁定状态
     */
    LOCKED("1", "已锁定");

    private final String value;
    private final String description;

    /**
     * 根据value获取枚举
     *
     * @param value 枚举值
     * @return 枚举对象，未找到返回null
     */
    public static LockFlagEnum fromValue(String value) {
        for (LockFlagEnum flag : LockFlagEnum.values()) {
            if (flag.value.equals(value)) {
                return flag;
            }
        }
        return null;
    }

    /**
     * 判断是否为锁定状态
     *
     * @return true=已锁定
     */
    public boolean isLocked() {
        return this == LOCKED;
    }
}
