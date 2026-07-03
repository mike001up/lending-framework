package com.pig4cloud.pig.admin.convertor;

import com.pig4cloud.pig.admin.api.dto.ClientDTO;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.api.vo.ClientVO;
import com.pig4cloud.pig.common.security.dto.ClientRegisteredDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientConverter {    
    ClientVO toVo(SysOauthClientDetails client);
    SysOauthClientDetails toEntity(ClientDTO clientDto);
    @Mapping(source = "scope", target = "scopes")
    @Mapping(source = "webServerRedirectUri", target = "redirectUris")
    ClientRegisteredDTO toClientRegisteredDTO(SysOauthClientDetails client);
}
