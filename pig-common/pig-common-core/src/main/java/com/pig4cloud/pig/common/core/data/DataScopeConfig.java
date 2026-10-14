package com.pig4cloud.pig.common.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据权限配置（跨模块传输）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataScopeConfig {

    /** 数据范围类型：ALL / SELF / SELF_AND_CHILD / CUSTOM */
    private String scopeType;

    /** 归属人字段名，如 create_by */
    private String ownerColumn;

    /** SELF_AND_CHILD 时的层级类型，如 agency */
    private String hierarchyType;

    /** CUSTOM 时的自定义 SQL */
    private String customSql;
}