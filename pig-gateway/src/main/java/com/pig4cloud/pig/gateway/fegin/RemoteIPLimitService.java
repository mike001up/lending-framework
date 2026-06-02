package com.pig4cloud.pig.gateway.fegin;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteIPlimitService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteIPLimitService {

	@InternalFeign
	@GetMapping("/bizIpLimit/isValidIP")
	Boolean isValidIP(@RequestParam("remoteIP") String remoteIP);

}
