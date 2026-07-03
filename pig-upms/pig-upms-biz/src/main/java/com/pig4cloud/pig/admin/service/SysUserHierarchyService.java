package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;

import java.util.List;

public interface SysUserHierarchyService extends IService<SysUserHierarchy> {

	List<Long> getAncestors(Long userId);

	List<Long> getDescendants(Long userId);

	List<Long> getDirectChildren(Long userId);

	void addRelation(Long ancestor, Long descendant);

	void addRelation(Long ancestor, Long descendant, String hierarchyType, int depth);

	void removeRelation(Long ancestor, Long descendant);

	Long getAncestorByType(Long userId, String hierarchyType);

	List<Long> getDescendantsByAncestorAndType(Long ancestorId, String hierarchyType);

	void buildHierarchy(Long agencyId, Long tenantId, Long userId);

}