package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.admin.api.entity.RoleDataScope;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDataScopeMapper extends BaseMapper<RoleDataScope> {

    @Select("""
        SELECT rds.* FROM sys_role_data_scope rds
        INNER JOIN sys_user_role ur ON rds.role_id = ur.role_id
        WHERE ur.user_id = #{userId}
        """)
    List<RoleDataScope> listByUserId(@Param("userId") Long userId);
}