package com.pig4cloud.pig.common.core.util;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GetClient {

    public static String get() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        String client = CommonConstants.BMS;
        if (attributes != null) {
            request = attributes.getRequest();
            client = request.getHeader(CommonConstants.CLIENT);
        }
        return client;
    }
}
