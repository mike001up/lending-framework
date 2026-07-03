/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pig.admin.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.pig4cloud.pig.admin.api.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.pig4cloud.pig.common.core.constant.enums.CertificationStatusEnum;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.LockFlagEnum;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;

/**
 * @author lengleng
 * @date 2017/10/29
 */
@Data
@Schema(description = "前端用户展示对象")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键")
    private Long userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 锁定标记 (0:正常, 9:已锁定)
     */
    @Schema(description = "锁定标记")
    private LockFlagEnum lockFlag;

    /**
     * 锁定截止时间
     */
    @Schema(description = "锁定截止时间")
    private Instant lockUntil;

    /**
     * 状态 (0:启用, 1:禁用)
     */
    @Schema(description = "状态")
    private UserStatusEnum status;

    /**
     * 认证状态 (0:未认证, 1:待审核, 2:已认证, 3:审核拒绝)
     */
    @Schema(description = "认证状态")
    private CertificationStatusEnum certificationStatus;

    /**
     * 认证信息 (JSON格式)
     */
    @Schema(description = "认证信息")
    private String certificationInfo;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Instant createTime;
    
    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private Instant updateTime;
}
