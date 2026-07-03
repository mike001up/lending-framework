package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.entity.SysRolePermission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    List<SysPermission> selectGrantPermission(Long userId);

}