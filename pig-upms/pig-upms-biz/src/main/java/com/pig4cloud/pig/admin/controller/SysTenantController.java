package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysTenant;
import com.pig4cloud.pig.admin.service.SysTenantService;
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
@RequestMapping("/tenant")
@Tag(description = "tenant", name = "租户管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysTenantController {

	private final SysTenantService sysTenantService;

	@GetMapping("/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysTenantService.getById(id));
	}

	@GetMapping("/page")
	public R page(Page page, SysTenant query) {
		return R.ok(sysTenantService.page(page, Wrappers.query(query)));
	}

	@SysLog("新增租户")
	@PostMapping
	@HasPermission("sys_tenant_add")
	public R save(@Valid @RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.save(sysTenant));
	}

	@SysLog("删除租户")
	@DeleteMapping("/{id}")
	@HasPermission("sys_tenant_del")
	public R removeById(@PathVariable Long id) {
		return R.ok(sysTenantService.removeById(id));
	}

	@SysLog("更新租户")
	@PutMapping
	@HasPermission("sys_tenant_edit")
	public R update(@Valid @RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.updateById(sysTenant));
	}

}