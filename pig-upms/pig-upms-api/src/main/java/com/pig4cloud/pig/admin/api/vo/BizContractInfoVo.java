package com.pig4cloud.pig.admin.api.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pig4cloud.pig.admin.api.entity.BizContractExecution;
import com.pig4cloud.pig.admin.api.entity.SysUserKyc;
import com.pig4cloud.pig.common.core.jackson.SqlTimestampDeserializer;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Data
@Schema(description = "合同信息VO")
public class BizContractInfoVo  {


	/**
	* ID
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="ID")
    private Long id;

	/**
	* 合同编号
	*/
    @Schema(description="合同编号")
    private Long contractId;

	/**
	* 借款人ID
	*/
    @Schema(description="借款人ID")
    private Long userId;

	/**
	* 用户账号（冗余）
	*/
    @Schema(description="用户账号（冗余）")
    private String userName;

	/**
	* 真实姓名（冗余）
	*/
    @Schema(description="真实姓名（冗余）")
    private String realName;

	/**
	* 借款周期（天/期）
	*/
    @Schema(description="借款周期（天/期）")
    private Integer loanTerm;

	/**
	* 合同开始时间
	*/
    @Schema(description="合同开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = SqlTimestampDeserializer.class)
    private Timestamp startDate;

	/**
	* 合同结束时间
	*/
    @Schema(description="合同结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = SqlTimestampDeserializer.class)
    private Timestamp endDate;

	/**
	* 滞纳金开始计算日期（合同开始后延迟还款开始收滞纳金）
	*/
    @Schema(description="滞纳金开始计算日期（合同开始后延迟还款开始收滞纳金）")
    private Integer startLateFeeDate;

	/**
	* 每期收款开始日期（天数）
	*/
    @Schema(description="每期收款开始日期（天数）")
    private Integer periodStartDate;

	/**
	* 每期收款期天数（每期收款截止日期=每期收款日期+天数）
	*/
    @Schema(description="每期收款期天数（每期收款截止日期=每期收款日期+天数）")
    private Integer periodDays;

	/**
	* 借款金额
	*/
    @Schema(description="借款金额")
    private BigDecimal principal;

	/**
	* 实付金额
	*/
    @Schema(description="实付金额")
    private BigDecimal fundAmount;

	/**
	* 手续费金额
	*/
    @Schema(description="手续费金额")
    private BigDecimal feeAmount;

	/**
	* 币种
	*/
    @Schema(description="币种")
    private String currency;

	/**
	* 利率（%）
	*/
    @Schema(description="利率（%） 数据库存小数")
    private BigDecimal interestRate;

	/**
	* 还款方式
	*/
    @Schema(description="还款方式")
    private String repaymentType;

	/**
	* 执行人
	*/
    @Schema(description="执行人")
    private String executor;

	/**
	* 项目总监
	*/
    @Schema(description="项目总监")
    private String projectDirector;

	/**
	* 备注
	*/
    @Schema(description="备注")
    private String remark;

	/**
	* 完成百分比
	*/
    @Schema(description="完成百分比")
    private BigDecimal finishedProgress;

	/**
	* 是否结束（0结束，1进行中）
	*/
    @Schema(description="是否结束（0结束，1进行中）")
    private Integer isRunning;

	/**
	* 商户ID
	*/
    @Schema(description="商户ID")
    private Long merchantId;

	/**
	* 担保人
	*/
    @Schema(description="担保人")
    private String guarantor;

	/**
	* 创建人
	*/
	@TableField(fill = FieldFill.INSERT)
    @Schema(description="创建人")
    private String createBy;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
    @Schema(description="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

	/**
	* 修改人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="修改人")
    private String updateBy;

	/**
	* 修改时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;


	@Schema(description="kyc信息")
	private SysUserKyc sysUserKyc;

	@Schema(description="抵押物列表")
	private List<BizWareHouse> houseList;

	@Schema(description="合同执行情况")
	private BizContractExecution bizContractExecution;
}
