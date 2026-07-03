package com.pig4cloud.pig.admin.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pig4cloud.pig.admin.api.entity.SysLog;
import com.pig4cloud.pig.admin.service.SysLogService;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.R;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class LocalLogServiceImpl implements RemoteLogService{
    private final SysLogService sysLogService;
    @Override
    public R<Boolean> saveLog(RemoteSysLogDTO sysLogDTO) {
        SysLog sysLog = new SysLog();
        return R.ok(sysLogService.save(sysLog));
    }

}
