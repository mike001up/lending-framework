package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.SysPermissionDTO;
import com.pig4cloud.pig.admin.api.entity.SysPermission;
import com.pig4cloud.pig.admin.api.vo.PermissionVO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionConverter {    
    PermissionVO toVo(SysPermission permission);
    SysPermission toEntity(SysPermissionDTO userDTO);    
}
