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

package com.pig4cloud.pig.admin.api.dto;

import java.io.Serializable;
import java.util.List;

import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Data
@Schema(description = "角色")
public class SysRoleDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Schema(description = "角色编号")
	@NotNull(groups = UpdateGroup.class,message = "角色ID不能为空")
	private Long roleId;

	@Size(min = 2, max = 50, message = "角色名称长度必须在2到50个字符之间")
	@NotBlank(groups = CreateGroup.class,message = "角色名称不能为空")
	@Schema(description = "角色名称")
	private String roleName;

	@NotBlank(groups = CreateGroup.class,message = "角色标识不能为空")
	@Schema(description = "角色标识")
	private String roleCode;

	@Schema(description = "角色描述")
	private String roleDesc;
	
	@Schema(description = "角色权限")
	private List<String> permissionIds;

}
