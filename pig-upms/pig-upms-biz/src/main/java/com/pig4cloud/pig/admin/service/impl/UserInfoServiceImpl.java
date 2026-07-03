package com.pig4cloud.pig.admin.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;
import com.pig4cloud.pig.common.security.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Service
// @Primary   // 添加此注解
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{@Override
    public R<PigUserDTO> loadUserByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
