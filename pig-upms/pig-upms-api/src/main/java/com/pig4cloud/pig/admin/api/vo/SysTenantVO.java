package com.pig4cloud.pig.admin.api.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.TenantStatusEnum;
import com.pig4cloud.pig.common.core.constant.enums.GrantTypeEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class SysTenantVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "主键")
	private Long id;

	@Schema(description = "租户代码")
	private String tenantCode;

	@Schema(description = "租户名称")
	private String name;

	@Schema(description = "状态:ENABLED-启用,DISABLED-禁用")
	private TenantStatusEnum status;

	@Schema(description = "描述")
	private String description;
}