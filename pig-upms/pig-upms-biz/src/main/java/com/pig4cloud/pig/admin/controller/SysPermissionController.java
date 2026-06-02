package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.service.SysPermissionService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/permission")
@Tag(description = "permission", name = "权限资源管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysPermissionController {

	private final SysPermissionService sysPermissionService;

	@GetMapping("/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysPermissionService.getById(id));
	}

	@GetMapping("/tree")
	public R getTree(Long parentId, String name) {
		return R.ok(sysPermissionService.treePermission(parentId, name));
	}

	@SysLog("新增权限资源")
	@PostMapping
	@HasPermission("sys_permission_add")
	public R save(@Valid @RequestBody SysPermission sysPermission) {
		return R.ok(sysPermissionService.save(sysPermission));
	}

	@SysLog("删除权限资源")
	@DeleteMapping("/{id}")
	@HasPermission("sys_permission_del")
	public R removeById(@PathVariable Long id) {
		return sysPermissionService.removePermissionById(id);
	}

	@SysLog("更新权限资源")
	@PutMapping
	@HasPermission("sys_permission_edit")
	public R update(@Valid @RequestBody SysPermission sysPermission) {
		return R.ok(sysPermissionService.updatePermissionById(sysPermission));
	}

	@Inner
	@RequireServiceAuth
	@GetMapping("/authorize-rules")
	public R<List<SysPermission>> getAuthorizeRules() {
		return R.ok(sysPermissionService.listAuthorizeRules());
	}

}