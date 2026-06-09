package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "租户数据源配置")
@EqualsAndHashCode(callSuper = true)
public class SysTenantDatasource extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "主键")
	private Long id;

	@NotNull(message = "租户ID不能为空")
	@Schema(description = "租户id")
	private Long tenantId;


	@NotBlank(message = "数据库类型不能为空")
	@Schema(description = "数据库类型")
	private String dbType;

	@NotBlank(message = "主机地址不能为空")
	@Schema(description = "主机地址")
	private String host;

	@NotNull(message = "端口不能为空")
	@Schema(description = "端口")
	private Integer port;

	@NotBlank(message = "数据库名不能为空")
	@Schema(description = "数据库名")
	private String dbName;

	@NotBlank(message = "数据库用户名不能为空")
	@Schema(description = "数据库用户名")
	private String username;

	@NotBlank(message = "加密密码不能为空")
	@Schema(description = "AES加密后的密码")
	private String passwordEncrypted;

	@Schema(description = "连接池参数")
	private String poolParams;


	@TableLogic(value = "'NO'", delval = "'YES'")
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "逻辑删除,YES:已删除,NO:未删除")
	private IsDelEnum isDel;

}