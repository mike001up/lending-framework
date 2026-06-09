package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysIpLimit;
import com.pig4cloud.pig.admin.service.SysIpLimitService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.annotation.RequireServiceAuth;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ipLimit")
@Tag(description = "ipLimit", name = "后台IP白名单管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysIpLimitController {

	private final SysIpLimitService sysIpLimitService;

	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@HasPermission("admin_ipLimit_view")
	public R getSysIpLimitPage(@ParameterObject Page page, @ParameterObject SysIpLimit sysIpLimit) {
		LambdaQueryWrapper<SysIpLimit> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysIpLimit.getIp())) {
			wrapper.eq(SysIpLimit::getIp, sysIpLimit.getIp());
		}
		return R.ok(sysIpLimitService.page(page, wrapper));
	}

	@Operation(summary = "通过条件查询", description = "通过条件查询对象")
	@GetMapping("/details")
	@HasPermission("admin_ipLimit_view")
	public R getDetails(@ParameterObject SysIpLimit sysIpLimit) {
		return R.ok(sysIpLimitService.list(Wrappers.query(sysIpLimit)));
	}

	@Operation(summary = "新增后台IP白名单", description = "新增后台IP白名单")
	@SysLog("新增后台IP白名单")
	@PostMapping
	@HasPermission("admin_ipLimit_add")
	public R save(@RequestBody SysIpLimit sysIpLimit) {
		long count = sysIpLimitService.lambdaQuery()
				.eq(SysIpLimit::getIp, sysIpLimit.getIp())
				.count();
		if (count > 0) return R.ok();
		return R.ok(sysIpLimitService.save(sysIpLimit));
	}

	@Operation(summary = "修改后台IP白名单", description = "修改后台IP白名单")
	@SysLog("修改后台IP白名单")
	@PutMapping
	@HasPermission("admin_ipLimit_edit")
	public R updateById(@RequestBody SysIpLimit sysIpLimit) {
		long count = sysIpLimitService.lambdaQuery()
				.eq(SysIpLimit::getIp, sysIpLimit.getIp())
				.ne(SysIpLimit::getId, sysIpLimit.getId())
				.count();
		if (count > 0) return R.ok();
		return R.ok(sysIpLimitService.updateById(sysIpLimit));
	}

	@Operation(summary = "通过id删除后台IP白名单", description = "通过id删除后台IP白名单")
	@SysLog("通过id删除后台IP白名单")
	@DeleteMapping
	@HasPermission("admin_ipLimit_del")
	public R removeById(@RequestBody Long[] ids) {
		return R.ok(sysIpLimitService.removeBatchByIds(CollUtil.toList(ids)));
	}

	@ResponseExcel
	@GetMapping("/export")
	@HasPermission("admin_ipLimit_export")
	public List<SysIpLimit> exportExcel(SysIpLimit sysIpLimit, Long[] ids) {
		return sysIpLimitService.list(Wrappers.lambdaQuery(sysIpLimit).in(ArrayUtil.isNotEmpty(ids), SysIpLimit::getId, ids));
	}

	@PostMapping("/import")
	@HasPermission("admin_ipLimit_export")
	public R importExcel(@RequestExcel List<SysIpLimit> sysIpLimitList, BindingResult bindingResult) {
		return R.ok(sysIpLimitService.saveBatch(sysIpLimitList));
	}

	@Inner
	@RequireServiceAuth
	@GetMapping("/isValidIP")
	public Boolean isValidIP(@RequestParam("remoteIP") String remoteIP) {
		if (StringUtils.isBlank(remoteIP)) {
			return false;
		}
		List<SysIpLimit> list = sysIpLimitService.list();
		if (CollUtil.isEmpty(list)) {
			return true;
		}
		return sysIpLimitService.isMatch(remoteIP, list);
	}
}