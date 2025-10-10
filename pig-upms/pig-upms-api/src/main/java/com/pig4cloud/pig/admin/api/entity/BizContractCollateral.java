package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合同抵押物关联表
 *
 * @author pig
 * @date 2025-09-29 13:19:41
 */
@Data
@TableName("biz_contract_collateral")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "合同抵押物关联表")
public class BizContractCollateral extends Model<BizContractCollateral> {


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
	* 抵押物ID
	*/
    @Schema(description="抵押物ID")
    private Long collateralId;
}
