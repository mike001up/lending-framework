package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.BatchCheckPermissionDTO;
import com.pig4cloud.pig.admin.api.dto.CheckPermissionDTO;
import com.pig4cloud.pig.admin.api.dto.SysPermissionDTO;
import com.pig4cloud.pig.admin.api.dto.SysRoleDTO;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.vo.PermissionVO;
import com.pig4cloud.pig.admin.api.vo.RoleVO;
import com.pig4cloud.pig.admin.convertor.PermissionConverter;
import com.pig4cloud.pig.admin.service.SysPermissionService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;

import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/permission")
@Tag(description = "permission", name = "权限资源管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysPermissionController {

	private final SysPermissionService sysPermissionService;
	private final PermissionConverter permissionConverter;

	@GetMapping("/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysPermissionService.getById(id));
	}

	@GetMapping("/tree")
	public R<List<TreeNode<Long>>> getTree(Long parentId, String name) {
		return R.ok(sysPermissionService.treePermission(parentId, name));
	}

	/**
	 * 分页查询权限信息
	 * @param page 分页对象
	 * @param role 查询条件
	 * @return 分页对象
	 */
	@GetMapping("/page")
	@HasPermission("sys:permission:page")
	public R<IPage<PermissionVO>> getPermissionPage(Page page, SysPermissionDTO permissionDTO) {
		Page<SysPermission> pages = sysPermissionService.page(page, Wrappers.<SysPermission>lambdaQuery()
			.like(StrUtil.isNotBlank(permissionDTO.getParentPermission()), SysPermission::getPermission, permissionDTO.getParentPermission())
			.eq(StrUtil.isNotBlank(permissionDTO.getPermCode()), SysPermission::getPermCode, permissionDTO.getPermCode())
			.eq(permissionDTO.getResourceType() != null, SysPermission::getResourceType, permissionDTO.getResourceType())
		);
		IPage<PermissionVO> pageVO = pages.convert(permission->{
			return permissionConverter.toVo(permission);
		});
		return R.ok(pageVO);
	}
	
	@SysLog("新增权限资源")
	@PostMapping
	@HasPermission("sys:permission:add")
	public R save(@Validated(CreateGroup.class) @RequestBody SysPermissionDTO sysPermission) {
		return sysPermissionService.savePermission(permissionConverter.toEntity(sysPermission));
	}

	@SysLog("删除权限资源")
	@DeleteMapping("/{id}")
	@HasPermission("sys:permission:del")
	public R removeById(@PathVariable Long id) {
		return sysPermissionService.removePermissionById(id);
	}

	@SysLog("更新权限资源")
	@PutMapping
	@HasPermission("sys:permission:edit")
	public R update(@Validated(UpdateGroup.class) @RequestBody SysPermissionDTO sysPermission) {
		SysPermission permission = permissionConverter.toEntity(sysPermission);
		permission.setParentId(null);
		permission.setPermCode(null);
		permission.setPermission(null);
		return R.ok(sysPermissionService.updatePermissionById(permission));
	}

	@Inner
	@RequireServiceAuth
	@GetMapping("/authorize-rules")
	public R<List<SysPermission>> getAuthorizeRules() {
		return R.ok(sysPermissionService.listAuthorizeRules());
	}

	@Inner
	@RequireServiceAuth
	@GetMapping("/check/{userId}/{permCode}")
	@HasPermission("sys:permission:check")
	public R checkPermission(@PathVariable("userId")Long userId, @PathVariable("permCode")String permCode) {
		return R.ok(sysPermissionService.checkPermission(userId, permCode));
	}

	// @PostMapping("/batch-check")
	// public R batchCheckPermission(@RequestBody BatchCheckPermissionDTO dto) {
	// 	return R.ok(sysPermissionService.batchCheckPermission(dto.getUserId(), dto.getPermCodes()));
	// }

}