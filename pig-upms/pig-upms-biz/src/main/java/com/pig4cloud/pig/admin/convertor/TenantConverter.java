package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.SysTenantDTO;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.admin.api.vo.SysTenantVO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantConverter {    
    SysTenantVO toVo(SysTenant permission);
    SysTenant toEntity(SysTenantDTO userDTO);    
}
