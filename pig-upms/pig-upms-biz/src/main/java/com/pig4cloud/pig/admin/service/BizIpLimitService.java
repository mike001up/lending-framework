package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.BizIpLimit;

import java.util.List;


public interface BizIpLimitService extends IService<BizIpLimit> {

    boolean isMatch(String remoteIP, List<BizIpLimit> list);

}
