package com.pig4cloud.pig.admin.task;

import com.pig4cloud.pig.admin.service.BizCollectionScheduleService;
import com.pig4cloud.pig.admin.service.BizContractInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CollectionScheduleTask {

    @Autowired
    private BizContractInfoService contractInfoService;

    @Autowired
    private BizCollectionScheduleService bizCollectionScheduleService;

    /**
     * 每天凌晨 2 点检查并生成收款计划
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void generateMonthlySchedules() {
        contractInfoService.generateCollectionSchedules();
    }


    /**
     * 每天凌晨 1 点扫描未收款计划，将过期的标记为延迟收款
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void scanAndUpdateOverdue() {
        bizCollectionScheduleService.updateOverdueStatus();
    }
}
