package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.admin.api.entity.ServiceAuthRule;
import com.pig4cloud.pig.admin.service.ServiceAuthRuleService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/serviceAuth")
public class ServiceAuthRuleController {

	private final ServiceAuthRuleService serviceAuthRuleService;

	@Inner
	@GetMapping("/check")
	public Boolean checkServiceAuth(@RequestParam String callerServiceId,
			@RequestParam String providerServiceId,
			@RequestParam String requestPath) {
		return serviceAuthRuleService.checkPermission(callerServiceId, providerServiceId, requestPath);
	}

	@GetMapping("/rules")
	public R<List<ServiceAuthRule>> listRules() {
		return R.ok(serviceAuthRuleService.listRules());
	}

	@PostMapping("/rule")
	public R<Boolean> addRule(@RequestBody ServiceAuthRule rule) {
		return R.ok(serviceAuthRuleService.save(rule));
	}

	@DeleteMapping("/rule/{id}")
	public R<Boolean> deleteRule(@PathVariable Long id) {
		return R.ok(serviceAuthRuleService.removeById(id));
	}

}
