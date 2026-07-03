package com.pig4cloud.pig.common.security.service;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.ClientRegisteredDTO;

/**
 * 客户端详情获取服务（由业务模块实现）
 *
 * @author lengleng
 */
public interface ClientDetailsService {

    /**
     * 根据 clientId 加载客户端
     *
     * @param clientId 客户端ID
     * @return RegisteredClient
     */
    R<ClientRegisteredDTO> loadClientByClientId(String clientId);
}
