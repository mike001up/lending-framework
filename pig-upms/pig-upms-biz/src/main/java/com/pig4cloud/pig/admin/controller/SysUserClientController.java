package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.api.dto.BatchGrantClientDTO;
import com.pig4cloud.pig.admin.api.dto.GrantClientDTO;
import com.pig4cloud.pig.admin.api.dto.RevokeClientDTO;
import com.pig4cloud.pig.admin.api.entity.SysUserClient;
import com.pig4cloud.pig.admin.service.SysUserClientService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user-client")
@Tag(description = "userClient", name = "用户客户端授权模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserClientController {

	private final SysUserClientService sysUserClientService;

	@GetMapping("/{userId}")
	public R findByUserId(@PathVariable Long userId) {
		return R.ok(sysUserClientService.findByUserId(userId));
	}

	@SysLog("授权用户客户端")
	@PostMapping
	@HasPermission("sys_user_client_grant")
	public R grantClient(@RequestBody GrantClientDTO dto) {
		sysUserClientService.grantClient(dto.getUserId(), dto.getClientId());
		return R.ok();
	}

	@SysLog("批量授权用户客户端")
	@PostMapping("/batch")
	@HasPermission("sys_user_client_grant")
	public R batchGrantClient(@RequestBody BatchGrantClientDTO dto) {
		dto.getClientIds().forEach(clientId -> sysUserClientService.grantClient(dto.getUserId(), clientId));
		return R.ok();
	}

	@SysLog("解除用户客户端授权")
	@DeleteMapping
	@HasPermission("sys_user_client_revoke")
	public R revokeClient(@RequestBody RevokeClientDTO dto) {
		sysUserClientService.revokeClient(dto.getUserId(), dto.getClientId());
		return R.ok();
	}

}