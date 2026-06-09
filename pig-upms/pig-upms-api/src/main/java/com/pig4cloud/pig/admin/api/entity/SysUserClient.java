package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户客户端授权")
public class SysUserClient implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键")
	private Long id;

	@NotNull(message = "用户ID不能为空")
	@Schema(description = "用户id")
	private Long userId;

	@NotNull(message = "客户端ID不能为空")
	@Schema(description = "客户端id，关联sys_oauth_client_details.id")
	private Long clientId;

}