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
	public List<Tree<Long>> treePermission(Long parentId, String name) {
		Long parent = parentId == null ? -1L : parentId;

		List<TreeNode<Long>> collect = baseMapper
			.selectList(Wrappers.<SysPermission>lambdaQuery()
				.like(StrUtil.isNotBlank(name), SysPermission::getName, name)
				.orderByAsc(SysPermission::getSortOrder))
			.stream()
			.map(this::toTreeNode)
			.collect(Collectors.toList());

		if (StrUtil.isNotBlank(name)) {
			return collect.stream().map(node -> {
				Tree<Long> tree = new Tree<>();
				tree.putAll(node.getExtra());
				return tree;
			}).collect(Collectors.toList());
		}

		return TreeUtil.build(collect, parent);
	}

	private TreeNode<Long> toTreeNode(SysPermission perm) {
		TreeNode<Long> node = new TreeNode<>();
		node.setId(perm.getPermissionId());
		node.setName(perm.getName());
		node.setParentId(perm.getParentId());
		node.setWeight(perm.getSortOrder());
		Map<String, Object> extra = new HashMap<>();
		extra.put("permission", perm.getPermission());
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
		List<SysUserRole> userRoles = sysUserRoleMapper
			.selectList(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
		if (CollUtil.isEmpty(userRoles)) {
			return false;
		}
		List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
		for (Long roleId : roleIds) {
			List<SysPermission> perms = findPermissionByRoleId(roleId);
			boolean has = perms.stream()
				.anyMatch(p -> permCode.equals(p.getPermCode()) || permCode.equals(p.getPermission()));
			if (has) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Boolean> batchCheckPermission(Long userId, List<String> permCodes) {
		return permCodes.stream().map(code -> checkPermission(userId, code)).collect(Collectors.toList());
	}

}