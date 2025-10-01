package com.pig4cloud.pig.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.mapper.BizCollectionScheduleMapper;
import com.pig4cloud.pig.admin.service.BizCollectionScheduleService;
import com.pig4cloud.pig.admin.service.SysPublicParamService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * 催款计划信息表
 *
 * @author pig
 * @date 2025-09-29 13:25:39
 */
@Service
@Slf4j
public class BizCollectionScheduleServiceImpl extends ServiceImpl<BizCollectionScheduleMapper, BizCollectionSchedule> implements BizCollectionScheduleService {

    @Autowired
    private SysPublicParamService sysPublicParamService;

    @Override
    public BigDecimal getTotalIncomeAmount(Long contractId) {
        return this.baseMapper.getTotalIncomeAmount(contractId);
    }

    @Override
    public Boolean hasLastMonthDelayedOrders(BizContractInfo contract) {
        //查看是不是第一次
        List<BizCollectionSchedule> list = this.list(Wrappers.<BizCollectionSchedule>query().lambda().eq(BizCollectionSchedule::getContractId, contract.getContractId()));
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1 && list.get(0).getStatus() == 1) {
            String exemptionDays = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.EXEMPTION_DAYS);
            //判断上月最后一天离滞纳金开始计算日期有几天
            Integer startLateFeeDate = contract.getStartLateFeeDate();
            Integer endLateFeeDate = startLateFeeDate + Integer.valueOf(exemptionDays);
            // 上个月最后一天
            LocalDate lastDayOfPrevMonth = LocalDate.now()
                    .minusMonths(1)
                    .with(TemporalAdjusters.lastDayOfMonth());
            // 取出“天”部分
            int lastDay = lastDayOfPrevMonth.getDayOfMonth();
            if (lastDay > endLateFeeDate) {
                return true;
            }
        }
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.minusMonths(1).withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = now.withDayOfMonth(1).atStartOfDay().minusSeconds(1);
        boolean exists = this.lambdaQuery().eq(BizCollectionSchedule::getStatus, 1)
                .eq(BizCollectionSchedule::getContractId, contract.getContractId())
                .between(BizCollectionSchedule::getColStartDate, start, end).exists();
        return exists;
    }

    @Override
    @Transactional
    public void updateOverdueStatus() {
        LocalDateTime now = LocalDateTime.now();
        // 更新收款结束日之前未收款的记录
        LambdaUpdateWrapper<BizCollectionSchedule> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.lt(BizCollectionSchedule::getColEndDate, now) // 收款结束日 < 当前时间
                .eq(BizCollectionSchedule::getStatus, 0)      // 待收款
                .set(BizCollectionSchedule::getStatus, 1);    // 设置为延迟收款
        int updatedCount = this.baseMapper.update(null, updateWrapper);
        log.info("延迟收款更新条数：" + updatedCount);
    }

}
