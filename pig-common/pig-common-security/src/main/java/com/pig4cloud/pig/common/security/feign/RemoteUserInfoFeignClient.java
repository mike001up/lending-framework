package com.pig4cloud.pig.common.security.feign;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.service.UserInfoService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 默认的 UserInfoService Feign 实现（供其他服务调用 pig-upms-biz）
 */
@FeignClient(contextId = "remoteUserInfoService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteUserInfoFeignClient extends UserInfoService{


    @GetMapping("/user/info/query")
    @InternalFeign
    R<PigUserDTO> loadUserByUsername(@RequestParam("username") String username);
}
