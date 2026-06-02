package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.service.SysTenantDatasourceService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/tenant-datasource")
@Tag(description = "tenantDatasource", name = "租户数据源配置模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysTenantDatasourceController {

	private final SysTenantDatasourceService sysTenantDatasourceService;

	@GetMapping("/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysTenantDatasourceService.getById(id));
	}

	@GetMapping("/page")
	public R page(Page page, SysTenantDatasource query) {
		return R.ok(sysTenantDatasourceService.page(page, Wrappers.query(query)));
	}

	@SysLog("新增数据源配置")
	@PostMapping
	@HasPermission("sys_tenant_ds_add")
	public R save(@Valid @RequestBody SysTenantDatasource ds) {
		return R.ok(sysTenantDatasourceService.save(ds));
	}

	@SysLog("删除数据源配置")
	@DeleteMapping("/{id}")
	@HasPermission("sys_tenant_ds_del")
	public R removeById(@PathVariable Long id) {
		return R.ok(sysTenantDatasourceService.removeById(id));
	}

	@SysLog("更新数据源配置")
	@PutMapping
	@HasPermission("sys_tenant_ds_edit")
	public R update(@Valid @RequestBody SysTenantDatasource ds) {
		return R.ok(sysTenantDatasourceService.updateById(ds));
	}

	@GetMapping("/test/{id}")
	public R testConnection(@PathVariable Long id) {
		return sysTenantDatasourceService.testConnection(id);
	}

}