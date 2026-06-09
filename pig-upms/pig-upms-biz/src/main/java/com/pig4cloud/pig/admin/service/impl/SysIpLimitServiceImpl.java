package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysIpLimit;
import com.pig4cloud.pig.admin.mapper.SysIpLimitMapper;
import com.pig4cloud.pig.admin.service.SysIpLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysIpLimitServiceImpl extends ServiceImpl<SysIpLimitMapper, SysIpLimit> implements SysIpLimitService {

	@Override
	public boolean isMatch(String remoteIP, List<SysIpLimit> list) {
		List<String> ipList = list.stream().map(SysIpLimit::getIp).collect(Collectors.toList());
		return ipList.contains(remoteIP);
	}
}