package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;

@Data
@Schema(description = "权限资源")
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends Model<SysPermission> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "permission_id", type = IdType.ASSIGN_ID)
	@Schema(description = "权限id")
	private Long permissionId;

	@NotBlank(message = "权限名称不能为空")
	@Schema(description = "权限名称")
	private String name;

	@Schema(description = "权限编码")
	private String permCode;

	@Schema(description = "权限标识")
	private String permission;

	@Schema(description = "描述")
	private String description;

	@Schema(description = "资源类型：module/menu/button/api")
	private String resourceType;

	@Schema(description = "请求路径")
	private String path;

	@Schema(description = "请求方法")
	private String method;

	@NotNull(message = "父级ID不能为空")
	@Schema(description = "父级id")
	private Long parentId;

	@Schema(description = "排序值")
	private Integer sortOrder;

	@Schema(description = "状态：enabled-启用，disabled-禁用")
	private String status;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建人")
	private String createBy;

	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "修改人")
	private String updateBy;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private Instant createTime;

	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "更新时间")
	private Instant updateTime;

	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "删除标记,YES:已删除,NO:正常")
	private IsDelEnum isDel;

}