package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.SysTenantDatasourceDTO;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.api.vo.SysTenantDatasourceVO;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantDsConverter {    
    SysTenantDatasourceVO toVo(SysTenantDatasource ds);
    SysTenantDatasource toEntity(SysTenantDatasourceDTO ds);
    List<SysTenantDatasourceVO> toVOList(List<SysTenantDatasource> list);    
}
