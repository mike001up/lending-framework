package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;

public interface SysTenantDatasourceService extends IService<SysTenantDatasource> {

	R testConnection(Long id);

	SysTenantDatasource getByIdWithDecrypted(Long id);

	List<SysTenantDatasource> listByTenantIdWithDecrypted(Long tenantId);

}
