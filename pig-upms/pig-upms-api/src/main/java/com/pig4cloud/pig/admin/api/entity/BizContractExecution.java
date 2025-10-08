package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 合同执行情况表
 *
 * @author pig
 * @date 2025-09-29 13:14:01
 */
@Data
@TableName("biz_contract_execution")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "合同执行情况表")
public class BizContractExecution extends Model<BizContractExecution> {


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
	* 还款余额
	*/
    @Schema(description="还款余额")
    private BigDecimal balance;


	/**
	* 合同总金额(本金加利息)
	*/
    @Schema(description="合同总金额(本金加利息)")
    private BigDecimal totalMoney;

	/**
	* 汇款进度, 如: 50%
	*/
    @Schema(description="汇款进度, 如: 50%,这里存储小数")
    private BigDecimal repaymentProgress;

	/**
	* 备注
	*/
    @Schema(description="备注")
    private String remark;

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
