package com.pig4cloud.pig.gateway.fegin;

import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "remotePermService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemotePermService {

	@InternalFeign
	@GetMapping("/user/info/query")
	R<UserInfo> getUserInfo(@RequestParam("username") String username);

	@InternalFeign
	@GetMapping("/permission/authorize-rules")
	R<List<SysPermission>> getAuthorizeRules();

}
