package com.pig4cloud.pig.common.security.dto;

import java.time.Instant;
import java.util.List;

import com.pig4cloud.pig.common.core.constant.enums.LockFlagEnum;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户数据传输对象（Builder 模式）
 *
 * @author Nick
 * @date 2026-06-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PigUserDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户状态
     */
    private UserStatusEnum status;

    /**
     * 锁定标志
     */
    private LockFlagEnum lockFlag;

    /**
     * 锁定截止时间（账户锁定到该时间点）
     */
    private Instant LockUntil;

    /**
     * 角色列表（角色名称或ID）
     */
    private List<Long> roles;

    /**
     * 权限列表（权限标识符）
     */
    private List<String> permissions;
}
