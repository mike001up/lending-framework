package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;

import java.math.BigDecimal;

public interface BizContractInfoService extends IService<BizContractInfo> {

    void generateCollectionSchedules();

    BigDecimal totalInterest(BizContractInfo contract);

    BigDecimal lateFee(BizContractInfo contract);
}
