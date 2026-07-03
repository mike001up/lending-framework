package com.pig4cloud.pig.common.security.feign;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.ClientRegisteredDTO;
import com.pig4cloud.pig.common.security.service.ClientDetailsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 默认的 ClientDetailsService Feign 实现
 */
@FeignClient(contextId = "remoteClientDetailsFeignClient", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteClientDetailsFeignClient extends ClientDetailsService {


    @GetMapping("/client/details")
    @InternalFeign
    R<ClientRegisteredDTO> loadClientByClientId(@RequestParam("clientId") String clientId);
}
