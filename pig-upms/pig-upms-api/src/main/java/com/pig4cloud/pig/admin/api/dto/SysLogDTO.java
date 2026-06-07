package com.pig4cloud.pig.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(description = "审计日志查询对象")
public class SysLogDTO {

	private Long id;

	@Schema(description = "操作人ID")
	private Long userId;

	@Schema(description = "操作人登录名")
	private String createBy;

	@Schema(description = "操作类型")
	private String operationType;

	@Schema(description = "操作对象描述")
	private String targetObject;

	@Schema(description = "操作结果：1-成功，0-失败")
	private Integer result;

	@Schema(description = "请求来源IP")
	private String remoteAddr;

	@Schema(description = "客户端标识")
	private String clientName;

	@Schema(description = "日志类型")
	private String logType;

	@Schema(description = "日志标题")
	private String title;

	@Schema(description = "应用标识")
	private String serviceId;

	@Schema(description = "创建时间区间 [开始时间，结束时间]")
	private Instant[] createTime;

}
