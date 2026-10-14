package com.pig4cloud.pig.common.data.provider;

import com.pig4cloud.pig.common.core.data.DataScopeConfig;
import com.pig4cloud.pig.common.core.data.DataScopeProvider;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.data.feign.DataScopeFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collections;
import java.util.List;

/**
 * 远程实现：通过 Feign 调用 upms 获取数据权限
 * 适用于"非 upms 服务"
 */
@Slf4j
@RequiredArgsConstructor
public class RemoteDataScopeProvider implements DataScopeProvider {

    private final DataScopeFeignClient feignClient;

    @Override
    // @Cacheable(value = "dataScope:config",
    //            key = "#userId + ':' + #businessTable",
    //            unless = "#result == null")
    public DataScopeConfig getScopeConfig(Long userId, String businessTable) {
        try {
            R<DataScopeConfig> result = feignClient.getScopeConfig(userId, businessTable);
            return result.getData();
        } catch (Exception e) {
            log.error("远程获取数据权限失败，拒绝访问。userId={}, table={}",
                    userId, businessTable, e);
            return null;
        }
    }

    @Override
    // @Cacheable(value = "dataScope:ownerValues",
    //            key = "#userId + ':' + #hierarchyType + ':' + #ownerColumn")
    public List<String> listOwnerValues(Long userId, String hierarchyType, String ownerColumn) {
        try {
            R<List<String>> result = feignClient.listOwnerValues(userId, hierarchyType, ownerColumn);
            return result.getData() != null ? result.getData() : Collections.emptyList();
        } catch (Exception e) {
            log.error("远程获取归属值失败，降级为仅自己。userId={}", userId, e);
            return Collections.emptyList();
        }
    }
}