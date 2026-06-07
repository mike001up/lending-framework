package com.pig4cloud.pig.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.dto.SysLogDTO;
import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.mapper.SysLogMapper;
import com.pig4cloud.pig.admin.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	@Override
	public Page getLogByPage(Page page, SysLogDTO sysLog) {
		return baseMapper.selectPage(page, buildQuery(sysLog));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveLog(SysLog sysLog) {
		baseMapper.insert(sysLog);
		return Boolean.TRUE;
	}

	@Override
	public List<SysLog> getList(SysLogDTO sysLog) {
		return baseMapper.selectList(buildQuery(sysLog));
	}

	private LambdaQueryWrapper buildQuery(SysLogDTO sysLog) {
		LambdaQueryWrapper<SysLog> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getLogType())) {
			wrapper.eq(SysLog::getLogType, sysLog.getLogType());
		}
		if (StrUtil.isNotBlank(sysLog.getOperationType())) {
			wrapper.eq(SysLog::getOperationType, sysLog.getOperationType());
		}
		if (sysLog.getUserId() != null) {
			wrapper.eq(SysLog::getUserId, sysLog.getUserId());
		}
		if (sysLog.getResult() != null) {
			wrapper.eq(SysLog::getResult, sysLog.getResult());
		}
		if (StrUtil.isNotBlank(sysLog.getClientName())) {
			wrapper.eq(SysLog::getClientName, sysLog.getClientName());
		}
		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLog::getCreateTime, sysLog.getCreateTime()[0])
				.le(SysLog::getCreateTime, sysLog.getCreateTime()[1]);
		}
		return wrapper;
	}

}
