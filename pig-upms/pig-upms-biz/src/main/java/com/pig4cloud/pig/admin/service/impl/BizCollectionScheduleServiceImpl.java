package com.pig4cloud.pig.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.*;
import com.pig4cloud.pig.admin.mapper.BizCollectionScheduleMapper;
import com.pig4cloud.pig.admin.service.*;
import com.pig4cloud.pig.common.core.constant.BusinessConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.FinanceCalcUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @Autowired
    private BizCollectionDetailsService bizCollectionDetailsService;
    @Autowired
    private BizContractExecutionService bizContractExecutionService;

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
            LocalDate lastDayOfPrevMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            // 取出“天”部分
            int lastDay = lastDayOfPrevMonth.getDayOfMonth();
            if (lastDay > endLateFeeDate) {
                return true;
            }
        }
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.minusMonths(1).withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = now.withDayOfMonth(1).atStartOfDay().minusSeconds(1);
        boolean exists = this.lambdaQuery().eq(BizCollectionSchedule::getStatus, 1).eq(BizCollectionSchedule::getContractId, contract.getContractId()).between(BizCollectionSchedule::getColStartDate, start, end).exists();
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

    @Override
    public R verificationStatus(BizCollectionSchedule bizCollectionSchedule) {
        BizCollectionSchedule collectionScheduleServiceById = this.getById(bizCollectionSchedule.getId());
        //已收款 不能更改成其他状态
        if (collectionScheduleServiceById != null && collectionScheduleServiceById.getStatus().equals(BusinessConstants.PAYMENT_STATUS) && bizCollectionSchedule.getStatus().equals(collectionScheduleServiceById.getStatus())) {
            return R.failed(MsgUtils.getMessage("sys.changed.status"));
        }
        return R.ok(this.updateById(bizCollectionSchedule));
    }

    @Override
    @Transactional
    public R audit(BizCollectionSchedule bizCollectionSchedule, BizContractInfo info) {
        //如果审核通过,更新到收款详情以及合同执行情况
        if (bizCollectionSchedule.getApproveStatus().equals(BusinessConstants.REVIEW_STATUS)) {
            //防止没传一些数据
            BizCollectionSchedule collectionScheduleServiceById = this.getById(bizCollectionSchedule.getId());
            //如果之前状态是审核通过,就报错,不允许重复审核
            if (collectionScheduleServiceById.getApproveStatus().equals(BusinessConstants.REVIEW_STATUS)) {
                return R.ok(MsgUtils.getMessage("sys.not.allowedchanged.review"));
            }
            //如果实际收款金额没填也报错
            if (collectionScheduleServiceById.getCollectedAmount() == null || collectionScheduleServiceById.getCollectedAmount().compareTo(BigDecimal.ZERO) == 0) {
                return R.ok(MsgUtils.getMessage("sys.not.actual.amount"));
            }
            BizCollectionDetails bizCollectionDetails = new BizCollectionDetails();
            bizCollectionDetails.setContractId(collectionScheduleServiceById.getContractId());
            bizCollectionDetails.setRepaymentPeriod(DateTimeUtil.now());
            bizCollectionDetails.setPreBalance(collectionScheduleServiceById.getBalance());
            //收款后余额 = 收款前余额 - 本期实际收款金额
            BigDecimal postBalance = bizCollectionDetails.getPreBalance().subtract(collectionScheduleServiceById.getCollectedAmount());
            bizCollectionDetails.setPostBalance(postBalance);
            bizCollectionDetails.setRemainsPrincipal(collectionScheduleServiceById.getMinAmount());
            bizCollectionDetails.setLateFee(collectionScheduleServiceById.getLateFee());
            BigDecimal periodInterest = FinanceCalcUtil.calculateInterest(bizCollectionDetails.getRemainsPrincipal(), info.getInterestRate());
            bizCollectionDetails.setPeriodInterest(periodInterest);
            bizCollectionDetails.setRemainsInterest(periodInterest);
            bizCollectionDetails.setCollectedPrincipal(bizCollectionDetails.getRemainsPrincipal());
            bizCollectionDetails.setCollectedInterest(bizCollectionDetails.getRemainsInterest());
            bizCollectionDetails.setCreateTime(DateTimeUtil.now());
            bizCollectionDetails.setCreateBy(SecurityUtils.getUser().getUsername());
            bizCollectionDetailsService.save(bizCollectionDetails);
            BizContractExecution contractExecution = bizContractExecutionService.getOne(Wrappers.<BizContractExecution>lambdaQuery().eq(BizContractExecution::getContractId, bizCollectionSchedule.getContractId()));
            if (contractExecution != null) {
                //还款余额
                BigDecimal balance = contractExecution.getBalance();
                balance = balance.subtract(collectionScheduleServiceById.getCollectedAmount());
                contractExecution.setBalance(balance);
                BigDecimal totalMoney = contractExecution.getTotalMoney();
                //已还
                BigDecimal repaidMoney = totalMoney.subtract(balance);
                BigDecimal repaymentProgress = repaidMoney.divide(totalMoney, 3, RoundingMode.HALF_UP);
                contractExecution.setRepaymentProgress(repaymentProgress);
                bizContractExecutionService.updateById(contractExecution);
            }

        }
        return R.ok(this.updateById(bizCollectionSchedule));
    }

}
