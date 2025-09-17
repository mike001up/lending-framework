package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 用户KYC信息表
 *
 * @author pig
 * @date 2025-09-17 14:26:09
 */
@Data
@TableName("sys_user_kyc")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户KYC信息表")
public class SysUserKyc extends Model<SysUserKyc> {


	/**
	* 自增ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="自增ID")
    private Long id;

	/**
	* 关联用户表 user.id
	*/
    @Schema(description="关联用户表 user.id")
    private Long userId;

	/**
	* 真实姓名
	*/
    @Schema(description="真实姓名")
    private String realName;

	/**
	* 国家
	*/
    @Schema(description="国家")
    private String country;

	/**
	* 城市
	*/
    @Schema(description="城市")
    private String city;

	/**
	* 家庭详细地址
	*/
    @Schema(description="家庭详细地址")
    private String address;

	/**
	* 住房购买或者租赁合同，图片地址
	*/
    @Schema(description="住房购买或者租赁合同，图片地址")
    private String agreementRealEstate;

	/**
	* 证件号
	*/
    @Schema(description="证件号")
    private String idNumber;

	/**
	* 证件类型: ID_CARD(身份证)/PASS_PORT(护照)/DRIVER_LICENSE(驾照)
	*/
    @Schema(description="证件类型: ID_CARD(身份证)/PASS_PORT(护照)/DRIVER_LICENSE(驾照)")
    private String idType;

	/**
	* 证件正面照片URL
	*/
    @Schema(description="证件正面照片URL")
    private String idImageFront;

	/**
	* 证件反面照片URL
	*/
    @Schema(description="证件反面照片URL")
    private String idImageBack;

	/**
	* 手持证件照片URL
	*/
    @Schema(description="手持证件照片URL")
    private String idImageHand;

	/**
	* 联系人姓名
	*/
    @Schema(description="联系人姓名")
    private String contactName;

	/**
	* 联系人电话
	*/
    @Schema(description="联系人电话")
    private String contactPhone;

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
    private LocalDateTime createTime;

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
    private LocalDateTime updateTime;
}
