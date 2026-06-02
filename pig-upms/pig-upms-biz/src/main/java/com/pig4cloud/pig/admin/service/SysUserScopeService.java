package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysUserScope;

import java.util.List;

public interface SysUserScopeService extends IService<SysUserScope> {

	List<SysUserScope> findByUserId(Long userId);

	void grantScope(Long userId, Long scopeId, Long grantedBy);

	void revokeScope(Long userId, Long scopeId);

}