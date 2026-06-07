package com.pig4cloud.pig.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

	List<SysPermission> findPermissionByRoleId(Long roleId);

	R removePermissionById(Long id);

	Boolean updatePermissionById(SysPermission sysPermission);

	List<Tree<Long>> treePermission(Long parentId, String name);

	List<SysPermission> listAuthorizeRules();

	Boolean checkPermission(Long userId, String permCode);

	List<Boolean> batchCheckPermission(Long userId, List<String> permCodes);

}