package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRolePermission;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.mapper.SysPermissionMapper;
import com.pig4cloud.pig.admin.mapper.SysRolePermissionMapper;
import com.pig4cloud.pig.admin.mapper.SysUserRoleMapper;
import com.pig4cloud.pig.admin.service.SysPermissionService;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

	private final SysRolePermissionMapper sysRolePermissionMapper;

	private final SysUserRoleMapper sysUserRoleMapper;

	@Override
	public R<Boolean> savePermission(SysPermission sysPermission) {
		boolean isExisting = this.exists(Wrappers.<SysPermission>lambdaQuery().eq(SysPermission::getPermCode, sysPermission.getPermCode()));
		if (isExisting) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_PERMISSION_PERMCODE_EXISTING,
					sysPermission.getPermCode()));
		}
		return R.ok(this.save(sysPermission));
	}

	@Override
	@Cacheable(value = CacheConstants.PERMISSION_DETAILS, key = "#roleId", unless = "#result.isEmpty()")
	public List<SysPermission> findPermissionByRoleId(Long roleId) {
		return baseMapper.listPermissionsByRoleId(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.PERMISSION_DETAILS, allEntries = true)
	public R removePermissionById(Long id) {
		List<SysPermission> children = this.list(Wrappers.<SysPermission>query().lambda().eq(SysPermission::getParentId, id));
		if (CollUtil.isNotEmpty(children)) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_MENU_DELETE_EXISTING));
		}
		sysRolePermissionMapper.delete(Wrappers.<SysRolePermission>query().lambda().eq(SysRolePermission::getPermissionId, id));
		return R.ok(this.removeById(id));
	}

	@Override
	@CacheEvict(value = CacheConstants.PERMISSION_DETAILS, allEntries = true)
	public Boolean updatePermissionById(SysPermission sysPermission) {
		return this.updateById(sysPermission);
	}

	@Override
	public List<TreeNode<Long>> treePermission(Long parentId, String name) {
		return baseMapper.selectList(Wrappers.<SysPermission>lambdaQuery()
            // 传入父ID，就查该父节点下的所有子节点
            .eq(parentId != null, SysPermission::getParentId, parentId)
            // 没传父ID，就查所有根节点（parent_id 为 NULL）
            .isNull(parentId == null, SysPermission::getParentId)
            .like(StrUtil.isNotBlank(name), SysPermission::getPermName, name)
            .orderByAsc(SysPermission::getSortOrder))
        .stream()
        .map(this::toTreeNode)
        .collect(Collectors.toList());
	}

	private TreeNode<Long> toTreeNode(SysPermission perm) {
		TreeNode<Long> node = new TreeNode<>();
		node.setId(perm.getPermissionId());
		node.setName(perm.getPermName());
		node.setParentId(perm.getParentId());
		node.setWeight(perm.getSortOrder());
		Map<String, Object> extra = new HashMap<>();
		extra.put("permission", perm.getPermission());
		extra.put("permissionCode", perm.getPermCode());
		extra.put("description", perm.getDescription());
		extra.put("path", perm.getPath());
		extra.put("method", perm.getMethod());
		extra.put("sortOrder", perm.getSortOrder());
		node.setExtra(extra);
		return node;
	}

	@Override
	public List<SysPermission> listAuthorizeRules() {
		return baseMapper.selectList(Wrappers.<SysPermission>lambdaQuery()
			.isNotNull(SysPermission::getPath)
			.ne(SysPermission::getPath, "")
			.isNotNull(SysPermission::getPermission)
			.ne(SysPermission::getPermission, ""));
	}

	@Override
	public Boolean checkPermission(Long userId, String permCode) {
		Long count = sysUserRoleMapper.countUserPermission(userId, permCode);
		
		return count > 0;
	}

	@Override
	public List<Boolean> batchCheckPermission(Long userId, List<String> permCodes) {
		return permCodes.stream().map(code -> checkPermission(userId, code)).collect(Collectors.toList());
	}

}