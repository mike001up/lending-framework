CREATE TABLE IF NOT EXISTS `service_auth_rule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `provider_service` VARCHAR(64) NOT NULL COMMENT '被调方服务ID',
  `caller_service` VARCHAR(64) NOT NULL COMMENT '调用方服务ID',
  `path_pattern` VARCHAR(255) NOT NULL COMMENT '路径匹配模式(Ant风格)',
  `http_method` VARCHAR(10) DEFAULT '*' COMMENT 'HTTP方法(*表示全部)',
  `is_allowed` TINYINT(1) DEFAULT 1 COMMENT '是否允许(1允许/0拒绝)',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '说明',
  `created_by` BIGINT DEFAULT NULL COMMENT '创建人ID',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` BIGINT DEFAULT NULL COMMENT '更新人ID',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` ENUM('NO','YES') DEFAULT 'NO' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_caller_provider` (`caller_service`, `provider_service`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务授权规则表';
