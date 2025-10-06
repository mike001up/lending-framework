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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.vo.UserExcelVO;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

	/**
	 * 获取指定用户全部信息
	 * @return 用户信息
	 */
	@Inner
	@GetMapping(value = { "/info/query" })
	public R info(@RequestParam(required = false) String username, @RequestParam(required = false) String phone) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda()
			.eq(StrUtil.isNotBlank(username), SysUser::getUsername, username)
						.ne(SysUser::getUserType, CommonConstants.FRONTEND)
			.eq(StrUtil.isNotBlank(phone), SysUser::getPhone, phone));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, username));
		}
		UserInfo userInfo = userService.findUserInfo(user);
		return R.ok(userInfo);
	}

	@Inner
	@GetMapping(value = { "/info/queryApp" })
	public R infoApp(@RequestParam(required = false) String username, @RequestParam(required = false) String phone) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda()
			.eq(StrUtil.isNotBlank(username), SysUser::getUsername, username)
						.eq(SysUser::getUserType, CommonConstants.FRONTEND)
			.eq(StrUtil.isNotBlank(phone), SysUser::getPhone, phone));
		if (user == null) {
			return R.failedByCode(101, MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, username));
		}
		if (user.getIsMagLogout()) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.USER_IS_BLACK, username));
		}
		UserInfo userInfo = userService.findUserInfo(user);
		return R.ok(userInfo);
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
		return R.ok(userService.selectUserVoById(id));
	}

	/**
	 * 查询用户信息
	 * @param query 查询条件
	 * @return 不为空返回用户名
	 */
	@Inner(value = false)
	@GetMapping("/details")
	public R getDetails(@ParameterObject SysUser query) {
		SysUser sysUser = userService.getOne(Wrappers.query(query), false);
		return R.ok(sysUser == null ? null : sysUser);
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
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@HasPermission("sys_user_add")
	public R user(@RequestBody UserDTO userDto) {
		return R.ok(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@HasPermission("sys_user_edit")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
		return R.ok(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 * @param page 参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getUserPage(@ParameterObject Page page, @ParameterObject UserDTO userDTO) {
		IPage<UserVO> usersWithRolePage = userService.getUsersWithRolePage(page, userDTO);
		// 只有admin账号才能看到admin账号，才能修改admin
		PigUser user = SecurityUtils.getUser();
		if (!user.getUsername().equalsIgnoreCase(SecurityConstants.ADMIN)) {
			// 过滤掉admin账号，普通用户不可见
			List<UserVO> filteredRecords = usersWithRolePage.getRecords().stream()
					.filter(u -> !u.getUsername().equalsIgnoreCase(SecurityConstants.ADMIN))
					.collect(Collectors.toList());
			usersWithRolePage.setRecords(filteredRecords);
		}
		return R.ok(usersWithRolePage);
	}

	/**
	 * 修改个人信息
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

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
	public R lockUser(@PathVariable String username) {
		return userService.lockUser(username);
	}

	@PutMapping("/password")
	public R password(@RequestBody UserDTO userDto) {
		String username = SecurityUtils.getUser().getUsername();
		userDto.setUsername(username);
		return userService.changePassword(userDto);
	}

	@PostMapping("/check")
	public R check(String password) {
		return userService.checkPassword(password);
	}


	//下面是会员管理接口

	/**
	 * 会员分页查询
	 */
	@GetMapping("/memberList")
	@HasPermission("sys_user_memberList")
	public R members(@ParameterObject Page page, @ParameterObject UserDTO userDTO) {
		IPage<UserVO> usersWithRolePage = userService.getMembers(page, userDTO);
		return R.ok(usersWithRolePage);
	}


	/**
	 * 会员模糊搜索
	 */
	@GetMapping("/membersByUsername")
	public R membersByUsername(String username) {
		return userService.membersByUsername(username);
	}



	@SysLog("新增会员")
	@PostMapping("/addMember")
	@HasPermission("sys_user_addMember")
	public R membersAdd(@RequestBody UserDTO userDto) {
		return userService.saveMembers(userDto);
	}


	@SysLog("注销/撤销注销")
	@GetMapping("/magLogout")
	@HasPermission("sys_user_magLogout")
	public R magLogout(Boolean isMagLogout,Long userId) {
		SysUser user = userService.getById(userId);
		return userService.isMagLogoutByUserId(isMagLogout, userId, user.getUsername());
	}


	@SysLog("拉黑/撤销拉黑")
	@GetMapping("/isBlack")
	@HasPermission("sys_user_isBlack")
	public R isBlack(Boolean isBlack,Long userId) {
		SysUser user = userService.getById(userId);
		return userService.isBlackByUserId(isBlack, userId, user.getUsername());
	}



}
