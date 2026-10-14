package com.pig4cloud.pig.common.data.aspect;

import com.pig4cloud.pig.common.core.data.DataScopeConfig;
import com.pig4cloud.pig.common.core.data.DataScopeProvider;
import com.pig4cloud.pig.common.data.annotation.DataScope;
import com.pig4cloud.pig.common.data.context.DataScopeContext;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class DataScopeAspect {

    /** ObjectProvider 兼容"未引入 SPI 实现"的场景 */
    private final ObjectProvider<DataScopeProvider> providerProvider;

    /** 特殊字段：使用 userId 而非 username */
    private static final String USER_ID_COLUMN = "user_id";

    @Around("@annotation(dataScope)")
    public Object around(ProceedingJoinPoint pjp, DataScope dataScope) throws Throwable {
        PigUser user = SecurityUtils.getUser();
        if (user == null) {
            return pjp.proceed();
        }

        DataScopeProvider provider = providerProvider.getIfAvailable();
        if (provider == null) {
            // 未引入 SPI 实现，直接放行
            return pjp.proceed();
        }

        String scopeSql = buildScopeSql(user, dataScope, provider);
        if (!StringUtils.hasText(scopeSql)) {
            return pjp.proceed();
        }

        DataScopeContext.set(scopeSql);
        try {
            return pjp.proceed();
        } finally {
            DataScopeContext.clear();
        }
    }

    private String buildScopeSql(PigUser user, DataScope dataScope, DataScopeProvider provider) {
        DataScopeConfig config = provider.getScopeConfig(user.getId(), dataScope.tableName());
        if (config == null) {
            return null;
        }

        String prefix = StringUtils.hasText(dataScope.tableAlias())
                ? dataScope.tableAlias() + "." : "";
        String ownerCol = StringUtils.hasText(dataScope.ownerColumn())
                ? dataScope.ownerColumn()
                : config.getOwnerColumn();

        if (!StringUtils.hasText(ownerCol)) {
            log.warn("数据权限配置缺少 ownerColumn，userId={}, table={}",
                    user.getId(), dataScope.tableName());
            return "1=0";
        }

        return switch (config.getScopeType()) {
            case "ALL" -> "1=1";

            case "SELF" -> prefix + ownerCol + " = " + user.getId();

            case "SELF_AND_CHILD" -> {
                String hierarchyType = StringUtils.hasText(config.getHierarchyType())
                        ? config.getHierarchyType() : "default";
                List<Long> ids = provider.listDescendants(user.getId(), hierarchyType);
                if (CollectionUtils.isEmpty(ids)) {
                    yield prefix + ownerCol + " = " + user.getId();
                }
                yield prefix + ownerCol + " IN ("
                        + ids.stream().map(String::valueOf).collect(Collectors.joining(","))
                        + ")";
            }

            case "CUSTOM" -> StringUtils.hasText(config.getCustomSql())
                    ? config.getCustomSql() : "1=0";

            default -> {
                log.warn("未知的数据范围类型: {}", config.getScopeType());
                yield "1=0";
            }
        };
    }
}