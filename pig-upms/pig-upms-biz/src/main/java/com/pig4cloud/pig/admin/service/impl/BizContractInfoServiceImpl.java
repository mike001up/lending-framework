package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.*;
import com.pig4cloud.pig.admin.api.vo.BizContractInfoVo;
import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;
import com.pig4cloud.pig.admin.cypher.AbstractRepaymentCalculator;
import com.pig4cloud.pig.admin.cypher.RepaymentCalculatorFactory;
import com.pig4cloud.pig.admin.mapper.BizContractInfoMapper;
import com.pig4cloud.pig.admin.service.*;
import com.pig4cloud.pig.common.core.constant.BusinessConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.enums.RepaymentType;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
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
    @Autowired
    private BizCollectionDetailsService bizCollectionDetailsService;

    @Override
    @Transactional
    public void generateCollectionSchedules() {
        // 查询未结束的合同
        List<BizContractInfo> contractList = this.lambdaQuery().eq(BizContractInfo::getIsRunning, BusinessConstants.CONTRACT_STATUS_RUNNING).list();

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

            // 收款开始日
            int periodStartDay = contract.getPeriodStartDate() == null || contract.getPeriodStartDate() <= 0 ? 1 : contract.getPeriodStartDate();
            periodStartDay = Math.min(periodStartDay, today.lengthOfMonth()); // 防止超过当月天数
            LocalDate startDate = today.withDayOfMonth(periodStartDay);

            //比较现在是不是大于等于收款开始时间,如果不大于等于那么不生成账单,只有到了日期才生成账单
            if (!DateTimeUtil.greaterOrEqual(today, startDate)) {
                continue;
            }
            //生成还款明细数据
            RepaymentDetailVo detail = getDetailVo(contract);
            // 收款结束日 = 开始日 + period_days
            // 获取合同天数，处理 null 或 <=0 的情况
            int periodDays = contract.getPeriodDays() == null || contract.getPeriodDays() <= 0 ? 1 : contract.getPeriodDays();
            // 结束日期，开始日算作第 1 天，所以减 1
            LocalDate endDate = startDate.plusDays(periodDays - 1);
            // 构建催款计划
            BizCollectionSchedule schedule = new BizCollectionSchedule();
            schedule.setContractId(contract.getContractId());
            schedule.setUserId(contract.getUserId());
            schedule.setUserName(contract.getUserName());
            schedule.setRealName(contract.getRealName());
            schedule.setBalance(detail.getRemainingPrincipalBefore());
            schedule.setMinAmount(detail.getMinRepaymentAmount());
            schedule.setMaxAmount(detail.getMaxRepaymentAmount());
            schedule.setColStartDate(DateTimeUtil.startOfDay(startDate));
            schedule.setColEndDate(DateTimeUtil.endOfDay(endDate));
            schedule.setStatus(0); // 待收款
            schedule.setLateFee(detail.getLateFee());
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
                execution.setBalance(detail.getRemainingPrincipalBefore());
                bizContractExecutionService.save(execution);
            }
        }
    }


    //使用缓存版
    @Override
    public RepaymentDetailVo getDetailVo(BizContractInfo contract) {
        Boolean hasPreviousLate = collectionScheduleService.hasLastMonthDelayedOrders(contract);
        //滞纳金利率
        String sysPublicParamKeyToValue = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.LATE_PAYMENT_PENALTY_RATE);
        BigDecimal lateFeeRate = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(sysPublicParamKeyToValue)) {
            lateFeeRate = new BigDecimal(sysPublicParamKeyToValue);
        }
        // 从工厂类获取对应计算器
        Long successCount = bizCollectionDetailsService.count(Wrappers.<BizCollectionDetails>lambdaQuery().eq(BizCollectionDetails::getContractId, contract.getContractId()));
        AbstractRepaymentCalculator calculator = RepaymentCalculatorFactory.getCalculator(RepaymentType.fromType(contract.getRepaymentType()));
        Integer currentPeriod = Math.toIntExact(successCount + 1);
        RepaymentDetailVo detail = calculator.calculate(contract.getFundAmount(), contract.getInterestRate(), contract.getLoanTerm(), currentPeriod, hasPreviousLate, lateFeeRate);
        saveRepaymentDetailToRedis(detail, contract.getContractId(), currentPeriod);
        return detail;
    }

    //不使用缓存版
    @Override
    public RepaymentDetailVo getRepaymentDetailVo(BizContractInfo contract) {
        //查询上次有没有延迟还款
        Boolean hasPreviousLate = collectionScheduleService.hasLastMonthDelayedOrders(contract);
        //滞纳金利率
        String sysPublicParamKeyToValue = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.LATE_PAYMENT_PENALTY_RATE);
        BigDecimal lateFeeRate = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(sysPublicParamKeyToValue)) {
            lateFeeRate = new BigDecimal(sysPublicParamKeyToValue);
        }
        // 从工厂类获取对应计算器
        Long successCount = bizCollectionDetailsService.count(Wrappers.<BizCollectionDetails>lambdaQuery().eq(BizCollectionDetails::getContractId, contract.getContractId()));
        AbstractRepaymentCalculator calculator = RepaymentCalculatorFactory.getCalculator(RepaymentType.fromType(contract.getRepaymentType()));
        Integer currentPeriod = Math.toIntExact(successCount + 1);
        RepaymentDetailVo detail = calculator.calculate(contract.getFundAmount(), contract.getInterestRate(), contract.getLoanTerm(), currentPeriod, hasPreviousLate, lateFeeRate);
        return detail;
    }

    /**
     * 保存还款详情到 Redis
     *
     * @param detail     还款详情对象
     * @param contractNo 合同号
     * @param period     第几期
     */
    @Override
    public void saveRepaymentDetailToRedis(RepaymentDetailVo detail, Long contractNo, int period) {
        // 构建 key，例如 contractNo_期数
        String key = contractNo + "_" + period;
        // 保存到 Redis，设置过期时间 60 天（单位秒）
        RedisUtils.set(key, detail, 60 * 24 * 60 * 60L);
    }

    /**
     * 从 Redis 获取还款详情
     */
    @Override
    public RepaymentDetailVo getRepaymentDetailFromRedis(Long contractNo, int period) {
        String key = contractNo + "_" + period;
        Object obj = RedisUtils.get(key);
        if (obj instanceof RepaymentDetailVo) {
            return (RepaymentDetailVo) obj;
        }
        return null;
    }

    @Override
    public IPage<BizContractInfoVo> getPage(Page page, BizContractInfo bizContractInfo) {
        return this.baseMapper.getPage(page, bizContractInfo);
    }


}
