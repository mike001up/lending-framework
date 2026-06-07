package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.common.core.util.R;

public interface SysTenantService extends IService<SysTenant> {

	R enableTenant(Long id);

	R disableTenant(Long id);

	R removeTenantById(Long id);

}