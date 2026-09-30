/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.SysRoleDTO;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.vo.RoleVO;
import com.pig4cloud.pig.admin.convertor.RoleConverter;
import com.pig4cloud.pig.admin.service.SysRoleService;
import com.pig4cloud.pig.admin.service.SysUserRoleService;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2020-02-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Tag(description = "role", name = "角色管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysRoleController {

	private final SysRoleService sysRoleService;

	private final SysUserRoleService sysUserRoleService;

	private final RoleConverter roleConverter;

	private final SysUserService sysUserService;

	/**
	 * 通过ID查询角色信息
	 * @param id ID
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	@HasPermission("sys:role:details")
	public R<RoleVO> details(@PathVariable Long id) {
		return R.ok(roleConverter.toVo(sysRoleService.getById(id)));
	}

	/**
	 * 添加角色
	 * @param sysRole 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping
	@HasPermission("sys:role:add")
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R save(@Validated(CreateGroup.class) @RequestBody SysRoleDTO sysRole) {
		return sysRoleService.saveRole(sysRole);
	}

	/**
	 * 修改角色
	 * @param sysRole 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping
	@HasPermission("sys:role:edit")
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R update(@Validated(UpdateGroup.class) @RequestBody SysRoleDTO sysRole) {
		return R.ok(sysRoleService.updateById(roleConverter.toEntity(sysRole)));
	}

	/**
	 * 删除角色
	 * @param ids
	 * @return
	 */
	@SysLog("删除角色")
	@DeleteMapping
	@HasPermission("sys:role:del")
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R removeById(@RequestBody Long[] ids) {
		return R.ok(sysRoleService.removeRoleByIds(ids));
	}

	/**
	 * 分页查询角色信息
	 * @param page 分页对象
	 * @param role 查询条件
	 * @return 分页对象
	 */
	@GetMapping("/page")
	@HasPermission("sys:role:page")
	public R<IPage<RoleVO>> getRolePage(Page page, SysRoleDTO roleDTO) {
		Page<SysRole> pages = sysRoleService.page(page, Wrappers.<SysRole>lambdaQuery()
			.like(StrUtil.isNotBlank(roleDTO.getRoleName()), SysRole::getRoleName, roleDTO.getRoleName())
			.eq(StrUtil.isNotBlank(roleDTO.getRoleCode()), SysRole::getRoleCode, roleDTO.getRoleCode())
		);
		IPage<RoleVO> pageVO = pages.convert(role->{
			return roleConverter.toVo(role);
		});
		return R.ok(pageVO);
	}

	/**
	 * 更新角色权限
	 * @param role 角色对象
	 * @return success、false
	 */
	@SysLog("更新角色权限")
	@PutMapping("/permission")
	@HasPermission("sys:role:perm")
	public R saveRolePermissions(@Validated(UpdateGroup.class) @RequestBody SysRoleDTO role) {
		return R.ok(sysRoleService.updateRolePerm(role));
	}

	/**
	 * 通过角色ID 查询角色列表
	 * @param roleIdList 角色ID
	 * @return
	 */
	@GetMapping("/getRoleList")
	@HasPermission("sys:role:list")
	public R<List<RoleVO>> getRoleList(@RequestBody List<Long> roleIdList) {
		if (CollUtil.isEmpty(roleIdList)) {
        	return R.ok(Collections.emptyList());
    	}
		List<SysRole> sysRoles = sysRoleService.findRolesByRoleIds(roleIdList, CollUtil.join(roleIdList, StrUtil.UNDERLINE));
		List<RoleVO> roles = Optional.ofNullable(sysRoles).orElse(Collections.emptyList()).stream()
			.map(role->{
				return roleConverter.toVo(role);
			}).collect(Collectors.toList());
		return R.ok(roles);
	}

	/**
	 * 将角色赋予制定用户
	 * @param userId 用户ID
	 * @param roleIdList 角色ID
	 * @return
	 */
	@PutMapping("/grant/{userId}")
	@HasPermission("sys:role:user:grant")
	public R grantUserRole(@PathVariable("userId") Long userId, @RequestBody List<Long> roleIdList) {
		long userCount = sysUserService.count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserId, userId));
		if(userCount == 0){
			return R.failed("请指定用户");
		}
		sysUserRoleService.grantUserRole(userId, roleIdList);
		return R.ok();
	}

	// /**
	//  * 导出excel 表格
	//  * @return
	//  */
	// @ResponseExcel
	// @GetMapping("/export")
	// @HasPermission("sys:role:export")
	// public List<RoleExcelVO> export() {
	// 	return sysRoleService.listRole();
	// }

	// /**
	//  * 导入角色
	//  * @param excelVOList 角色列表
	//  * @param bindingResult 错误信息列表
	//  * @return ok fail
	//  */
	// @PostMapping("/import")
	// @HasPermission("sys_role_export")
	// public R importRole(@RequestExcel List<RoleExcelVO> excelVOList, BindingResult bindingResult) {
	// 	return sysRoleService.importRole(excelVOList, bindingResult);
	// }

}
