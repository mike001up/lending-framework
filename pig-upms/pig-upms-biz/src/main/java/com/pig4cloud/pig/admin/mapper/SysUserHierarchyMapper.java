package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserHierarchyMapper extends BaseMapper<SysUserHierarchy> {

	List<Long> selectAncestors(Long descendant);

	List<Long> selectDescendants(Long ancestor);

	List<Long> selectDirectChildren(Long ancestor);

}