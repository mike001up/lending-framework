package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 催款计划信息表
 *
 * @author pig
 * @date 2025-09-29 13:25:39
 */
@Data
@TableName("biz_collection_schedule")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "催款计划信息表")
public class BizCollectionSchedule extends Model<BizCollectionSchedule> {


	/**
	* 主键ID
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 合同ID
	*/
    @Schema(description="合同ID")
    private Long contractId;

	/**
	* 用户ID
	*/
    @Schema(description="用户ID")
    private Long userId;

	/**
	* 用户账号（冗余设计）
	*/
    @Schema(description="用户账号（冗余设计）")
    private String userName;

	/**
	* 真实姓名（冗余设计）
	*/
    @Schema(description="真实姓名（冗余设计）")
    private String realName;

	@Schema(description="滞纳金")
	private BigDecimal lateFee;

	/**
	* 最小收款金额
	*/
    @Schema(description="最小收款金额")
    private BigDecimal minAmount;

	/**
	* 最大收款金额
	*/
    @Schema(description="最大收款金额")
    private BigDecimal maxAmount;

	/**
	* 本期还款前余额
	*/
    @Schema(description="本期还款前余额")
    private BigDecimal balance;

	/**
	* 收款日期
	*/
    @Schema(description="收款日期")
    private Timestamp collectionDate;

	/**
	* 实际收款金额
	*/
    @Schema(description="实际收款金额")
    private BigDecimal collectedAmount;

	/**
	* 客户签名
	*/
    @Schema(description="客户签名")
    private String clientsSignature;

	/**
	* 收款开始时间
	*/
    @Schema(description="收款开始时间")
    private Timestamp colStartDate;

	/**
	* 收款结束时间
	*/
    @Schema(description="收款结束时间")
    private Timestamp colEndDate;

	/**
	* 收款人签名
	*/
    @Schema(description="收款人签名")
    private String operatorSignature;

	/**
	* 收款签条图片地址
	*/
    @Schema(description="收款签条图片地址")
    private String slipLink;

	/**
	* 入款时间
	*/
    @Schema(description="入款时间")
    private Timestamp incomeTime;

	/**
	* 入款金额
	*/
    @Schema(description="入款金额")
    private BigDecimal incomeAmount;

	/**
	* 审核人ID
	*/
    @Schema(description="审核人ID")
    private Long approveUserId;

	/**
	* 审核时间
	*/
    @Schema(description="审核时间")
    private Timestamp approveTime;

	/**
	* 审核状态 0 待审核;1 审核不通过;2 审核通过
	*/
    @Schema(description="审核状态 0 待审核;1 审核不通过;2 审核通过")
    private Integer approveStatus;

	/**
	* 审核不通过原因
	*/
    @Schema(description="审核不通过原因")
    private String rejectReason;

	/**
	* 收款状态 0 待收款;1 延迟收款;2 已收款
	*/
    @Schema(description="收款状态 0 待收款;1 延迟收款;2 已收款")
    private Integer status;

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
    private Timestamp updateTime;

}
