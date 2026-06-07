package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.api.dto.BatchGrantScopeDTO;
import com.pig4cloud.pig.admin.api.dto.GrantScopeDTO;
import com.pig4cloud.pig.admin.api.dto.RevokeScopeDTO;
import com.pig4cloud.pig.admin.api.entity.SysUserScope;
import com.pig4cloud.pig.admin.service.SysUserScopeService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/user-scope")
@Tag(description = "userScope", name = "用户范围授权模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserScopeController {

	private final SysUserScopeService sysUserScopeService;

	@GetMapping("/{userId}")
	public R findByUserId(@PathVariable Long userId) {
		return R.ok(sysUserScopeService.findByUserId(userId));
	}

	@SysLog("授权用户范围")
	@PostMapping
	@HasPermission("sys_user_scope_grant")
	public R grantScope(@RequestBody GrantScopeDTO dto) {
		sysUserScopeService.grantScope(dto.getUserId(), dto.getScopeId(), SecurityUtils.getUser().getId());
		return R.ok();
	}

	@SysLog("批量授权用户范围")
	@PostMapping("/batch")
	@HasPermission("sys_user_scope_grant")
	public R batchGrantScope(@RequestBody BatchGrantScopeDTO dto) {
		Long grantedBy = SecurityUtils.getUser().getId();
		dto.getScopeIds().forEach(scopeId -> sysUserScopeService.grantScope(dto.getUserId(), scopeId, grantedBy));
		return R.ok();
	}

	@SysLog("解除用户范围授权")
	@DeleteMapping
	@HasPermission("sys_user_scope_revoke")
	public R revokeScope(@RequestBody RevokeScopeDTO dto) {
		sysUserScopeService.revokeScope(dto.getUserId(), dto.getScopeId());
		return R.ok();
	}

}