package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserHierarchyMapper extends BaseMapper<SysUserHierarchy> {

	List<Long> selectAncestors(Long descendant);

	List<Long> selectDescendants(Long ancestor);

	List<Long> selectDirectChildren(Long ancestor);

	@Select("""
        SELECT descendant FROM sys_user_hierarchy
        WHERE ancestor = #{userId} AND hierarchy_type = #{hierarchyType}
        """)
    List<Long> listDescendants(@Param("userId") Long userId,
                               @Param("hierarchyType") String hierarchyType);

}