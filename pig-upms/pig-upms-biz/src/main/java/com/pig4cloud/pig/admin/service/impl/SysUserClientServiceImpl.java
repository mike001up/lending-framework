package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.api.entity.SysUserClient;
import com.pig4cloud.pig.admin.mapper.SysUserClientMapper;
import com.pig4cloud.pig.admin.service.SysOauthClientDetailsService;
import com.pig4cloud.pig.admin.service.SysUserClientService;
import com.pig4cloud.pig.common.core.exception.ErrorCodes;
import com.pig4cloud.pig.common.core.util.MsgUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SysUserClientServiceImpl extends ServiceImpl<SysUserClientMapper, SysUserClient> implements SysUserClientService {
	
	private final SysOauthClientDetailsService clientDetailsService;

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

	@Override
	@Transactional
	public void grantClient(Long userId, List<Long> clientIds) {	
		if (clientIds == null || clientIds.isEmpty()) {
        	throw new RuntimeException(MsgUtils.getMessage(ErrorCodes.SYS_CLIENTS_NON_EXISTING));
    	}

		// 批量查询所有 clientId 对应的客户端
		List<SysOauthClientDetails> clientDetailsList = clientDetailsService.list(
			Wrappers.<SysOauthClientDetails>lambdaQuery()
				.in(SysOauthClientDetails::getClientId, clientIds)
		);

    	// 批量授权（见下方说明）
    	List<SysUserClient> relations = clientIds.stream()
        .map(clientIdValue -> {
            SysUserClient relation = new SysUserClient();
            relation.setUserId(userId);
            relation.setClientId(clientIdValue);
            return relation;
        })
        .collect(Collectors.toList());
		// 使用 MyBatis-Plus 的 saveBatch 或自定义批量插入
		baseMapper.insert(relations);
	}

}