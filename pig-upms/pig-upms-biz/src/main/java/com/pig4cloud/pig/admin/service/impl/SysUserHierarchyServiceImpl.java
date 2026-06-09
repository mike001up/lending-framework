package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import com.pig4cloud.pig.admin.mapper.SysUserHierarchyMapper;
import com.pig4cloud.pig.admin.service.SysUserHierarchyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserHierarchyServiceImpl extends ServiceImpl<SysUserHierarchyMapper, SysUserHierarchy> implements SysUserHierarchyService {

	@Override
	public List<Long> getAncestors(Long userId) {
		return baseMapper.selectAncestors(userId);
	}

	@Override
	public List<Long> getDescendants(Long userId) {
		return baseMapper.selectDescendants(userId);
	}

	@Override
	public List<Long> getDirectChildren(Long userId) {
		return baseMapper.selectDirectChildren(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addRelation(Long ancestor, Long descendant) {
		addRelation(ancestor, descendant, "agency", 1);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addRelation(Long ancestor, Long descendant, String hierarchyType, int depth) {
		SysUserHierarchy hierarchy = new SysUserHierarchy();
		hierarchy.setAncestor(ancestor);
		hierarchy.setDescendant(descendant);
		hierarchy.setDepth(depth);
		hierarchy.setHierarchyType(hierarchyType);
		baseMapper.insert(hierarchy);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeRelation(Long ancestor, Long descendant) {
		this.remove(Wrappers.<SysUserHierarchy>lambdaQuery()
			.eq(SysUserHierarchy::getAncestor, ancestor)
			.eq(SysUserHierarchy::getDescendant, descendant));
	}

	@Override
	public Long getAncestorByType(Long userId, String hierarchyType) {
		SysUserHierarchy hierarchy = this.getOne(Wrappers.<SysUserHierarchy>lambdaQuery()
			.eq(SysUserHierarchy::getDescendant, userId)
			.eq(SysUserHierarchy::getHierarchyType, hierarchyType)
			.eq(SysUserHierarchy::getDepth, 1)
			.last("LIMIT 1"));
		return hierarchy != null ? hierarchy.getAncestor() : null;
	}

	@Override
	public List<Long> getDescendantsByAncestorAndType(Long ancestorId, String hierarchyType) {
		return this.list(Wrappers.<SysUserHierarchy>lambdaQuery()
			.eq(SysUserHierarchy::getAncestor, ancestorId)
			.eq(SysUserHierarchy::getHierarchyType, hierarchyType))
			.stream()
			.map(SysUserHierarchy::getDescendant)
			.distinct()
			.collect(java.util.stream.Collectors.toList());
	}

}