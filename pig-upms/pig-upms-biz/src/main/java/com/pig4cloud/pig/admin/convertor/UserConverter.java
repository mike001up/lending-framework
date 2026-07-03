package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {    
    UserVO toVo(SysUser user);
    SysUser toEntity(UserDTO userDTO);
    @Mapping(source = "userId", target = "id")
    PigUserDTO toPigUserVO(SysUser user);
}
