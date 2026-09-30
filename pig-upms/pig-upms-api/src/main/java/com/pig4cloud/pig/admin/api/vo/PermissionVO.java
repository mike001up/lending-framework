/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pig.admin.api.vo;

import java.io.Serializable;
import java.time.Instant;

import com.pig4cloud.pig.admin.api.enums.ResourceTypeEnum;
import com.pig4cloud.pig.common.core.constant.enums.PermissionStatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lengleng
 * @date 2020/2/10
 */
@Data
@Schema(description = "前端角色展示对象")
public class PermissionVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Schema(description = "权限id")
	private Long permissionId;

	@Schema(description = "权限名称")
	private String permName;

	@Schema(description = "权限编码")
	private String permCode;

	@Schema(description = "权限标识")
	private String permission;

	@Schema(description = "描述")
	private String description;

	@Schema(description = "资源类型：module/menu/button/api")
	private ResourceTypeEnum resourceType;

	@Schema(description = "请求路径")
	private String path;

	@Schema(description = "请求方法")
	private String method;

	@Schema(description = "父级id")
	private Long parentId;

	@Schema(description = "排序值")
	private Integer sortOrder;

	@Schema(description = "状态：enabled-启用，disabled-禁用")
	private PermissionStatusEnum status;

	/**
	 * 创建者
	 */
	@Schema(description = "创建人")
	private String createBy;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private Instant createTime;

	/**
	 * 更新者
	 */
	@Schema(description = "更新人")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	private Instant updateTime;
}
