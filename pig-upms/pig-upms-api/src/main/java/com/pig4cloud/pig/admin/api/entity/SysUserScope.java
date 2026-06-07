package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;

@Data
@Schema(description = "用户范围授权")
@EqualsAndHashCode(callSuper = true)
public class SysUserScope extends Model<SysUserScope> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键")
	private Long id;

	@NotNull(message = "用户ID不能为空")
	@Schema(description = "用户id")
	private Long userId;

	@NotNull(message = "范围ID不能为空")
	@Schema(description = "范围id")
	private Long scopeId;

	@NotNull(message = "授权人不能为空")
	@Schema(description = "授权人用户id")
	private Long grantedBy;

	@Schema(description = "授权时间")
	private Instant grantedAt;

	@Schema(description = "解除授权时间")
	private Instant revokedAt;

	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "逻辑删除,YES:已删除,NO:未删除")
	private IsDelEnum isDel;

}