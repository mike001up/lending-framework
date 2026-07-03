package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.IpActionEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_ip_limit")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "后台IP白名单")
public class SysIpLimit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	@Schema(description = "ID")
	private Long id;

	@Schema(description = "客户端id，关联sys_oauth_client_details.id")
	private Long clientId;

	@Schema(description = "IP 地址")
	private String ip;

	@Schema(description = "IP 限制规则")
	private IpActionEnum action;
}