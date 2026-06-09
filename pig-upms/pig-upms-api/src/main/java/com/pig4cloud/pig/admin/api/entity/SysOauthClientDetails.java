package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.ClientStatusEnum;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "客户端信息")
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	@NotBlank(message = "client_id 不能为空")
	@Schema(description = "客户端id")
	private String clientId;

	@NotBlank(message = "客户端名称不能为空")
	@Schema(description = "客户端名称")
	private String clientName;

	@NotBlank(message = "client_secret 不能为空")
	@Schema(description = "客户端密钥")
	private String clientSecret;

	@Schema(description = "资源id列表")
	private String resourceIds;

	@NotBlank(message = "scope 不能为空")
	@Schema(description = "作用域")
	private String scope;

	@Schema(description = "授权方式")
	private String[] authorizedGrantTypes;

	@Schema(description = "回调地址")
	private String webServerRedirectUri;

	@Schema(description = "权限列表")
	private String authorities;

	@Schema(description = "请求令牌有效时间")
	private Integer accessTokenValidity;

	@Schema(description = "刷新令牌有效时间")
	private Integer refreshTokenValidity;

	@Schema(description = "扩展信息")
	private String additionalInformation;

	@Schema(description = "是否自动放行")
	private String autoapprove;

	@Schema(description = "状态：enabled-启用，disabled-禁用")
	private ClientStatusEnum status;

	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "删除标记,YES:已删除,NO:正常")
	private IsDelEnum isDel;

}
