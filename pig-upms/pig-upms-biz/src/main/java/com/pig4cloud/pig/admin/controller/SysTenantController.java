package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.SysTenantDTO;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.admin.convertor.TenantConverter;
import com.pig4cloud.pig.admin.service.SysTenantService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Tag(description = "tenant", name = "租户管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysTenantController {

	private final SysTenantService sysTenantService;
	private final TenantConverter tenantConverter;

	@GetMapping("/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysTenantService.getById(id));
	}
	@HasPermission("sys:tenant:page")
	@GetMapping("/page")
	public R page(Page page, SysTenantDTO query) {
		return R.ok(sysTenantService.page(page, Wrappers.query(tenantConverter.toEntity(query))));
	}

	@SysLog("新增租户")
	@PostMapping
	@HasPermission("sys:tenant:add")
	public R save(@Validated(CreateGroup.class) @RequestBody SysTenantDTO sysTenant) {
		return R.ok(sysTenantService.save(tenantConverter.toEntity(sysTenant)));
	}

	@SysLog("删除租户")
	@DeleteMapping("/{id}")
	@HasPermission("sys:tenant:del")
	public R removeById(@PathVariable Long id) {
		return sysTenantService.removeTenantById(id);
	}

	@SysLog("更新租户")
	@PutMapping()
	@HasPermission("sys:tenant:edit")
	public R update(@Validated(UpdateGroup.class) @RequestBody SysTenantDTO sysTenant) {
		SysTenant tenant = tenantConverter.toEntity(sysTenant);
		tenant.setTenantCode(null);
		return R.ok(sysTenantService.updateById(tenant));
	}

	@SysLog("启用租户")
	@PutMapping("/enable/{id}")
	@HasPermission("sys:tenant:edit:enable")
	public R enableTenant(@PathVariable Long id) {
		return sysTenantService.enableTenant(id);
	}

	@SysLog("禁用租户")
	@PutMapping("/disable/{id}")
	@HasPermission("sys:tenant:edit:disable")
	public R disableTenant(@PathVariable Long id) {
		return sysTenantService.disableTenant(id);
	}

}