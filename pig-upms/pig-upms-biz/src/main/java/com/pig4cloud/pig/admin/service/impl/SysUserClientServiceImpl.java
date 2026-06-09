package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysUserClient;
import com.pig4cloud.pig.admin.mapper.SysUserClientMapper;
import com.pig4cloud.pig.admin.service.SysUserClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserClientServiceImpl extends ServiceImpl<SysUserClientMapper, SysUserClient> implements SysUserClientService {

	@Override
	public List<SysUserClient> findByUserId(Long userId) {
		return this.list(Wrappers.<SysUserClient>lambdaQuery()
			.eq(SysUserClient::getUserId, userId));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void grantClient(Long userId, Long clientId) {
		SysUserClient existing = this.getOne(Wrappers.<SysUserClient>lambdaQuery()
			.eq(SysUserClient::getUserId, userId)
			.eq(SysUserClient::getClientId, clientId));
		if (existing != null) {
			return;
		}
		SysUserClient uc = new SysUserClient();
		uc.setUserId(userId);
		uc.setClientId(clientId);
		baseMapper.insert(uc);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void revokeClient(Long userId, Long clientId) {
		this.remove(Wrappers.<SysUserClient>lambdaQuery()
			.eq(SysUserClient::getUserId, userId)
			.eq(SysUserClient::getClientId, clientId));
	}

}