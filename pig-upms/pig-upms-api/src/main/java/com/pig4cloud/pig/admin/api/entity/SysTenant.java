package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;

@Data
@Schema(description = "租户")
@EqualsAndHashCode(callSuper = true)
public class SysTenant extends Model<SysTenant> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键")
	private Long id;

	@NotBlank(message = "租户代码不能为空")
	@Schema(description = "租户代码")
	private String tenantCode;

	@NotBlank(message = "租户名称不能为空")
	@Schema(description = "租户名称")
	private String name;

	@Schema(description = "状态:ENABLED-启用,DISABLED-禁用")
	private String status;

	@Schema(description = "描述")
	private String description;

	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private Instant createdAt;

	@Schema(description = "创建人id")
	private Long createdBy;

	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "更新时间")
	private Instant updatedAt;

	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "逻辑删除,YES:已删除,NO:未删除")
	private IsDelEnum isDel;

}