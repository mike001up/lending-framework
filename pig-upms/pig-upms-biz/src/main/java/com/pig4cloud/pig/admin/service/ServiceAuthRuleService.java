package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.ServiceAuthRule;

import java.util.List;

public interface ServiceAuthRuleService extends IService<ServiceAuthRule> {

	boolean checkPermission(String callerServiceId, String providerServiceId, String requestPath);

	List<ServiceAuthRule> listRules();

}
