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

package com.pig4cloud.pig.guaranty.api.feign;


import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.feign.annotation.NoToken;
import com.pig4cloud.pig.guaranty.api.entity.DicCollateralType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author lengleng
 * @date 2018/6/28
 */
@FeignClient(contextId = "remoteCollateralTypeService", value = ServiceNameConstants.GUARANTY_SERVICE)
public interface RemoteCollateralTypeService {


	@NoToken
	@GetMapping("/dicCollateralType/getAllCollateralType")
	List<DicCollateralType> getAllCollateralType();

}
