package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.common.core.util.R;

public interface BizCollectionScheduleService extends IService<BizCollectionSchedule> {

    Boolean hasLastMonthDelayedOrders(BizContractInfo contract);

    void updateOverdueStatus();

    R audit(BizCollectionSchedule bizCollectionSchedule, BizContractInfo info);
}
