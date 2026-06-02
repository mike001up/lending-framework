package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysRolePermission;

public interface SysRolePermissionService extends IService<SysRolePermission> {

	Boolean saveRolePermissions(Long roleId, String permissionIds);

}