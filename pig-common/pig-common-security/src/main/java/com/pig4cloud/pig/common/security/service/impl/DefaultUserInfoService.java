package com.pig4cloud.pig.common.security.service.impl;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;
import com.pig4cloud.pig.common.security.feign.RemoteUserInfoFeignClient;
import com.pig4cloud.pig.common.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class DefaultUserInfoService implements UserInfoService {

    private final RemoteUserInfoFeignClient feignClient;

    @Override
    public R<PigUserDTO> loadUserByUsername(String username) {
        R<PigUserDTO> r = feignClient.loadUserByUsername(username);
        return feignClient.loadUserByUsername(username);
    }
}
