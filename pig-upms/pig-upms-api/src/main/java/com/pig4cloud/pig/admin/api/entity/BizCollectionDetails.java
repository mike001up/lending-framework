package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pig4cloud.pig.common.core.jackson.SqlTimestampDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 收款详情表
 *
 * @author pig
 * @date 2025-09-29 18:48:39
 */
@Data
@TableName("biz_collection_details")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "收款详情表")
public class BizCollectionDetails extends Model<BizCollectionDetails> {


	/**
	* 主键ID
	*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 合同ID
	*/
    @Schema(description="合同ID")
    private Long contractId;

	/**
	* 收款日期
	*/
    @Schema(description="收款日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = SqlTimestampDeserializer.class)
    private Timestamp repaymentPeriod;

	/**
	* 收款前余额
	*/
    @Schema(description="收款前余额")
    private BigDecimal preBalance;

	/**
	* 收款后余额
	*/
    @Schema(description="收款后余额")
    private BigDecimal postBalance;

	/**
	* 应还本金
	*/
    @Schema(description="应还本金")
    private BigDecimal remainsPrincipal;

	/**
	* 本期利息
	*/
    @Schema(description="本期利息")
    private BigDecimal periodInterest;

	/**
	* 应收利息
	*/
    @Schema(description="应收利息")
    private BigDecimal remainsInterest;

	/**
	* 实收本金
	*/
    @Schema(description="实收本金")
    private BigDecimal collectedPrincipal;

	/**
	* 实收利息
	*/
    @Schema(description="实收利息")
    private BigDecimal collectedInterest;

	/**
	* 滞纳金
	*/
    @Schema(description="滞纳金")
    private BigDecimal lateFee;

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
	@JsonDeserialize(using = SqlTimestampDeserializer.class)
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
	@JsonDeserialize(using = SqlTimestampDeserializer.class)
    private Timestamp updateTime;
}
