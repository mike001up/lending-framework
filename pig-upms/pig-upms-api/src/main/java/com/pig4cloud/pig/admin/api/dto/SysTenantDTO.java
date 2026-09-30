package com.pig4cloud.pig.admin.api.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.TenantStatusEnum;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "租户")
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
public class SysTenantDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = UpdateGroup.class, message = "请指定租户")
	private Long id;

	@NotBlank(groups = CreateGroup.class, message = "租户代码不能为空")
	@Schema(description = "租户代码")
	private String tenantCode;

	@NotBlank(groups = CreateGroup.class, message = "租户名称不能为空")
	@Schema(description = "租户名称")
	private String name;

	@Schema(description = "状态:ENABLED-启用,DISABLED-禁用")
	private TenantStatusEnum status;

	@Schema(description = "描述")
	private String description;

}