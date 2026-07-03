package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysIpLimit;
import com.pig4cloud.pig.admin.api.entity.SysOauthClientDetails;
import com.pig4cloud.pig.admin.mapper.SysIpLimitMapper;
import com.pig4cloud.pig.admin.service.SysIpLimitService;
import com.pig4cloud.pig.admin.service.SysOauthClientDetailsService;
import com.pig4cloud.pig.common.core.constant.enums.IpActionEnum;
import com.pig4cloud.pig.common.core.util.IpMatcherUtil;
import com.pig4cloud.pig.common.core.util.R;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysIpLimitServiceImpl extends ServiceImpl<SysIpLimitMapper, SysIpLimit> implements SysIpLimitService {

	private final SysOauthClientDetailsService clientDetailsService;

	@Override
	public boolean isMatch(String remoteIP, List<SysIpLimit> list) {
		List<String> ipList = list.stream().map(SysIpLimit::getIp).collect(Collectors.toList());
		return ipList.contains(remoteIP);
	}

	/**
     * 检查 IP 是否允许访问
     * 使用 Spring Cache 缓存结果（可配置缓存管理器，如 Redis 或 Caffeine）
     * 缓存 key = clientId + "_" + ip，或按 clientId 缓存整个规则列表
     * 这里为了演示，直接使用 @Cacheable 缓存最终结果
     */
    @Cacheable(value = "ip_limit", key = "'gateway:ip_limit:result:'+#clientId + '_' + #remoteIp", unless = "#result == null")
	@Override
	public Boolean checkIp(String clientId, String remoteIp) {
		if (StrUtil.isBlank(clientId) || StrUtil.isBlank(remoteIp)) {
            return Boolean.FALSE;
        }

        // 1. 查询该 clientId 的所有规则
        LambdaQueryWrapper<SysIpLimit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysIpLimit::getClientId, clientId);
        List<SysIpLimit> rules = this.getBaseMapper().selectList(wrapper);

		SysOauthClientDetails client = clientDetailsService.getOne(Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId));
		if(client == null || StringUtils.isEmpty(client.getAdditionalInformation()) || !client.getAdditionalInformation().contains("ip_action")){
			return Boolean.FALSE;
		}
		String ipLimitAction = JSONUtil.parseObj(client.getAdditionalInformation()).getStr("ip_action");
		IpActionEnum ipActionEnum = IpActionEnum.valueOf(ipLimitAction);
        // 3. 无规则时，根据 ipAction 决定默认行为
        //    ALLOW: 无规则 => 拒绝（不允许任何 IP）
        //    DENY:  无规则 => 允许（不限制任何 IP）
        if (rules == null || rules.isEmpty()) {
            boolean defaultAllowed = ipActionEnum.DENY.equals(ipActionEnum);
            log.debug("clientId={} 无 IP 规则，默认 {}访问", clientId, defaultAllowed ? "允许" : "拒绝");
            return Boolean.valueOf(defaultAllowed);
        }

        // 4. 解析规则列表
		List<IpMatcherUtil.IpRule> ruleMatchers = new ArrayList<>();
        for (SysIpLimit rule : rules) {            
			try {
            	ruleMatchers.add(IpMatcherUtil.parseRule(rule.getIp()));
			} catch (IllegalArgumentException e) {
				log.warn("解析 IP 规则失败, clientId={}, pattern={}", clientId, rule.getIp(), e);
				return Boolean.FALSE;
			}
        }

        // 5. 执行匹配
        try {
            InetAddress address = InetAddress.getByName(remoteIp);
			for (IpMatcherUtil.IpRule matcher : ruleMatchers) {
				if (matcher.matches(address)) {
					// 匹配到规则：ALLOW 模式 => 允许，DENY 模式 => 拒绝
					boolean allowed = IpActionEnum.ALLOW.equals(ipLimitAction);
					log.debug("IP 匹配规则, clientId={}, ip={}, action={}, result={}",
							clientId, remoteIp, ipLimitAction, allowed);
					return Boolean.valueOf(allowed);
				}
			}
			//未匹配到
			return Boolean.valueOf(!IpActionEnum.ALLOW.equals(ipLimitAction));
        } catch (UnknownHostException e) {
            log.warn("解析 IP 失败: {}", remoteIp);
            return Boolean.FALSE;
        }
	}
}