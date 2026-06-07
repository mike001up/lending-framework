package com.pig4cloud.pig.admin.api.entity;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;

@Data
@Schema(description = "审计日志")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_ID)
	@ExcelProperty("日志编号")
	@Schema(description = "日志编号")
	private Long id;

	@Schema(description = "操作人ID")
	private Long userId;

	@ExcelProperty("操作人")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "操作人登录名（快照）")
	private String createBy;

	@NotBlank(message = "操作类型不能为空")
	@ExcelProperty("操作类型")
	@Schema(description = "操作类型（如 LOGIN, CREATE_USER）")
	private String operationType;

	@ExcelProperty("操作对象")
	@Schema(description = "操作对象描述")
	private String targetObject;

	@Schema(description = "操作结果：1-成功，0-失败")
	private Integer result;

	@ExcelProperty("操作ip地址")
	@Schema(description = "请求来源IP")
	private String remoteAddr;

	@Schema(description = "客户端标识")
	private String clientName;

	@Schema(description = "附加详情JSON")
	private String detail;

	@ExcelProperty("创建时间")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "操作时间")
	private Instant createTime;

	@ExcelIgnore
	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "更新时间")
	private Instant updateTime;

	@NotBlank(message = "日志类型不能为空")
	@ExcelIgnore
	@Schema(description = "日志类型（0-正常 9-错误）")
	private String logType;

	@ExcelProperty("日志标题")
	@Schema(description = "日志标题")
	private String title;

	@Schema(description = "用户代理")
	private String userAgent;

	@ExcelProperty("请求uri")
	@Schema(description = "请求uri")
	private String requestUri;

	@ExcelProperty("操作方式")
	@Schema(description = "HTTP方法")
	private String method;

	@ExcelProperty("提交数据")
	@Schema(description = "提交数据")
	private String params;

	@ExcelProperty("执行时间")
	@Schema(description = "方法执行时间")
	private Long time;

	@ExcelProperty("异常信息")
	@Schema(description = "异常信息")
	private String exception;

	@ExcelProperty("应用标识")
	@Schema(description = "应用标识")
	private String serviceId;

	@TableLogic(value = "'NO'", delval = "'YES'")
	@ExcelIgnore
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "删除标记,YES:已删除,NO:正常")
	private IsDelEnum isDel;

}
