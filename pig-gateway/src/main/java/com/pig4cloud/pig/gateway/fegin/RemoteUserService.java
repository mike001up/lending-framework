

package com.pig4cloud.pig.gateway.fegin;


import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteUserService {


    @GetMapping("/token/getUser")
    Map<String, Object> getUser(@RequestHeader(SecurityConstants.FROM) String from, @RequestParam("token") String token);
}
