package com.pig4cloud.pig.common.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.dto.PigUserDTO;

/**
 * 用户信息获取服务（由业务模块实现）
 *
 * @author lengleng
 */
public interface UserInfoService {

    /**
     * 根据用户名/手机号加载用户
     *
     * @param username 用户名或手机号
     * @return UserDetails
     */
    R<PigUserDTO> loadUserByUsername(String username);

    /**
     * 根据用户对象加载（用于 check-token 等场景）
     *
     * @param pigUser 用户对象（由 security 模块定义）
     * @return UserDetails
     */
    // default UserDetails loadUserByUser(PigUser pigUser) {
    //     return loadUserByUsername(pigUser.getUsername());
    // }
}
