package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 后台IP白名单
 *
 * @author pig
 * @date 2025-08-28 16:57:13
 */
@Data
@TableName("biz_ip_limit")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "后台IP白名单")
public class BizIpLimit extends Model<BizIpLimit> {


	/**
	* ID
	*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 0 后台;1 app端
	*/
    @Schema(description="0 后台;1 app端")
    private String rangeType;

	/**
	* IP 地址
	*/
    @Schema(description="IP 地址")
    private String ip;

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
    private Instant createTime;

	/**
	* 更新人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="更新人")
    private String updateBy;

	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="更新时间")
    private Instant updateTime;
}
