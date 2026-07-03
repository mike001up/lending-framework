package com.pig4cloud.pig.common.security.service.impl;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.ClientRegisteredDTO;
import com.pig4cloud.pig.common.security.feign.RemoteClientDetailsFeignClient;
import com.pig4cloud.pig.common.security.service.ClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@RequiredArgsConstructor
public class DefaultClientDetailsService implements ClientDetailsService {

    private final RemoteClientDetailsFeignClient feignClient;

    @Override
    public R<ClientRegisteredDTO> loadClientByClientId(String clientId) {
        R<ClientRegisteredDTO> r = feignClient.loadClientByClientId(clientId);
        return r;
    }
}
