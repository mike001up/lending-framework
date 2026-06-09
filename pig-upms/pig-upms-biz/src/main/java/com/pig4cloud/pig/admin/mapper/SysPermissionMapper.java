package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	List<SysPermission> listPermissionsByRoleId(Long roleId);

	@Select("SELECT COUNT(*) FROM sys_permission WHERE perm_code = #{permCode}")
	long countByPermCode(@Param("permCode") String permCode);

}