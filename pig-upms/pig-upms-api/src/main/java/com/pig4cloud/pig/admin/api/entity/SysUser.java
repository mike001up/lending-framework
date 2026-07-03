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

package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pig4cloud.pig.common.core.constant.enums.CertificationStatusEnum;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.LockFlagEnum;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;
import com.pig4cloud.pig.common.core.validation.ValidPhone;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Data
@Schema(description = "用户")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键id")
	private Long userId;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	@Size(min = 6, max = 64, message = "用户名长度必须在6到64个字符之间")
	private String username;

	/**
	 * 密码
	 */
	@Schema(description = "密码")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,50}$", 
         message = "密码必须包含大小写字母和数字，且长度在6-50之间")
	private String password;

	/**
	 * 随机盐
	 */
	@JsonIgnore
	@Schema(description = "随机盐")
	private String salt;


	// @TableLogic(value = "'NO'", delval = "'YES'")
	// @TableField(fill = FieldFill.INSERT)
	// @Schema(description = "删除标记,YES:已删除,NO:正常")
	// private IsDelEnum isDel;

	/**
	 * 锁定标记
	 */
	@Schema(description = "锁定标记")
	private LockFlagEnum lockFlag;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	@ValidPhone(message = "Please enter a valid international phone number")
	private String phone;

	/**
	 * 头像
	 */
	@Schema(description = "头像地址")
	private String avatar;


	/**

	 * 微信openid
	 */
	@Schema(description = "微信openid")
	private String wxOpenid;

	/**
	 * 微信小程序openId
	 */
	@Schema(description = "微信小程序openid")
	private String miniOpenid;

	/**
	 * QQ openid
	 */
	@Schema(description = "QQ openid")
	private String qqOpenid;

	/**
	 * 码云唯一标识
	 */
	@Schema(description = "码云唯一标识")
	private String giteeLogin;

	/**
	 * 开源中国唯一标识
	 */
	@Schema(description = "开源中国唯一标识")
	private String oscId;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称")
	private String nickname;

	/**
	 * 姓名
	 */
	@Schema(description = "姓名")
	private String name;

	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱")
	@Email(message = "邮箱格式不正确")
	private String email;


	@Schema(description = "用户状态：enabled-启用，disabled-禁用，closed-已关停")
	private UserStatusEnum status;

	@Schema(description = "锁定到期时间")
	private Instant lockUntil;

	@Schema(description = "认证状态：not_certified-未认证，in_progress-认证中，certified-已认证，failed-认证失败")
	private CertificationStatusEnum certificationStatus;

	@Schema(description = "提交的身份信息JSON")
	private String certificationInfo;

}
