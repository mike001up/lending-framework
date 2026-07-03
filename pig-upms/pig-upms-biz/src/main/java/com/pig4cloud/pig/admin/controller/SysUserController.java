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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.ResetPasswordDTO;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.api.vo.UserExcelVO;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.admin.convertor.UserConverter;
import com.pig4cloud.pig.admin.service.SysOauthClientDetailsService;
import com.pig4cloud.pig.admin.service.SysPermissionService;
import com.pig4cloud.pig.admin.service.SysRolePermissionService;
import com.pig4cloud.pig.admin.service.SysRoleService;
import com.pig4cloud.pig.admin.service.SysTenantService;
import com.pig4cloud.pig.admin.service.SysUserHierarchyService;
import com.pig4cloud.pig.admin.service.SysUserRoleService;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.util.StringUtil;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lengleng
 * @date 2018/12/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Tag(description = "user", name = "用户管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserController {

	private final SysUserService userService;
	private final SysRoleService roleService;
	private final SysUserRoleService userRoleService;
	private final SysOauthClientDetailsService clientService;
	private final UserConverter userConverter;
	private final SysUserHierarchyService userHierarchyService;
	private final SysRolePermissionService rolePermissionService;
	private final SysTenantService tenantService;

	/**
	 * 获取指定用户全部信息
	 * 用于 auth 进行用户验证
	 * @return 用户信息
	 */
	@Inner
	@RequireServiceAuth
	@GetMapping(value = { "/info/query" })
	public R<PigUserDTO> info(@RequestParam String username) {
		if(StringUtil.isBlank(username)){
			return R.failed("必须指定 用户名/电话号码/电子邮箱");
		}
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda()
			.or(true, w -> w.eq(SysUser::getUsername, username))
			.or(true, w -> w.eq(SysUser::getPhone, username))
			.or(true, w -> w.eq(SysUser::getEmail, username)));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, new StringBuilder().append("用户名:").append(user.getUsername()).append("/").append("电话号码:").append(user.getPhone()).append("/").append("电子邮箱:").append(user.getEmail())));
		}
		List<SysUserHierarchy> userHierarchies = userHierarchyService.list(Wrappers.<SysUserHierarchy>lambdaQuery().eq(SysUserHierarchy::getDescendant, user.getUserId()).orderByDesc(SysUserHierarchy::getDepth));
		SysUserHierarchy maxDepthHierarchy = Stream.ofNullable(userHierarchies)
        .flatMap(Collection::stream)
        .reduce((first, second) -> second) // 取最后一个元素
        .orElse(null);
		List<SysRole> roles = userRoleService.selectGrantRoles(user.getUserId());
		List<Long> roleIds = Stream.ofNullable(roles)
        .flatMap(List::stream)
        .map(SysRole::getRoleId)
        .collect(Collectors.toList());
		List<SysPermission> permissions = rolePermissionService.selectGrantPermission(user.getUserId());
		List<String> persmissionCodes = Stream.ofNullable(permissions)
        .flatMap(List::stream)
        .map(SysPermission::getPermCode)
        .collect(Collectors.toList());
		PigUserDTO pigUser = userConverter.toPigUserVO(user);
		pigUser.setTenantId(maxDepthHierarchy == null?null:maxDepthHierarchy.getAncestor());
		pigUser.setRoles(roleIds);
		pigUser.setPermissions(persmissionCodes);
		return R.ok(pigUser);
	}


	/**
	 * 获取当前用户全部信息
	 * @return 用户信息
	 */
	@GetMapping(value = { "/info" })
	public R info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_QUERY_ERROR));
		}
		return R.ok(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/details/{id}")
	public R user(@PathVariable Long id) {
		SysUser sysUser = userService.getById(id);		
		UserVO userVO = userConverter.toVo(sysUser);
		return R.ok(userVO);
	}

	/**
	 * 查询用户信息
	 * @param query 查询条件
	 * @return 不为空返回用户名
	 */
	@Inner(value = false)
	@RequireServiceAuth
	@GetMapping("/details")
	public R getDetails(@ParameterObject UserDTO query) {
		SysUser sysUser = userService.getOne(Wrappers.query(query), false);
		return R.ok(sysUser == null ? null : CommonConstants.SUCCESS);
	}

	/**
	 * 删除用户信息
	 * @param ids ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping
	@HasPermission("sys_user_del")
	@Operation(summary = "删除用户", description = "根据ID删除用户")
	public R userDel(@RequestBody Long[] ids) {
		return R.ok(userService.deleteUserByIds(ids));
	}

	/**
	 * 添加用户
	 * 一般适合后台管理新增新用户
	 * 1，新增加用户记录
	 * 2，授权角色
	 * 3，授权客户端
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@HasPermission("sys_user_add")
	public R user(@Valid @RequestBody UserDTO userDto) {
		long count = userService.count(Wrappers.<SysUser>lambdaQuery()
			.eq(SysUser::getUsername, userDto.getUsername())
			.or(StringUtil.isNotBlank(userDto.getPhone()), w -> w.eq(SysUser::getPhone, userDto.getPhone()))
			.or(StringUtil.isNotBlank(userDto.getEmail()), w -> w.eq(SysUser::getEmail, userDto.getEmail())));
		if(count > 0){
			return R.failed("用户名/电话号码/邮箱 已存在");
		}
		if(CollectionUtils.isNotEmpty(userDto.getRoles())){
			long roleCount = roleService.count(Wrappers.<SysRole>lambdaQuery()
			.in(CollectionUtil.isNotEmpty(userDto.getRoles()), SysRole::getRoleId, userDto.getRoles()));
			if (roleCount != userDto.getRoles().size()) {
				return R.failed("请确保输入的角色正确");
			}
		}
		if(CollectionUtils.isNotEmpty(userDto.getClientIds())){
			long clientCount = clientService.count(Wrappers.<SysOauthClientDetails>lambdaQuery()
			.in(CollectionUtil.isNotEmpty(userDto.getClientIds()), SysOauthClientDetails::getId, userDto.getClientIds()));
			if (clientCount != userDto.getClientIds().size()) {
				return R.failed("请确保输入的终端应用正确");
			}
		}
		
		if(userDto.getTenantId() != null){
			boolean tenantExisting = tenantService.exists(Wrappers.<SysTenant>lambdaQuery()
			.eq(SysTenant::getId, userDto.getTenantId()));
			if (!tenantExisting) {
				return R.failed("请确保输入的租户正确");
			}
		}
		
		SysUser sysUser = userService.saveUser(userDto);
		if(sysUser == null){
			return R.failed();
		}
		return R.ok(userConverter.toVo(sysUser));
	}

	/**
	 * 更新用户信息
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@HasPermission("sys_user_edit")
	public R<UserVO> updateUser(@Valid @RequestBody UserDTO userDto) {
		if(userDto == null || userDto.getUserId() == null){
			return R.failed("请输入正确的用户ID");
		}
		return R.ok(userConverter.toVo(userService.updateUser(userDto)));
	}

	/**
	 * 分页查询用户
	 * @param page 参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getUserPage(@ParameterObject Page page, @ParameterObject UserDTO userDTO) {
		return R.ok(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 * @param userDto userDto
	 * @return success/false
	 */
	// @SysLog("修改个人信息")
	// @PutMapping("/edit")
	// public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
	// 	return userService.updateUserInfo(userDto);
	// }

	/**
	 * 导出excel 表格
	 * @param userDTO 查询条件
	 * @return
	 */
	@ResponseExcel
	@GetMapping("/export")
	@HasPermission("sys_user_export")
	public List export(UserDTO userDTO) {
		return userService.listUser(userDTO);
	}

	/**
	 * 导入用户
	 * @param excelVOList 用户列表
	 * @param bindingResult 错误信息列表
	 * @return R
	 */
	@PostMapping("/import")
	@HasPermission("sys_user_export")
	public R importUser(@RequestExcel List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		return userService.importUser(excelVOList, bindingResult);
	}

	/**
	 * 锁定指定用户
	 * @param username 用户名
	 * @return R
	 */
	@PutMapping("/lock/{username}")
	@HasPermission("sys_user_lock")
	public R lockUser(@PathVariable String username) {
		return userService.lockUser(username);
	}

	@PutMapping("/unlock/{username}")
	@HasPermission("sys_user_lock")
	public R unlockUser(@PathVariable String username) {
		return userService.unlockUser(username);
	}

	@PutMapping("/password")
	@HasPermission("sys_user_edit")
	public R password(@RequestBody UserDTO userDto) {
		String username = SecurityUtils.getUser().getUsername();
		userDto.setUsername(username);
		return userService.changePassword(userDto);
	}

	@SysLog("管理员重置用户密码")
	@PutMapping("/password/reset")
	@HasPermission("sys_user_reset_password")
	public R resetPassword(@RequestBody ResetPasswordDTO dto) {
		return userService.resetPassword(dto.getUserId(), dto.getNewPassword());
	}

	@PostMapping("/check")
	public R check(String password) {
		return userService.checkPassword(password);
	}

	@SysLog("关停用户账户")
	@PostMapping("/{id}/close")
	@HasPermission("sys_user_close")
	public R closeAccount(@PathVariable Long id) {
		return userService.closeAccount(id);
	}

	@SysLog("恢复用户账户")
	@PostMapping("/{id}/restore")
	@HasPermission("sys_user_restore")
	public R restoreAccount(@PathVariable Long id) {
		return userService.restoreAccount(id);
	}

	@SysLog("启用用户")
	@PutMapping("/{id}/enable")
	@HasPermission("sys_user_edit")
	public R enableUser(@PathVariable Long id) {
		return userService.enableUser(id);
	}

	@SysLog("禁用用户")
	@PutMapping("/{id}/disable")
	@HasPermission("sys_user_edit")
	public R disableUser(@PathVariable Long id) {
		return userService.disableUser(id);
	}

	@SysLog("更新用户头像")
	@PutMapping("/{id}/avatar")
	public R updateAvatar(@PathVariable Long id, @RequestParam String avatarUrl) {
		return userService.updateAvatar(id, avatarUrl);
	}

}
