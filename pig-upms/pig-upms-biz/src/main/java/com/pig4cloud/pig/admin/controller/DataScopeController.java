package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.provider.UpmsDataScopeProvider;
import com.pig4cloud.pig.common.core.data.DataScopeConfig;
import com.pig4cloud.pig.common.core.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据权限对外接口
 * 仅供内部服务调用，网关层应配置为内网白名单
 */
@RestController
@RequestMapping("/data-scope")
@RequiredArgsConstructor
public class DataScopeController {

    private final UpmsDataScopeProvider provider;

     @GetMapping("/config")
    public R<DataScopeConfig> getScopeConfig(@RequestParam Long userId,
                                              @RequestParam String businessTable) {
        return R.ok(provider.getScopeConfig(userId, businessTable));
    }

    @GetMapping("/owner-values")
    public R<List<String>> listOwnerValues(@RequestParam Long userId,
                                            @RequestParam String hierarchyType,
                                            @RequestParam String ownerColumn) {
        return R.ok(provider.listOwnerValues(userId, hierarchyType, ownerColumn));
    }
}