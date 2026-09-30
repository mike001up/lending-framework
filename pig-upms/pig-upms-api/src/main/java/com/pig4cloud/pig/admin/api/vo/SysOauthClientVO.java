package com.pig4cloud.pig.admin.api.vo;

import java.io.Serializable;

import com.pig4cloud.pig.admin.api.entity.SysOauthClientAdditionalInformation;
import com.pig4cloud.pig.common.core.constant.enums.ClientStatusEnum;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "客户端信息")
@AllArgsConstructor
@NoArgsConstructor
// @EqualsAndHashCode(callSuper = true)
public class SysOauthClientVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = UpdateGroup.class, message = "id 不能为空")
	@Schema(description = "id")
	private Long id;

	@NotBlank(groups = CreateGroup.class, message = "client_id 不能为空")
	@Schema(description = "客户端id")
	private String clientId;

	@NotBlank(groups = CreateGroup.class, message = "客户端名称不能为空")
	@Schema(description = "客户端名称")
	private String clientName;

	@NotBlank(groups = CreateGroup.class, message = "client_secret 不能为空")
	@Schema(description = "客户端密钥")
	private String clientSecret;

	//此client 被哪些其他 client访问
	@Schema(description = "资源id列表")
	private String resourceIds;

	@NotBlank(groups = CreateGroup.class, message = "scope 不能为空")
	@Schema(description = "作用域")
	private String scope;

	@Schema(description = "授权方式")
	private String authorizedGrantTypes;

	@Schema(description = "回调地址")
	private String webServerRedirectUri;

	//此client 具有哪些 内部权限点
	@Schema(description = "权限列表")
	private String authorities;

	@Schema(description = "请求令牌有效时间")
	private Integer accessTokenValidity;

	@Schema(description = "刷新令牌有效时间")
	private Integer refreshTokenValidity;

	@NotNull(groups = CreateGroup.class, message = "扩展信息 不能为空")
	@Schema(description = "扩展信息")
	private SysOauthClientAdditionalInformation additionalInformation;

	@Schema(description = "是否自动放行")
	private String autoapprove;

	@Schema(description = "状态：enabled-启用，disabled-禁用")
	private ClientStatusEnum status;

}
