package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.common.core.util.R;

import java.math.BigDecimal;

public interface BizCollectionScheduleService extends IService<BizCollectionSchedule> {

    BigDecimal getTotalIncomeAmount(Long contractId);

    Boolean hasLastMonthDelayedOrders(BizContractInfo contract);

    void updateOverdueStatus();

    R verificationStatus(BizCollectionSchedule bizCollectionSchedule);

    R audit(BizCollectionSchedule bizCollectionSchedule, BizContractInfo info);
}
