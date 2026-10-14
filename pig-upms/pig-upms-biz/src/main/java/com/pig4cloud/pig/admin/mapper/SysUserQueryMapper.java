package com.pig4cloud.pig.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 只用于查 username，避免污染 SysUserMapper
 */
@Mapper
public interface SysUserQueryMapper {

    @Select("""
        <script>
        SELECT username FROM sys_user
        WHERE is_del = 'NO' AND user_id IN
        <foreach collection='ids' item='id' open='(' separator=',' close=')'>
            #{id}
        </foreach>
        </script>
        """)
    List<String> listUsernamesByIds(@Param("ids") List<Long> ids);
}