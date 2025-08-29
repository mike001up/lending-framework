

package com.pig4cloud.pig.gateway.fegin;


import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;




@FeignClient(contextId = "remoteIPlimitService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteIPLimitService {
    /**
     * 验证登录Ip是否在白名单中
     *
     * @param remoteIP 需要验证的Ip
     * @return Boolean 有效IP返回true，否则返回 false
     */
    @GetMapping("/bizIpLimit/isValidIP")
    Boolean isValidIP(@RequestHeader(SecurityConstants.FROM) String from, @RequestParam("remoteIP") String remoteIP);
}
