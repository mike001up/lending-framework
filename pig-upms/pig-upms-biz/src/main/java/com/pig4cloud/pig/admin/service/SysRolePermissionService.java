package com.pig4cloud.pig.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRolePermission;

public interface SysRolePermissionService extends IService<SysRolePermission> {

	Boolean saveRolePermissions(Long roleId, String permissionIds);

	List<SysPermission> selectGrantPermission(Long userId);

}