/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.common.security.service;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.util.GetClient;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.exception.UserBlockedException;
import com.pig4cloud.pig.common.security.exception.UserNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户详细信息
 *
 * @author lengleng hccake
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class PigUserDetailsServiceImpl implements PigUserDetailsService {

    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        String client = GetClient.get();
        String cacheKey = client + ":" + username;
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(cacheKey) != null) {
            PigUser pigUser = (PigUser) cache.get(cacheKey).get();
            return pigUser;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        R<UserInfo> result;
        if (client.equalsIgnoreCase(CommonConstants.BMS)) {
            result = remoteUserService.info(userDTO);
        } else {
            result = remoteUserService.infoApp(userDTO);
        }
		int code = result.getCode();
        if (code == 1) {
            throw new UserBlockedException(result.getMsg());
        }
        if (code == 101) {
            throw new UserNotExistException(result.getMsg());
        }
        UserDetails userDetails = getUserDetails(result);
        if (cache != null) {
            cache.put(cacheKey, userDetails);
        }
        return userDetails;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
