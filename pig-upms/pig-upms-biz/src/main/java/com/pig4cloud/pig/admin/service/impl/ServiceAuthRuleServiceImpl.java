package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.ServiceAuthRule;
import com.pig4cloud.pig.admin.mapper.ServiceAuthRuleMapper;
import com.pig4cloud.pig.admin.service.ServiceAuthRuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceAuthRuleServiceImpl extends ServiceImpl<ServiceAuthRuleMapper, ServiceAuthRule>
		implements ServiceAuthRuleService {

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public boolean checkPermission(String callerServiceId, String providerServiceId, String requestPath) {
		List<ServiceAuthRule> rules = list(Wrappers.<ServiceAuthRule>lambdaQuery()
				.eq(ServiceAuthRule::getCallerService, callerServiceId)
				.eq(ServiceAuthRule::getProviderService, providerServiceId));
		for (ServiceAuthRule rule : rules) {
			if (pathMatcher.match(rule.getPathPattern(), requestPath)) {
				if (StrUtil.isBlank(rule.getHttpMethod()) || rule.getHttpMethod().equalsIgnoreCase("*")) {
					return Boolean.TRUE.equals(rule.getIsAllowed());
				}
			}
		}
		return false;
	}

	@Override
	public List<ServiceAuthRule> listRules() {
		return list();
	}

}
