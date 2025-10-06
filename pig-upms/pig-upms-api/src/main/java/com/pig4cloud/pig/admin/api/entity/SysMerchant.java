package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.sql.Timestamp;

/**
 * 商户表
 *
 */
@Data
@TableName("sys_merchant")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "商户表")
public class SysMerchant extends Model<SysMerchant> {


	/**
	* 主键ID
	*/
    @TableId(type = IdType.AUTO)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 平台账号名称
	*/
    @Schema(description="平台账号名称")
    private String platName;

	@TableField(exist = false)
	@Schema(description="登录密码")
	private String password;

	/**
	* 商户名
	*/
    @Schema(description="商户名")
    private String merName;

	/**
	* 商户地址
	*/
    @Schema(description="商户地址")
    private String merLocation;

	/**
	* 商户API Key
	*/
    @Schema(description="商户API Key")
    private String merApiKey;

	/**
	* 商户密钥
	*/
    @Schema(description="商户密钥")
    private String merSecKey;

	/**
	* 营业执照存放地址(链接)
	*/
    @Schema(description="营业执照存放地址(链接)")
    private String permitLink;

	/**
	* 营业执照编号
	*/
    @Schema(description="营业执照编号")
    private String permitId;

	/**
	* 状态: 1=正常, 2=停用
	*/
    @Schema(description="状态: 1=正常, 2=停用")
    private Integer status;

	/**
	* 联系方式(手机号/电话)
	*/
    @Schema(description="联系方式(手机号/电话)")
    private String contractNumber;

	/**
	* 联系人
	*/
    @Schema(description="联系人")
    private String contractPerson;

	/**
	* 是否删除: 0=未删除, 1=已删除
	*/
    @Schema(description="是否删除: 0=未删除, 1=已删除")
	@TableLogic
    private Integer isDel;

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
