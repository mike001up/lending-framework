package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.SysTenantDatasourceDTO;
import com.pig4cloud.pig.admin.api.entity.SysTenantDatasource;
import com.pig4cloud.pig.admin.api.vo.SysTenantDatasourceVO;
import com.pig4cloud.pig.admin.convertor.TenantDsConverter;
import com.pig4cloud.pig.admin.service.SysTenantDatasourceService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.validation.group.CreateGroup;
import com.pig4cloud.pig.common.core.validation.group.UpdateGroup;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.pig4cloud.pig.common.core.util.CryptoUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/tenant-datasource")
@Tag(description = "tenantDatasource", name = "租户数据源配置模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysTenantDatasourceController {

	private final SysTenantDatasourceService sysTenantDatasourceService;
	private final TenantDsConverter tenantDsConverter;
	private final CryptoUtil cryptoUtil;

	@HasPermission("sys:tenant:ds:del")
	@GetMapping("/{id}")
	public R<SysTenantDatasourceVO> getById(@PathVariable Long id) {
		SysTenantDatasource ds = sysTenantDatasourceService.getById(id);
		if (ds != null) {
			ds.setPasswordEncrypted("******");
		}
		return R.ok(tenantDsConverter.toVo(ds));
	}

	@HasPermission("sys:tenant:ds:page")
	@GetMapping("/page")
	public R<IPage<SysTenantDatasourceVO>> page(Page page, SysTenantDatasourceDTO query) {
		Page<SysTenantDatasource> result = sysTenantDatasourceService.page(page, Wrappers.query(tenantDsConverter.toEntity(query)));
		IPage<SysTenantDatasourceVO> pageVO = result.convert(ds->{
			ds.setPasswordEncrypted("******");
			return tenantDsConverter.toVo(ds);
		});
		return R.ok(pageVO);
	}

	@SysLog("新增数据源配置")
	@PostMapping
	@HasPermission("sys:tenant:ds:add")
	public R save(@Validated(CreateGroup.class) @RequestBody SysTenantDatasourceDTO ds) {
		boolean ret = sysTenantDatasourceService.save(tenantDsConverter.toEntity(ds));
		return ret?R.ok():R.failed();
	}

	@SysLog("删除数据源配置")
	@DeleteMapping("/{id}")
	@HasPermission("sys:tenant:ds:del")
	public R removeById(@PathVariable Long id) {
		return R.ok(sysTenantDatasourceService.removeById(id));
	}

	@SysLog("更新数据源配置")
	@PutMapping
	@HasPermission("sys:tenant:ds:edit")
	public R update(@Validated(UpdateGroup.class) @RequestBody SysTenantDatasourceDTO ds) {
		return R.ok(sysTenantDatasourceService.updateById(tenantDsConverter.toEntity(ds)));
	}
	/**
	 * 测试连通性
	 * @param id
	 * @return
	 */
	@GetMapping("/test/{id}")
	@HasPermission("sys:tenant:ds:test")
	public R testConnection(@PathVariable Long id) {
		return sysTenantDatasourceService.testConnection(id);
	}

	// @Inner
	// @RequireServiceAuth
	@GetMapping("/tenant/{tenantId}")
	public R<List<SysTenantDatasourceVO>> getByTenantId(@PathVariable Long tenantId) {
		// List<SysTenantDatasource> list = sysTenantDatasourceService.listByTenantIdWithDecrypted(tenantId);

		// return R.ok(list);

		List<SysTenantDatasource> list = sysTenantDatasourceService.listByTenantIdWithDecrypted(tenantId);
		List<SysTenantDatasourceVO> voList = list.stream().map(ds->{
			ds.setPasswordEncrypted(cryptoUtil.aesGcmDecrypt(ds.getPasswordEncrypted()));
			return tenantDsConverter.toVo(ds);
		}).collect(Collectors.toList());
		return R.ok(voList);
	}

}
