package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRolePermission;
import com.pig4cloud.pig.admin.mapper.SysRolePermissionMapper;
import com.pig4cloud.pig.admin.service.SysRolePermissionService;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

	private final CacheManager cacheManager;


	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.PERMISSION_DETAILS, key = "#roleId")
	public Boolean saveRolePermissions(Long roleId, List<String> permissionIds) {
		this.remove(Wrappers.<SysRolePermission>query().lambda().eq(SysRolePermission::getRoleId, roleId));

		if (CollectionUtil.isEmpty(permissionIds)) {
			return Boolean.TRUE;
		}
		List<SysRolePermission> rolePermissionList = permissionIds.stream().map(permId -> {
			return SysRolePermission.builder().roleId(roleId).permissionId(Long.valueOf(permId)).build();
		}).collect(Collectors.toList());

		cacheManager.getCache(CacheConstants.USER_DETAILS).clear();
		this.saveBatch(rolePermissionList);
		return Boolean.TRUE;
	}

	@Override
	public List<SysPermission> selectGrantPermission(Long userId) {
		return this.baseMapper.selectGrantPermission(userId);
	}

}