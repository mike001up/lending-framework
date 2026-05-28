package com.pig4cloud.pig.common.security.service;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteServiceAuthClient", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteServiceAuthClient {

	@GetMapping("/serviceAuth/check")
	Boolean checkServiceAuth(@RequestHeader(SecurityConstants.FROM) String from,
			@RequestParam("callerServiceId") String callerServiceId,
			@RequestParam("providerServiceId") String providerServiceId,
			@RequestParam("requestPath") String requestPath);

}
