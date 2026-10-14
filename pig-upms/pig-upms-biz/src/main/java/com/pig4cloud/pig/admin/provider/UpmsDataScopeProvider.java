package com.pig4cloud.pig.admin.provider;

import com.pig4cloud.pig.admin.api.entity.RoleDataScope;
import com.pig4cloud.pig.admin.mapper.RoleDataScopeMapper;
import com.pig4cloud.pig.admin.mapper.SysUserHierarchyMapper;
import com.pig4cloud.pig.admin.mapper.SysUserQueryMapper;
import com.pig4cloud.pig.common.core.data.DataScopeConfig;
import com.pig4cloud.pig.common.core.data.DataScopeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpmsDataScopeProvider implements DataScopeProvider {

    private final RoleDataScopeMapper roleDataScopeMapper;
    private final SysUserHierarchyMapper userHierarchyMapper;
    private final SysUserQueryMapper sysUserQueryMapper;

    private static final String USER_ID_COLUMN = "user_id";

    @Override
    public DataScopeConfig getScopeConfig(Long userId, String businessTable) {
        List<RoleDataScope> scopes = roleDataScopeMapper.listByUserId(userId);
        if (CollectionUtils.isEmpty(scopes)) {
            return null;
        }

        RoleDataScope picked = scopes.stream()
                .filter(s -> businessTable.equals(s.getBusinessTable()))
                .max(Comparator.comparingInt(s -> priority(s.getScopeType())))
                .orElse(null);

        if (picked == null) {
            return null;
        }

        DataScopeConfig config = new DataScopeConfig();
        config.setScopeType(picked.getScopeType());
        config.setOwnerColumn(picked.getOwnerColumn());
        config.setHierarchyType(picked.getHierarchyType());
        config.setCustomSql(picked.getCustomSql());
        return config;
    }

    @Override
    public List<String> listOwnerValues(Long userId, String hierarchyType, String ownerColumn) {
        List<Long> ids = userHierarchyMapper.listDescendants(userId, hierarchyType);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        // user_id 类型：直接返回 ID 字符串
        if (USER_ID_COLUMN.equals(ownerColumn)) {
            return ids.stream().map(String::valueOf).collect(Collectors.toList());
        }

        // 其他类型（create_by / owner_id 等）：返回 username 列表
        return sysUserQueryMapper.listUsernamesByIds(ids);
    }

    private int priority(String scopeType) {
        return switch (scopeType) {
            case "ALL" -> 4;
            case "SELF_AND_CHILD" -> 3;
            case "SELF" -> 2;
            case "CUSTOM" -> 1;
            default -> 0;
        };
    }
}