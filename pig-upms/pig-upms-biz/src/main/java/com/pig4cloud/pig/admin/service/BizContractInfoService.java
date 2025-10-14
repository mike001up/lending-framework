package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.api.vo.BizContractInfoVo;
import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;

public interface BizContractInfoService extends IService<BizContractInfo> {

    void generateCollectionSchedules();

    IPage<BizContractInfoVo> getPage(Page page, BizContractInfo bizContractInfo);

    RepaymentDetailVo getDetailVo(BizContractInfo contract);

    RepaymentDetailVo getRepaymentDetailVo(BizContractInfo contract);

    void saveRepaymentDetailToRedis(RepaymentDetailVo detail, Long contractNo, int period);

    RepaymentDetailVo getRepaymentDetailFromRedis(Long contractNo, int period);
}
