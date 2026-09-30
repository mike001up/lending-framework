package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pig.admin.api.dto.UserCertificationInfoDTO;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.UserCertificationInfo;
import com.pig4cloud.pig.admin.convertor.UserConverter;
import com.pig4cloud.pig.admin.mapper.SysUserMapper;
import com.pig4cloud.pig.common.core.constant.CacheConstants;
import com.pig4cloud.pig.common.core.constant.enums.CertificationStatusEnum;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.util.SecurityUtils;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user-kyc")
@Tag(description = "userKyc", name = "用户KYC实名认证模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserKycController {

	private final SysUserMapper sysUserMapper;

	private final UserConverter userConverter;


	@PostMapping
	@HasPermission("upms:user:cert:submit")
	@Transactional
	public R submitCertification(@Valid @RequestBody UserCertificationInfoDTO body) {
		Long userId = SecurityUtils.getUser().getId();
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser == null) {
			return R.failed("用户不存在");
		}
		if (!CertificationStatusEnum.NOT_CERTIFIED.equals(sysUser.getCertificationStatus())
			&& !CertificationStatusEnum.REJECTED.equals(sysUser.getCertificationStatus())) {
			return R.failed("请勿重复提交");
		}

		sysUser.setCertificationStatus(CertificationStatusEnum.PENDING);
		sysUser.setCertificationInfo(userConverter.toUserCertificationInfo(body));
		sysUserMapper.updateById(sysUser);
		return R.ok();
	}

	@SysLog("审核实名认证")
	@PutMapping("/review/{userId}")
	@HasPermission("upms:user:cert:review")
	@Transactional
	public R reviewCertification(@PathVariable Long userId, @RequestParam CertificationStatusEnum result) {
		if(!result.equals(CertificationStatusEnum.APPROVED) 
			&& !result.equals(CertificationStatusEnum.REJECTED)){
			return R.failed("审核结果状态错误");
		}
		int count = sysUserMapper.update(Wrappers.<SysUser>lambdaUpdate()
		.eq(SysUser::getUserId, userId)
		.eq(SysUser::getCertificationStatus, CertificationStatusEnum.PENDING)
		.set(SysUser::getCertificationStatus, result)
		.set(SysUser::getUpdateBy, SecurityUtils.getUser().getName())
		.set(SysUser::getUpdateTime, Instant.now()));
		return count >0 ?R.ok(): R.failed("审核失败");
	}
	/**
	 * 重置用户实名认证状态（仅管理员/客服）
	 * 将已认证(APPROVED)的用户状态重置为未认证(NOT_CERTIFIED)，清空认证信息，以便用户重新提交。
	 */
	@SysLog("重置用户实名认证")
	@PutMapping("/reset/{userId}")
	@HasPermission("upms:user:cert:reset")   // 需要配置对应的权限标识
	@Transactional
	public R resetCertification(@PathVariable Long userId) {
		// 1. 检查用户是否存在
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser == null) {
			return R.failed("用户不存在");
		}

		// 2. 只允许重置已经认证(APPROVED)的用户（可酌情允许REJECTED也可以重置，根据业务决定）
		if (!CertificationStatusEnum.APPROVED.equals(sysUser.getCertificationStatus())) {
			return R.failed("只有已认证的用户才能被重置");
		}

		// 3. 重置状态为未认证，清空认证信息
		int count = sysUserMapper.update(Wrappers.<SysUser>lambdaUpdate()
		.eq(SysUser::getUserId, userId)
		.eq(SysUser::getCertificationStatus, CertificationStatusEnum.APPROVED)
		.set(SysUser::getCertificationStatus, CertificationStatusEnum.NOT_CERTIFIED));
		if (count <= 0) {
			return R.failed("重置失败，请稍后重试");
		}

		// 6. 记录操作日志（@SysLog 已自动记录，但可补充更详细的信息）
		return R.ok("重置成功，用户需重新提交实名认证");
	}
}
