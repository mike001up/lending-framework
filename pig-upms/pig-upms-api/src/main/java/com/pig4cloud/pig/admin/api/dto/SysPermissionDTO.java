package com.pig4cloud.pig.admin.api.dto;

import com.pig4cloud.pig.admin.api.enums.ResourceTypeEnum;
import com.pig4cloud.pig.common.core.constant.enums.PermissionStatusEnum;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "权限资源")
@EqualsAndHashCode(callSuper = true)
public class SysPermissionDTO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "权限id")
	@NotNull(groups = UpdateGroup.class,message = "权限ID不能为空")
	private Long permissionId;

	@NotBlank(groups = CreateGroup.class,message = "权限名称不能为空")
	@Schema(description = "权限名称")
	private String permName;

	@NotBlank(groups = CreateGroup.class,message = "权限编码不能为空")
	@Schema(description = "权限编码")
	private String permCode;

	@NotBlank(groups = CreateGroup.class,message = "权限标识不能为空")
	@Schema(description = "权限标识")
	private String permission;

	@Schema(description = "描述")
	private String description;

	@Schema(description = "资源类型：module/menu/button/api")
	@NotNull(groups = CreateGroup.class,message = "资源类型不能为空")
	private ResourceTypeEnum resourceType;

	@Schema(description = "请求路径")
	private String path;

	@Schema(description = "请求方法")
	private String method;

	@Schema(description = "父级id")
	private Long parentId;

	@Schema(description = "父级权限标识")
	private String parentPermission;

	@Schema(description = "排序值")
	private Integer sortOrder;

	@Schema(description = "状态：enabled-启用，disabled-禁用")
	private PermissionStatusEnum status;

}