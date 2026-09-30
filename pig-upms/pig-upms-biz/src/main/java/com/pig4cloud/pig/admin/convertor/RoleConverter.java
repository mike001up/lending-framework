package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.SysRoleDTO;
import com.pig4cloud.pig.admin.api.dto.UserCertificationInfoDTO;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.UserCertificationInfo;
import com.pig4cloud.pig.admin.api.vo.RoleVO;
import com.pig4cloud.pig.admin.api.vo.UserCertificationInfoVO;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RoleConverter {    
    RoleVO toVo(SysRole user);
    SysRole toEntity(SysRoleDTO userDTO);    
}
