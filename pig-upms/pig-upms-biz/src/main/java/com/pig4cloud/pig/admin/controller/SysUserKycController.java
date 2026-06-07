package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.mapper.SysUserMapper;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user-kyc")
@Tag(description = "userKyc", name = "用户KYC实名认证模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserKycController {

	private final SysUserMapper sysUserMapper;

	private final CacheManager cacheManager;

	@GetMapping("/{userId}")
	public R getByUserId(@PathVariable Long userId) {
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser == null) {
			return R.failed("用户不存在");
		}
		return R.ok(Map.of(
			"userId", sysUser.getUserId(),
			"certificationStatus", sysUser.getCertificationStatus() != null ? sysUser.getCertificationStatus() : "not_certified",
			"certificationInfo", sysUser.getCertificationInfo() != null ? sysUser.getCertificationInfo() : ""
		));
	}

	@SysLog("提交实名认证")
	@PostMapping
	public R submitCertification(@RequestBody Map<String, Object> body) {
		Long userId = Long.valueOf(body.get("userId").toString());
		String certificationInfo = body.get("certificationInfo") != null ? body.get("certificationInfo").toString() : null;

		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser == null) {
			return R.failed("用户不存在");
		}
		if ("in_progress".equals(sysUser.getCertificationStatus())) {
			return R.failed("认证已在审核中，请勿重复提交");
		}

		sysUser.setCertificationStatus("in_progress");
		sysUser.setCertificationInfo(certificationInfo);
		sysUserMapper.updateById(sysUser);
		evictUserCache(sysUser);
		return R.ok();
	}

	@SysLog("审核实名认证")
	@PutMapping("/review/{userId}")
	@HasPermission("sys_user_cert_review")
	public R reviewCertification(@PathVariable Long userId, @RequestParam String result) {
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser == null) {
			return R.failed("用户不存在");
		}
		if ("approved".equals(result)) {
			sysUser.setCertificationStatus("certified");
		}
		else {
			sysUser.setCertificationStatus("failed");
		}
		sysUserMapper.updateById(sysUser);
		evictUserCache(sysUser);
		return R.ok();
	}

	private void evictUserCache(SysUser sysUser) {
		var cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null) {
			cache.evictIfPresent(CacheConstants.USER_DETAILS_KEY_PREFIX + sysUser.getUsername());
		}
	}

}
