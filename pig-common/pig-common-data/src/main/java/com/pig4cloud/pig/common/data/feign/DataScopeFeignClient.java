package com.pig4cloud.pig.common.data.feign;

import com.pig4cloud.pig.common.core.data.DataScopeConfig;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 数据权限远程调用
 */
@FeignClient(
    contextId = "dataScopeFeignClient",
    name = "${data-scope.feign.service-name:pig-upms-biz}",
    path = "/data-scope"
)
public interface DataScopeFeignClient {

    @GetMapping("/config")
    R<DataScopeConfig> getScopeConfig(@RequestParam("userId") Long userId,
                                       @RequestParam("businessTable") String businessTable);

    @GetMapping("/owner-values")
    R<List<String>> listOwnerValues(@RequestParam("userId") Long userId,
                                     @RequestParam("hierarchyType") String hierarchyType,
                                     @RequestParam("ownerColumn") String ownerColumn);
}