package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.mapper.SysTenantDatasourceMapper;
import com.pig4cloud.pig.admin.service.SysTenantDatasourceService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@Service
public class SysTenantDatasourceServiceImpl extends ServiceImpl<SysTenantDatasourceMapper, SysTenantDatasource> implements SysTenantDatasourceService {

	@Override
	public R testConnection(Long id) {
		SysTenantDatasource ds = this.getById(id);
		if (ds == null) {
			return R.failed("数据源配置不存在");
		}
		String url = String.format("jdbc:%s://%s:%d/%s", ds.getDbType(), ds.getHost(), ds.getPort(), ds.getDbName());
		try (Connection conn = DriverManager.getConnection(url, ds.getUsername(), ds.getPasswordEncrypted())) {
			return R.ok(true);
		}
		catch (Exception e) {
			log.error("数据源连接测试失败: {}", e.getMessage());
			return R.failed("连接测试失败: " + e.getMessage());
		}
	}

}