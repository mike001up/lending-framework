package com.pig4cloud.pig.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户认证状态枚举
 *
 * @author lengleng
 */
@Getter
@RequiredArgsConstructor
public enum CertificationStatusEnum {
    /**
     * 未认证（未提交）
     */
    NOT_CERTIFIED("0", "未认证"),

    /**
     * 待审核（已提交，等待审核）
     */
    PENDING("1", "待审核"),

    /**
     * 已认证（审核通过）
     */
    APPROVED("2", "已认证"),

    /**
     * 审核拒绝（不通过）
     */
    REJECTED("3", "审核拒绝");

    private final String value;
    private final String description;

    /**
     * 根据 value 获取枚举
     */
    public static CertificationStatusEnum fromValue(String value) {
        for (CertificationStatusEnum status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 是否可编辑/重新提交（待审核和已认证时不可编辑）
     */
    public boolean isEditable() {
        return this == NOT_CERTIFIED || this == REJECTED;
    }

    /**
     * 是否已认证通过
     */
    public boolean isApproved() {
        return this == APPROVED;
    }
}
