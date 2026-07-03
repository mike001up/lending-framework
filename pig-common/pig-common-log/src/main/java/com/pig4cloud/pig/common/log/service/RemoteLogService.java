package com.pig4cloud.pig.common.log.service;

import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.util.R;

public interface RemoteLogService {
    R<Boolean> saveLog(RemoteSysLogDTO sysLog);
}
