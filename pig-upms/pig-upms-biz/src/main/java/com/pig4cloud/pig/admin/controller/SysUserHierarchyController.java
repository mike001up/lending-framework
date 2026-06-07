package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.service.SysUserHierarchyService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-hierarchy")
@Tag(description = "userHierarchy", name = "用户层级管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserHierarchyController {

	private final SysUserHierarchyService sysUserHierarchyService;

	@GetMapping("/ancestors/{userId}")
	@HasPermission("sys_user_hierarchy_view")
	public R getAncestors(@PathVariable Long userId) {
		return R.ok(sysUserHierarchyService.getAncestors(userId));
	}

	@GetMapping("/descendants/{userId}")
	@HasPermission("sys_user_hierarchy_view")
	public R getDescendants(@PathVariable Long userId) {
		return R.ok(sysUserHierarchyService.getDescendants(userId));
	}

	@GetMapping("/children/{userId}")
	@HasPermission("sys_user_hierarchy_view")
	public R getDirectChildren(@PathVariable Long userId) {
		return R.ok(sysUserHierarchyService.getDirectChildren(userId));
	}

	@PostMapping
	@HasPermission("sys_user_hierarchy_add")
	public R addRelation(@RequestParam Long ancestor, @RequestParam Long descendant) {
		sysUserHierarchyService.addRelation(ancestor, descendant);
		return R.ok();
	}

	@DeleteMapping
	@HasPermission("sys_user_hierarchy_del")
	public R removeRelation(@RequestParam Long ancestor, @RequestParam Long descendant) {
		sysUserHierarchyService.removeRelation(ancestor, descendant);
		return R.ok();
	}

}
