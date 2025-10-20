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

package com.pig4cloud.pig.admin.api.feign;


import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;
import com.pig4cloud.pig.common.core.config.FeignConfig;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lengleng
 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteCollectInfoService", value = ServiceNameConstants.UPMS_SERVICE, configuration = FeignConfig.class)
public interface RemoteCollectInfoService {


    @NoToken
    @PostMapping("/bizContractInfo/detail")
    RepaymentDetailVo detail(@RequestBody BizContractInfo contract);

    @NoToken
    @GetMapping("/bizContractInfo/detailByIssue")
    RepaymentDetailVo detailByIssue(@RequestParam("contractNo") Long contractNo, @RequestParam("period") int period);

    @NoToken
    @PostMapping("/bizContractInfo/finish")
    R finish(@RequestBody BizContractInfo contract);

}
