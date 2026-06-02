package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
	public Boolean saveRolePermissions(Long roleId, String permissionIds) {
		this.remove(Wrappers.<SysRolePermission>query().lambda().eq(SysRolePermission::getRoleId, roleId));

		if (StrUtil.isBlank(permissionIds)) {
			return Boolean.TRUE;
		}
		List<SysRolePermission> rolePermissionList = Arrays.stream(permissionIds.split(StrUtil.COMMA)).map(permId -> {
			SysRolePermission rp = new SysRolePermission();
			rp.setRoleId(roleId);
			rp.setPermissionId(Long.valueOf(permId));
			return rp;
		}).collect(Collectors.toList());

		cacheManager.getCache(CacheConstants.USER_DETAILS).clear();
		this.saveBatch(rolePermissionList);
		return Boolean.TRUE;
	}

}