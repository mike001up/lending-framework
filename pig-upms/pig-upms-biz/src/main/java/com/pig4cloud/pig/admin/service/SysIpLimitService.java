package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysIpLimit;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;

public interface SysIpLimitService extends IService<SysIpLimit> {

	boolean isMatch(String remoteIP, List<SysIpLimit> list);

    Boolean checkIp(String clientId, String remoteIP);

}