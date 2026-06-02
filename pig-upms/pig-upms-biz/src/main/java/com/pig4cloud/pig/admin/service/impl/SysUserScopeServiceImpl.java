package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysUserScope;
import com.pig4cloud.pig.admin.mapper.SysUserScopeMapper;
import com.pig4cloud.pig.admin.service.SysUserScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysUserScopeServiceImpl extends ServiceImpl<SysUserScopeMapper, SysUserScope> implements SysUserScopeService {

	@Override
	public List<SysUserScope> findByUserId(Long userId) {
		return this.list(Wrappers.<SysUserScope>lambdaQuery().eq(SysUserScope::getUserId, userId));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void grantScope(Long userId, Long scopeId, Long grantedBy) {
		SysUserScope scope = new SysUserScope();
		scope.setUserId(userId);
		scope.setScopeId(scopeId);
		scope.setGrantedBy(grantedBy);
		scope.setGrantedAt(LocalDateTime.now());
		baseMapper.insert(scope);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void revokeScope(Long userId, Long scopeId) {
		this.update(Wrappers.<SysUserScope>lambdaUpdate()
			.eq(SysUserScope::getUserId, userId)
			.eq(SysUserScope::getScopeId, scopeId)
			.set(SysUserScope::getRevokedAt, LocalDateTime.now()));
	}

}