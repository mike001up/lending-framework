package com.pig4cloud.pig.guaranty.api.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 抵押物库存信息
 *
 * @author pig
 * @date 2025-09-29 18:53:34
 */
@Data
@TableName("biz_ware_house")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "抵押物库存信息")
public class BizWareHouse extends Model<BizWareHouse> {


	/**
	* 主键ID
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 抵押物类型ID
	*/
    @Schema(description="抵押物类型ID")
    private Long collateralTypeId;

	/**
	* 评估价值
	*/
    @Schema(description="评估价值")
    private BigDecimal valueOfCollateral;

	/**
	* 抵押物数量
	*/
    @Schema(description="抵押物数量")
    private Integer quality;

	/**
	* 状态 0在仓库；1已返还；2已售卖
	*/
    @Schema(description="状态 0在仓库；1已返还；2已售卖")
    private Integer status;

	/**
	* 是否删除 0未删除，1已删除
	*/
    @Schema(description="是否删除 0未删除，1已删除")
	@TableLogic
    private Integer isDel;

	/**
	* 仓库管理员ID-创建人ID
	*/
    @Schema(description="仓库管理员ID-创建人ID")
    private Long operatorId;

	/**
	* 仓库管理员账号
	*/
    @Schema(description="仓库管理员账号")
    private String userName;

	/**
	* JSON保存不同抵押物的具体属性参数
	*/
    @Schema(description="JSON保存不同抵押物的具体属性参数")
	@TableField(typeHandler = JacksonTypeHandler.class)
	private JsonNode parameters;

	/**
	* 修改人ID
	*/
    @Schema(description="修改人ID")
    private Long updateUserId;

	/**
	* 修改人账号
	*/
    @Schema(description="修改人账号")
    private String updateUserName;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
    @Schema(description="创建时间")
    private Timestamp createTime;

	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="更新时间")
    private Timestamp updateTime;

	/**
	 * 抵押物类型名称
	 */
	@Schema(description="抵押物类型名称")
	@TableField(exist = false)
	private String collateralType;

	/**
	 * 抵押物类型值
	 */
	@Schema(description="抵押物类型值")
	@TableField(exist = false)
	private String collateralTypeValue;
}
