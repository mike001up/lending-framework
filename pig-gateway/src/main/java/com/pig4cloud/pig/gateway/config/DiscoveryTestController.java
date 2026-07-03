package com.pig4cloud.pig.gateway.config;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pig4cloud.pig.common.core.constant.enums.HttpMethodEnum;
import com.pig4cloud.pig.common.core.constant.enums.LogTypeEnum;
import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.event.SysLogEventSource;
import com.pig4cloud.pig.common.log.feign.RemoteLogServiceFeignClient;
import com.pig4cloud.pig.common.log.util.SysLogUtils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class DiscoveryTestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    RemoteLogServiceFeignClient logService;

    @GetMapping("/test/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/test/choose/{service}")
    public Mono<String> choose(@PathVariable String service) {
        return Mono.fromCallable(() -> {
            ServiceInstance instance = loadBalancerClient.choose(service);
            return instance == null ? "null" : instance.getUri().toString();
        }).subscribeOn(Schedulers.boundedElastic());
    }
    @GetMapping("/test/service-token")
    public Mono<R<Boolean>> getServiceToken() {
        return Mono.fromCallable(() -> {
            RemoteSysLogDTO sysLog = new RemoteSysLogDTO();
		    sysLog.setLogType(LogTypeEnum.NORMAL);
            sysLog.setRequestUri("/test/service-token");
            sysLog.setMethod(HttpMethodEnum.GET);
            sysLog.setRemoteAddr("/test/service-token");
            sysLog.setCreateBy("admin");
            sysLog.setServiceId(SpringUtil.getProperty("spring.application.name"));
            sysLog.setResult(1);
            sysLog.setOperationType("OPERATION");
            R<Boolean> result = logService.saveLog(sysLog);
            return result;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
