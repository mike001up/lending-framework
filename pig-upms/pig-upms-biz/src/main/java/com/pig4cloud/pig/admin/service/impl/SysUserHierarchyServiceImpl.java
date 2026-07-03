package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import com.pig4cloud.pig.admin.mapper.SysUserHierarchyMapper;
import com.pig4cloud.pig.admin.service.SysUserHierarchyService;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	@Transactional
	public void buildHierarchy(Long agencyId, Long tenantId, Long userId) {
		List<SysUserHierarchy> newRels = new ArrayList<>();
		SysUserHierarchy selfRel = new SysUserHierarchy();
		selfRel.setAncestor(userId);
		selfRel.setDescendant(userId);
		selfRel.setDepth(0);
		selfRel.setHierarchyType("agency");
		//指定了层级结构中的父节点
		if (agencyId != null && tenantId != null) {
			List<SysUserHierarchy> agencyRel = this.baseMapper.selectList(Wrappers.<SysUserHierarchy>lambdaQuery().eq(SysUserHierarchy::getDescendant, agencyId));
			newRels = agencyRel.stream().map(rel -> {
				SysUserHierarchy item = new SysUserHierarchy();
				item.setAncestor(rel.getAncestor());
				item.setDescendant(userId); // 假设 userId 是当前用户
				item.setDepth(rel.getDepth() + 1); // 或者其他
				item.setHierarchyType("agency");
				return item;
			})
			.collect(Collectors.toList());
			// 加入 selfRel
			newRels.add(selfRel);			
		}
		//没有指定层级结构的父节点，默认是租户
		if (tenantId != null && agencyId == null) {
			SysUserHierarchy tenantSelfRel = this.baseMapper.selectOne(Wrappers.<SysUserHierarchy>lambdaQuery(null)
				.eq(SysUserHierarchy::getDescendant, tenantId)
				.eq(SysUserHierarchy::getDepth, 0));
			if(tenantSelfRel == null){
				tenantSelfRel = new SysUserHierarchy();
				tenantSelfRel.setAncestor(tenantId);
				tenantSelfRel.setDescendant(tenantId);
				tenantSelfRel.setDepth(0);
				tenantSelfRel.setHierarchyType("agency");
				
				newRels.add(tenantSelfRel);				
			}
			SysUserHierarchy tenantRel = new SysUserHierarchy();
			tenantRel.setAncestor(tenantId);
			tenantRel.setDescendant(userId);
			tenantRel.setDepth(1);
			tenantRel.setHierarchyType("agency");
				
			newRels.add(tenantRel);
			newRels.add(selfRel);
		}
		this.saveBatch(newRels);
	}

	

}