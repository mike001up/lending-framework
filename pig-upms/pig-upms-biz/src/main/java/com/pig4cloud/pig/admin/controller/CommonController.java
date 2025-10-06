package com.pig4cloud.pig.admin.controller;

import com.pig4cloud.pig.admin.service.SysPublicParamService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.guaranty.api.feign.RemoteCollateralTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Tag(description = "common", name = "公共接口")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class CommonController {


    @Autowired
    private SysPublicParamService sysPublicParamService;
    @Autowired
    private RemoteCollateralTypeService remoteCollateralTypeService;


    @Operation(summary = "币种", description = "币种")
    @GetMapping("/currency")
    public R currency() {
        List<String> list = Arrays.asList();
        String value = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.CURRENCY);
        if (StringUtils.isNotBlank(value)) {
            list = Arrays.stream(value.split(",")).map(String::trim)         // 去掉前后空格
                    .filter(s -> !s.isEmpty()) // 过滤空项
                    .collect(Collectors.toList());
        }
        return R.ok(list);
    }

    @Operation(summary = "获取所有抵押物类型", description = "获取所有抵押物类型")
    @GetMapping("/getCollateralType")
    public R getCollateralType() {
        return R.ok(remoteCollateralTypeService.getAllCollateralType());
    }
}
