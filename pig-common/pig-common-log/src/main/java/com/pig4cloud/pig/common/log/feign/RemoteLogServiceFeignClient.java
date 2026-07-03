/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.pig4cloud.pig.common.log.feign;

import com.pig4cloud.pig.common.core.annotation.InternalFeign;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.feign.RemoteLogService;
import com.pig4cloud.pig.common.core.util.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lengleng
 * @date 2018/6/28
 */
@FeignClient(contextId = "RemoteLogServiceFeignClient", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteLogServiceFeignClient extends RemoteLogService{

	/**
	 * 保存日志 (异步多线程调用，无token)
	 * @param sysLog 日志实体
	 * @return succes、false
	 */
	@Override
	@PostMapping("/log/save")
	@InternalFeign
	R<Boolean> saveLog(@RequestBody RemoteSysLogDTO sysLog);

}
