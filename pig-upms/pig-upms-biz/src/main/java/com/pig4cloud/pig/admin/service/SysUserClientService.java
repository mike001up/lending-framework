package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysUserClient;

import java.util.List;

public interface SysUserClientService extends IService<SysUserClient> {

	List<SysUserClient> findByUserId(Long userId);

	void grantClient(Long userId, Long clientId);

	void grantClient(Long userId, List<Long> clientId);

	void revokeClient(Long userId, Long clientId);

}