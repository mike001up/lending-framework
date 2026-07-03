package com.pig4cloud.pig.admin.api.feign;

import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(contextId = "remotePermService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemotePermService {

	@InternalFeign
	@GetMapping("/permission/authorize-rules")
	R<List<SysPermission>> getAuthorizeRules();

	@NoToken
	@GetMapping("/user/info/{username}")
	R<UserInfo> getUserInfo(@PathVariable("username") String username);

}