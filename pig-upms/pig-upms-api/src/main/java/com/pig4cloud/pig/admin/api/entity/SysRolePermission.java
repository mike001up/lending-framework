package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "角色权限")
@EqualsAndHashCode(callSuper = true)
public class SysRolePermission extends Model<SysRolePermission> {

	private static final long serialVersionUID = 1L;

	@Schema(description = "角色id")
	private Long roleId;

	@Schema(description = "权限id")
	private Long permissionId;

}