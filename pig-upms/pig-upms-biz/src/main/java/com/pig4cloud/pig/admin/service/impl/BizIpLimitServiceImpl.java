package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.BizIpLimit;
import com.pig4cloud.pig.admin.mapper.BizIpLimitMapper;
import com.pig4cloud.pig.admin.service.BizIpLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台IP白名单
 *
 * @author pig
 * @date 2025-08-28 16:57:13
 */
@Service
@Slf4j
public class BizIpLimitServiceImpl extends ServiceImpl<BizIpLimitMapper, BizIpLimit> implements BizIpLimitService {

    @Override
    public boolean isMatch(String remoteIP) {
        List<BizIpLimit> existingIps = this.list();
        if (CollUtil.isEmpty(existingIps)) {
            return true;
        }
        List<String> ipList = existingIps.stream().map(BizIpLimit::getIp).collect(Collectors.toList());
        return ipList.contains(remoteIP);
    }
}
