package com.pig4cloud.pig.admin.api.feign;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteIPLimitService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteIPLimitService {

	@GetMapping("/ipLimit/check")
	@InternalFeign
	R<Boolean> check(@RequestParam("clientId") String clientId,
							@RequestParam("remoteIP") String remoteIP);

}