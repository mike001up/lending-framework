package com.pig4cloud.pig.admin.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.ClientStatusEnum;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "客户端信息扩展字段")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysOauthClientAdditionalInformation {

	@Schema(description = "是否加密客户端密钥 0:不加密; 1:加密")
	private  Integer encFlag;

	@Schema(description = "验证码开关 0:开; 1:关闭")
	private Integer captchaFlag;

	@Schema(description = "允许同时在线会话数量限制")
	private Integer onlineQuantity;

	//此client 被哪些其他 client访问
	@Schema(description = "IP 验证策略")
	private String ipAction;

}
