package com.pig4cloud.pig.admin.api.vo;

import java.io.Serializable;

import com.pig4cloud.pig.common.core.constant.enums.DbTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "租户数据源配置")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysTenantDatasourceVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Schema(description = "主键")
	private Long id;

	@Schema(description = "租户id")
	private Long tenantId;

	@Schema(description = "终端应用")
	private Long clientId;

	@Schema(description = "数据库类型")
	private DbTypeEnum dbType;

	@Schema(description = "主机地址")
	private String host;

	@Schema(description = "端口")
	private Integer port;

	@Schema(description = "数据库名")
	private String dbName;

	@Schema(description = "数据库用户名")
	private String username;

	@Schema(description = "AES加密后的密码")
	private String passwordEncrypted;

	@Schema(description = "连接池参数")
	private String poolParams;

}