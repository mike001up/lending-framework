package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.SysUserHierarchy;
import com.pig4cloud.pig.admin.mapper.SysTenantMapper;
import com.pig4cloud.pig.admin.mapper.SysUserHierarchyMapper;
import com.pig4cloud.pig.admin.mapper.SysUserMapper;
import com.pig4cloud.pig.admin.service.SysTenantService;
import com.pig4cloud.pig.common.core.constant.enums.TenantStatusEnum;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

	private final SysUserMapper sysUserMapper;

	private final SysUserHierarchyMapper sysUserHierarchyMapper;

	@Override
	public R enableTenant(Long id) {
		SysTenant tenant = baseMapper.selectById(id);
		if (tenant == null) {
			return R.failed("租户不存在");
		}
		if (TenantStatusEnum.ENABLED.equals(tenant.getStatus())) {
			return R.ok();
		}
		tenant.setStatus(TenantStatusEnum.ENABLED);
		baseMapper.updateById(tenant);
		return R.ok();
	}

	@Override
	public R disableTenant(Long id) {
		SysTenant tenant = baseMapper.selectById(id);
		if (tenant == null) {
			return R.failed("租户不存在");
		}
		if (TenantStatusEnum.DISABLED.equals(tenant.getStatus())) {
			return R.ok();
		}
		tenant.setStatus(TenantStatusEnum.DISABLED);
		baseMapper.updateById(tenant);
		return R.ok();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public R removeTenantById(Long id) {
		List<Long> userIds = sysUserHierarchyMapper
			.selectList(Wrappers.<SysUserHierarchy>lambdaQuery()
				.eq(SysUserHierarchy::getAncestor, id)
				.eq(SysUserHierarchy::getHierarchyType, "tenant"))
			.stream()
			.map(SysUserHierarchy::getDescendant)
			.distinct()
			.collect(Collectors.toList());
		if (!userIds.isEmpty()) {
			sysUserMapper.deleteBatchIds(userIds);
		}
		baseMapper.deleteById(id);
		return R.ok();
	}

}
