package com.pig4cloud.pig.gateway.fegin;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteUserService {

	@InternalFeign
	@GetMapping("/token/getUser")
	Map<String, Object> getUser(@RequestParam("token") String token);

}
