package com.pig4cloud.pig.common.core.data;

import java.util.List;

/**
 * 数据权限数据提供者（SPI）
 * 由业务模块实现，切面通过此接口获取用户的数据权限配置
 */
public interface DataScopeProvider {

    /**
     * 查询用户对指定业务表的数据权限配置
     *
     * @param userId        用户ID
     * @param businessTable 业务表名
     * @return 数据权限配置；null 表示无配置
     */
    DataScopeConfig getScopeConfig(Long userId, String businessTable);

    /**
     * 查询用户在指定层级下、对应 owner_column 字段的所有值（含自己）
     * 例：
     *   ownerColumn = "user_id"   → 返回 ["1", "2", "3"]
     *   ownerColumn = "create_by" → 返回 ["zhangsan", "lisi", "wangwu"]
     *
     * @param userId        用户ID
     * @param hierarchyType 层级类型
     * @param ownerColumn   归属字段名（决定返回值类型）
     * @return 归属值列表
     */
    List<String> listOwnerValues(Long userId, String hierarchyType, String ownerColumn);
}