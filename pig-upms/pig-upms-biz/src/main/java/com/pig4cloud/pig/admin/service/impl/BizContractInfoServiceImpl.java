package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import com.pig4cloud.pig.admin.api.entity.BizContractExecution;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.mapper.BizContractInfoMapper;
import com.pig4cloud.pig.admin.service.BizCollectionScheduleService;
import com.pig4cloud.pig.admin.service.BizContractExecutionService;
import com.pig4cloud.pig.admin.service.BizContractInfoService;
import com.pig4cloud.pig.admin.service.SysPublicParamService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.FinanceCalcUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同表
 *
 * @author pig
 * @date 2025-09-29 12:58:48
 */
@Service
public class BizContractInfoServiceImpl extends ServiceImpl<BizContractInfoMapper, BizContractInfo> implements BizContractInfoService {

    @Autowired
    private BizCollectionScheduleService collectionScheduleService;
    @Autowired
    private SysPublicParamService sysPublicParamService;
    @Autowired
    private BizContractExecutionService bizContractExecutionService;

    @Override
    @Transactional
    public void generateCollectionSchedules() {
        // 查询未结束的合同
        List<BizContractInfo> contractList = this.lambdaQuery().eq(BizContractInfo::getIsRunning, 1).list();

        LocalDate today = LocalDate.now();

        // 本月第一天和最后一天
        LocalDateTime firstDayOfMonth = DateTimeUtil.getFirstMomentOfMonth(today);
        LocalDateTime lastDayOfMonth = DateTimeUtil.getLastMomentOfMonth(today);

        for (BizContractInfo contract : contractList) {
            // 本月是否已生成收款计划
            Long count = collectionScheduleService.count(Wrappers.<BizCollectionSchedule>query().lambda().eq(BizCollectionSchedule::getContractId, contract.getContractId()).between(BizCollectionSchedule::getColStartDate, firstDayOfMonth, lastDayOfMonth));
            if (count > 0) {
                continue; // 已生成，跳过
            }

            // 计算总利息
            BigDecimal totalInterest = this.totalInterest(contract);

            // 计算总入款金额(总还款金额)
            BigDecimal totalIncomeAmount = collectionScheduleService.getTotalIncomeAmount(contract.getContractId());

            // 计算本期还款前余额 = 总利息 + 实付金额(实际借款金额) + 手续费 - 已经还款的金额
            BigDecimal fundAmount = contract.getFundAmount() == null ? BigDecimal.ZERO : contract.getFundAmount();
            BigDecimal feeAmount = contract.getFeeAmount() == null ? BigDecimal.ZERO : contract.getFeeAmount();
            BigDecimal balance = totalInterest.add(fundAmount).add(feeAmount).subtract(totalIncomeAmount).setScale(2, RoundingMode.HALF_UP);

            BigDecimal lateFee = this.lateFee(contract);
            // 计算每期最小金额
            BigDecimal minAmount = BigDecimal.ZERO;
            if (contract.getLoanTerm() != null && contract.getLoanTerm() > 0) {
                //最小还款金额 = 实际借款金额/期数
                minAmount = fundAmount.divide(BigDecimal.valueOf(contract.getLoanTerm()), 2, RoundingMode.HALF_UP);
            }

            // 收款开始日
            int periodStartDay = contract.getPeriodStartDate() == null || contract.getPeriodStartDate() <= 0 ? 1 : contract.getPeriodStartDate();
            periodStartDay = Math.min(periodStartDay, today.lengthOfMonth()); // 防止超过当月天数
            LocalDate startDate = today.withDayOfMonth(periodStartDay);

            //比较现在是不是大于等于收款开始时间,如果不大于等于那么不生成账单,只有到了日期才生成账单
            if (!DateTimeUtil.greaterOrEqual(today, startDate)) {
                continue;
            }
            // 收款结束日 = 开始日 + period_days
            // 获取合同天数，处理 null 或 <=0 的情况
            int periodDays = contract.getPeriodDays() == null || contract.getPeriodDays() <= 0 ? 1 : contract.getPeriodDays();

            // 结束日期，开始日算作第 1 天，所以减 1
            LocalDate endDate = startDate.plusDays(periodDays - 1);


            // 计算本月利息 = 本期本金 * 月利率
            BigDecimal monthlyInterest = FinanceCalcUtil.calculateInterest(minAmount, contract.getInterestRate());
            //最大还款金额 = 本金 + 利息 + 滞纳金
            BigDecimal maxAmount = minAmount.add(monthlyInterest).add(lateFee).setScale(2, RoundingMode.HALF_UP);

            // 构建催款计划
            BizCollectionSchedule schedule = new BizCollectionSchedule();
            schedule.setContractId(contract.getContractId());
            schedule.setUserId(contract.getUserId());
            schedule.setUserName(contract.getUserName());
            schedule.setRealName(contract.getRealName());
            schedule.setBalance(balance);
            schedule.setMinAmount(minAmount);
            schedule.setMaxAmount(maxAmount);
            schedule.setColStartDate(DateTimeUtil.startOfDay(startDate));
            schedule.setColEndDate(DateTimeUtil.endOfDay(endDate));
            schedule.setStatus(0); // 待收款
            schedule.setLateFee(lateFee);
            schedule.setCreateBy("admin");
            schedule.setCreateTime(DateTimeUtil.now());
            collectionScheduleService.save(schedule);
            //查看是否合同执行情况记录
            BizContractExecution execution = bizContractExecutionService.getOne(Wrappers.<BizContractExecution>query().lambda().eq(BizContractExecution::getContractId, contract.getContractId()));
            if (execution == null) {
                execution = new BizContractExecution();
                execution.setContractId(contract.getContractId());
                execution.setCreateTime(DateTimeUtil.now());
                execution.setCreateBy("admin");
                execution.setBalance(balance);
                bizContractExecutionService.save(execution);
            }
        }
    }


    @Override
    public BigDecimal totalInterest(BizContractInfo contract) {
        if (contract.getFundAmount() == null || contract.getInterestRate() == null || contract.getLoanTerm() == null) {
            return BigDecimal.ZERO;
        }
        // 总利息 = 实付金额 × 月利率 × 借款期数
        BigDecimal interest = contract.getFundAmount().multiply(contract.getInterestRate()).multiply(BigDecimal.valueOf(contract.getLoanTerm()));
        return interest.setScale(2, RoundingMode.HALF_UP);
    }

    //计算滞纳金
    @Override
    public BigDecimal lateFee(BizContractInfo contract) {
        //查询上月有没有延迟收款的,如果是第一次且是豁免时间内,就不需要滞纳金,否则需要滞纳金
        BigDecimal lateFee = BigDecimal.ZERO;
        Boolean hasLastMonthDelayedOrders = collectionScheduleService.hasLastMonthDelayedOrders(contract);
        if (hasLastMonthDelayedOrders) {
            //计算滞纳金
            String sysPublicParamKeyToValue = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.LATE_PAYMENT_PENALTY_RATE);
            //每期还款本金
            BigDecimal principalAmount = contract.getFundAmount().divide(BigDecimal.valueOf(contract.getLoanTerm()).setScale(2, RoundingMode.HALF_UP));
            if (StringUtils.isNotBlank(sysPublicParamKeyToValue)) {
                lateFee = FinanceCalcUtil.calculateInterest(principalAmount, new BigDecimal(sysPublicParamKeyToValue));
            }
        }
        return lateFee;
    }
}
