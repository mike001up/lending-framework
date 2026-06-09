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

package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.dto.RegisterUserDTO;
import com.pig4cloud.pig.common.core.constant.enums.IsDelEnum;
import com.pig4cloud.pig.common.core.constant.enums.UserStatusEnum;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.*;
import com.pig4cloud.pig.admin.api.util.ParamResolver;
import com.pig4cloud.pig.admin.api.vo.UserExcelVO;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.admin.api.feign.RemoteTokenService;
import com.pig4cloud.pig.admin.mapper.SysUserMapper;

import com.pig4cloud.pig.admin.mapper.SysUserRoleMapper;
import com.pig4cloud.pig.admin.service.*;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2017/10/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysPermissionService sysPermissionService;

	private final SysRoleService sysRoleService;



	private final SysUserHierarchyService sysUserHierarchyService;

	private final SysUserRoleMapper sysUserRoleMapper;


	private final CacheManager cacheManager;

	private final RemoteTokenService remoteTokenService;

	/**
	 * 保存用户信息
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUser existing = this.getOne(Wrappers.<SysUser>lambdaQuery()
			.eq(SysUser::getUsername, userDto.getUsername()));
		if (existing != null) {
			throw new RuntimeException(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERNAME_EXISTING, userDto.getUsername()));
		}

		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setIsDel(IsDelEnum.NO);
		sysUser.setCreateBy(userDto.getUsername());
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);

		if (userDto.getTenantId() != null) {
			SysUserHierarchy tenantRel = new SysUserHierarchy();
			tenantRel.setAncestor(userDto.getTenantId());
			tenantRel.setDescendant(sysUser.getUserId());
			tenantRel.setDepth(1);
			tenantRel.setHierarchyType("tenant");
			sysUserHierarchyService.save(tenantRel);
		}

		if (userDto.getAgencyId() != null) {
			SysUserHierarchy agencyRel = new SysUserHierarchy();
			agencyRel.setAncestor(userDto.getAgencyId());
			agencyRel.setDescendant(sysUser.getUserId());
			agencyRel.setDepth(1);
			agencyRel.setHierarchyType("agency");
			sysUserHierarchyService.save(agencyRel);
		}

		SysUserHierarchy selfRel = new SysUserHierarchy();
		selfRel.setAncestor(sysUser.getUserId());
		selfRel.setDescendant(sysUser.getUserId());
		selfRel.setDepth(0);
		selfRel.setHierarchyType("self");
		sysUserHierarchyService.save(selfRel);


		if (CollUtil.isEmpty(userDto.getRoles())) {
			String defaultRole = ParamResolver.getStr("USER_DEFAULT_ROLE");
			if (StrUtil.isNotBlank(defaultRole)) {
				SysRole sysRole = sysRoleService
					.getOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getRoleCode, defaultRole));
				if (sysRole != null) {
					userDto.setRoles(Collections.singletonList(sysRole.getRoleId()));
				}
			}
		}

		if (CollUtil.isNotEmpty(userDto.getRoles())) {
			userDto.getRoles().stream().map(roleId -> {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserId(sysUser.getUserId());
				userRole.setRoleId(roleId);
				return userRole;
			}).forEach(sysUserRoleMapper::insert);
		}
		return Boolean.TRUE;
	}

	/**
	 * 通过查用户的全部信息
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);

		Long tenantId = sysUserHierarchyService.getAncestorByType(sysUser.getUserId(), "tenant");
		userInfo.setTenantId(tenantId);

		// 设置角色列表 （ID）
		List<Long> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
			.stream()
			.map(SysRole::getRoleId)
			.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));

		// 设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roleIds.forEach(roleId -> {
			List<String> permissionList = sysPermissionService.findPermissionByRoleId(roleId)
				.stream()
				.filter(perm -> StrUtil.isNotEmpty(perm.getPermission()))
				.map(SysPermission::getPermission)
				.collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 * @param page 分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
		return baseMapper.getUserVosPage(page, userDTO);
	}

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(Long id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 删除用户
	 * @param ids 用户ID 列表
	 * @return Boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteUserByIds(Long[] ids) {
		List<SysUser> userList = baseMapper.selectBatchIds(CollUtil.toList(ids));
		for (SysUser sysUser : userList) {
			evictUserCache(sysUser.getUsername());
		}

		sysUserRoleMapper.delete(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, CollUtil.toList(ids)));
		this.removeBatchByIds(CollUtil.toList(ids));
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "T(com.pig4cloud.pig.common.core.constant.CacheConstants).USER_DETAILS_KEY_PREFIX + #userDto.username")
	public R<Boolean> updateUserInfo(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(SecurityUtils.getUser().getId());
		sysUser.setAvatar(userDto.getAvatar());
		sysUser.setNickname(userDto.getNickname());
		sysUser.setName(userDto.getName());
		sysUser.setEmail(userDto.getEmail());
		return R.ok(this.updateById(sysUser));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "T(com.pig4cloud.pig.common.core.constant.CacheConstants).USER_DETAILS_KEY_PREFIX + #userDto.username")
	public Boolean updateUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUsername(null);
		sysUser.setUpdateTime(Instant.now());
		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);

		// 更新用户角色表
		if (Objects.nonNull(userDto.getRoles())) {
			sysUserRoleMapper
				.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userDto.getUserId()));
			userDto.getRoles().stream().map(roleId -> {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserId(sysUser.getUserId());
				userRole.setRoleId(roleId);
				return userRole;
			}).forEach(sysUserRoleMapper::insert);
		}


		return Boolean.TRUE;
	}

	/**
	 * 查询全部的用户
	 * @param userDTO 查询条件
	 * @return list
	 */
	@Override
	public List<UserExcelVO> listUser(UserDTO userDTO) {
		List<UserVO> voList = baseMapper.selectVoList(userDTO);
		return voList.stream().map(userVO -> {
			UserExcelVO excelVO = new UserExcelVO();
			BeanUtils.copyProperties(userVO, excelVO);
			String roleNameList = userVO.getRoleList()
				.stream()
				.map(SysRole::getRoleName)
				.collect(Collectors.joining(StrUtil.COMMA));
			excelVO.setRoleNameList(roleNameList);
			return excelVO;
		}).collect(Collectors.toList());
	}

	@Override
	public R importUser(List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
		List<SysRole> roleList = sysRoleService.list();
		List<SysUser> allUsers = this.list();

		for (UserExcelVO excel : excelVOList) {
			Set<String> errorMsg = new HashSet<>();
			boolean exsitUserName = allUsers.stream()
				.anyMatch(sysUser -> excel.getUsername().equals(sysUser.getUsername()));

			if (exsitUserName) {
				errorMsg.add(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERNAME_EXISTING, excel.getUsername()));
			}

			List<String> roleNameList = StrUtil.split(excel.getRoleNameList(), StrUtil.COMMA);
			List<SysRole> roleCollList = roleList.stream()
				.filter(role -> roleNameList.stream().anyMatch(name -> role.getRoleName().equals(name)))
				.collect(Collectors.toList());

			if (roleCollList.size() != roleNameList.size()) {
				errorMsg.add(MsgUtils.getMessage(ErrorCodes.SYS_ROLE_ROLENAME_INEXISTENCE, excel.getRoleNameList()));
			}

			if (CollUtil.isEmpty(errorMsg)) {
				insertExcelUser(excel, roleCollList);
			}
			else {
				errorMessageList.add(new ErrorMessage(excel.getLineNum(), errorMsg));
			}

		}

		if (CollUtil.isNotEmpty(errorMessageList)) {
			return R.failed(errorMessageList);
		}
		return R.ok();
	}

	private void insertExcelUser(UserExcelVO excel, List<SysRole> roleCollList) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(excel.getUsername());
		userDTO.setPhone(excel.getPhone());
		userDTO.setNickname(excel.getNickname());
		userDTO.setName(excel.getName());
		userDTO.setEmail(excel.getEmail());
		userDTO.setPassword(userDTO.getPhone());
		List<Long> roleIdList = roleCollList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
		userDTO.setRoles(roleIdList);
		this.saveUser(userDTO);
	}

	/**
	 * 注册用户 赋予用户默认角色
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R<Boolean> registerUser(RegisterUserDTO userDto) {
		// 判断用户名是否存在
		SysUser sysUser = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userDto.getUsername()));
		if (sysUser != null) {
			String message = MsgUtils.getMessage(ErrorCodes.SYS_USER_USERNAME_EXISTING, userDto.getUsername());
			return R.failed(message);
		}

		UserDTO user = new UserDTO();
		BeanUtils.copyProperties(userDto, user);
		return R.ok(saveUser(user));
	}

	/**
	 * 锁定用户
	 * @param username 用户名
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "T(com.pig4cloud.pig.common.core.constant.CacheConstants).USER_DETAILS_KEY_PREFIX + #username")
	public R<Boolean> lockUser(String username) {
		SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));

		if (Objects.nonNull(sysUser)) {
			sysUser.setLockFlag(CommonConstants.STATUS_LOCK);
			sysUser.setLockUntil(Instant.now().plusSeconds(900));
			baseMapper.updateById(sysUser);
		}
		return R.ok();
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "T(com.pig4cloud.pig.common.core.constant.CacheConstants).USER_DETAILS_KEY_PREFIX + #username")
	public R<Boolean> unlockUser(String username) {
		SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));

		if (Objects.nonNull(sysUser)) {
			sysUser.setLockFlag(CommonConstants.STATUS_NORMAL);
			sysUser.setLockUntil(null);
			baseMapper.updateById(sysUser);
		}
		return R.ok();
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "T(com.pig4cloud.pig.common.core.constant.CacheConstants).USER_DETAILS_KEY_PREFIX + #userDto.username")
	public R changePassword(UserDTO userDto) {
		SysUser sysUser = baseMapper.selectById(SecurityUtils.getUser().getId());
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}

		if (StrUtil.isEmpty(userDto.getPassword())) {
			return R.failed("原密码不能为空");
		}

		if (!ENCODER.matches(userDto.getPassword(), sysUser.getPassword())) {
			log.info("原密码错误，修改个人信息失败:{}", userDto.getUsername());
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_UPDATE_PASSWORDERROR));
		}

		if (StrUtil.isEmpty(userDto.getNewpassword1())) {
			return R.failed("新密码不能为空");
		}
		String password = ENCODER.encode(userDto.getNewpassword1());

		this.update(Wrappers.<SysUser>lambdaUpdate()
			.set(SysUser::getPassword, password)
			.eq(SysUser::getUserId, sysUser.getUserId()));

		remoteTokenService.removeTokenByUsername(sysUser.getUsername());
		return R.ok();
	}

	@Override
	public R resetPassword(Long userId, String newPassword) {
		SysUser sysUser = baseMapper.selectById(userId);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}

		if (StrUtil.isEmpty(newPassword)) {
			return R.failed("新密码不能为空");
		}

		String password = ENCODER.encode(newPassword);
		this.update(Wrappers.<SysUser>lambdaUpdate()
			.set(SysUser::getPassword, password)
			.eq(SysUser::getUserId, userId));

		remoteTokenService.removeTokenByUsername(sysUser.getUsername());
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	@Override
	public R checkPassword(String password) {
		SysUser sysUser = baseMapper.selectById(SecurityUtils.getUser().getId());

		if (!ENCODER.matches(password, sysUser.getPassword())) {
			log.info("原密码错误");
			return R.failed("密码输入错误");
		}
		else {
			return R.ok();
		}
	}

	@Override
	public R closeAccount(Long id) {
		SysUser sysUser = baseMapper.selectById(id);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}
		sysUser.setStatus(UserStatusEnum.CLOSED);
		baseMapper.updateById(sysUser);
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	@Override
	public R restoreAccount(Long id) {
		SysUser sysUser = baseMapper.selectById(id);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}
		sysUser.setStatus(UserStatusEnum.ENABLED);
		baseMapper.updateById(sysUser);
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	@Override
	public R enableUser(Long id) {
		SysUser sysUser = baseMapper.selectById(id);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}
		if (UserStatusEnum.ENABLED.equals(sysUser.getStatus())) {
			return R.ok(sysUser);
		}
		sysUser.setStatus(UserStatusEnum.ENABLED);
		baseMapper.updateById(sysUser);
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	@Override
	public R disableUser(Long id) {
		SysUser sysUser = baseMapper.selectById(id);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}
		if (UserStatusEnum.DISABLED.equals(sysUser.getStatus())) {
			return R.ok(sysUser);
		}
		sysUser.setStatus(UserStatusEnum.DISABLED);
		baseMapper.updateById(sysUser);
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	@Override
	public R updateAvatar(Long id, String avatarUrl) {
		SysUser sysUser = baseMapper.selectById(id);
		if (Objects.isNull(sysUser)) {
			return R.failed("用户不存在");
		}
		sysUser.setAvatar(avatarUrl);
		baseMapper.updateById(sysUser);
		evictUserCache(sysUser.getUsername());
		return R.ok(sysUser);
	}

	private void evictUserCache(String username) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null) {
			cache.evictIfPresent(CacheConstants.USER_DETAILS_KEY_PREFIX + username);
		}
	}

}
