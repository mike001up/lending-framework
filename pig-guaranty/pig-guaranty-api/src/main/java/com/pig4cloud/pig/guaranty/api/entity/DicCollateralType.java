package com.pig4cloud.pig.guaranty.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.sql.Timestamp;


/**
 * 抵押物类型字典表
 *
 * @author pig
 * @date 2025-09-29 18:59:33
 */
@Data
@TableName("dic_collateral_type")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "抵押物类型字典表")
public class DicCollateralType extends Model<DicCollateralType> {


	/**
	* 主键ID
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 抵押物类型名称
	*/
    @Schema(description="抵押物类型名称")
    private String collateralType;

	/**
	* 抵押物类型值
	*/
    @Schema(description="抵押物类型值")
    private String collateralTypeValue;

	/**
	* 排序号
	*/
    @Schema(description="排序号")
    private Integer sn;

	/**
	* 是否删除 0未删除 1已删除
	*/
    @Schema(description="是否删除 0未删除 1已删除")
	@TableLogic
    private Integer isDel;

	/**
	* 是否启用 0禁用 1启用
	*/
    @Schema(description="是否启用 0禁用 1启用")
    private Integer isEnable;

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
