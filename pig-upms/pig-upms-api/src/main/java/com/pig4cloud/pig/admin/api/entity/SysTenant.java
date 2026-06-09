package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.TenantStatusEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "租户")
@EqualsAndHashCode(callSuper = true)
public class SysTenant extends BaseEntity {

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
	private TenantStatusEnum status;

	@Schema(description = "描述")
	private String description;


	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "逻辑删除,YES:已删除,NO:未删除")
	private IsDelEnum isDel;

}