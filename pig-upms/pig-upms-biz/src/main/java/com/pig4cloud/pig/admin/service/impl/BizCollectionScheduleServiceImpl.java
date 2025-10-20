package com.pig4cloud.pig.admin.service.impl;


import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.*;
import com.pig4cloud.pig.admin.api.feign.RemoteCollectInfoService;
import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;
import com.pig4cloud.pig.admin.mapper.BizCollectionScheduleMapper;
import com.pig4cloud.pig.admin.service.*;
import com.pig4cloud.pig.common.core.constant.BusinessConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

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
    @Autowired
    private RemoteCollectInfoService remoteCollectInfoService;

    /**
     * 判断合同上个月是否存在逾期还款（含首月免滞纳规则）
     *
     * @param contract 合同信息
     * @return true 表示上个月存在逾期
     */
    @Override
    public Boolean hasLastMonthDelayedOrders(BizContractInfo contract) {
        if (contract == null || contract.getContractId() == null) {
            return false;
        }
        // 获取最新一期还款计划
        BizCollectionSchedule lastSchedule = this.lambdaQuery().eq(BizCollectionSchedule::getContractId, contract.getContractId()).orderByDesc(BizCollectionSchedule::getColEndDate).last("LIMIT 1").one();
        // 如果没有还款计划，直接返回 false
        if (lastSchedule == null) {
            return false;
        }
        // 计算该合同一共生成几期还款计划
        Long totalCount = this.count(Wrappers.<BizCollectionSchedule>lambdaQuery().eq(BizCollectionSchedule::getContractId, contract.getContractId()));
        // 如果最新一期状态是逾期
        boolean isDelayed = BusinessConstants.PAYMENT_STATUS_DELAY.equals(lastSchedule.getStatus());
        // === 1️⃣ 首月免滞纳逻辑 ===
        if (totalCount == 1 && isDelayed) {
            // 获取系统配置的免滞纳天数（默认 0）
            String exemptionDaysStr = sysPublicParamService.getSysPublicParamKeyToValue(SecurityConstants.EXEMPTION_DAYS);
            int exemptionDays = NumberUtil.parseInt(exemptionDaysStr, 0);
            // 滞纳金起算日
            Integer startLateFeeDate = contract.getStartLateFeeDate();
            if (startLateFeeDate == null) {
                startLateFeeDate = 0;
            }
            // 滞纳金免息截止日期
            int endLateFeeDate = startLateFeeDate + exemptionDays;
            // 上个月最后一天的“日”数
            int lastDayOfPrevMonth = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
            // 若上月最后一天超过免息截止日 → 逾期生效
            return lastDayOfPrevMonth > endLateFeeDate;
        }
        //=== 2️⃣ 非首月逻辑 ===
        //若状态为逾期
        if (isDelayed) {
            return true;
        }
        return false;
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
    @GlobalTransactional(rollbackFor = Exception.class)
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
            //获取缓存
            Long successCount = bizCollectionDetailsService.count(Wrappers.<BizCollectionDetails>lambdaQuery().eq(BizCollectionDetails::getContractId, info.getContractId()));
            Integer currentPeriod = Math.toIntExact(successCount + 1);
            RepaymentDetailVo detail = remoteCollectInfoService.detailByIssue(info.getContractId(), currentPeriod);
            if (detail == null) {
                detail = remoteCollectInfoService.detail(info);
            }
            BizCollectionDetails bizCollectionDetails = new BizCollectionDetails();
            bizCollectionDetails.setContractId(collectionScheduleServiceById.getContractId());
            bizCollectionDetails.setRepaymentPeriod(DateTimeUtil.now());
            bizCollectionDetails.setPreBalance(detail.getRemainingPrincipalBefore());
            BigDecimal postBalance = detail.getRemainingPrincipal();
            bizCollectionDetails.setPostBalance(postBalance);
            bizCollectionDetails.setRemainsPrincipal(detail.getPrincipal());
            bizCollectionDetails.setLateFee(detail.getLateFee());
            bizCollectionDetails.setPeriodInterest(detail.getInterest());
            bizCollectionDetails.setRemainsInterest(detail.getInterest());
            bizCollectionDetails.setCollectedPrincipal(detail.getPrincipal());
            bizCollectionDetails.setCollectedInterest(detail.getInterest());
            bizCollectionDetails.setCreateTime(DateTimeUtil.now());
            bizCollectionDetails.setCreateBy(SecurityUtils.getUser().getUsername());
            bizCollectionDetailsService.save(bizCollectionDetails);
            BizContractExecution contractExecution = bizContractExecutionService.getOne(Wrappers.<BizContractExecution>lambdaQuery().eq(BizContractExecution::getContractId, bizCollectionSchedule.getContractId()));
            if (contractExecution != null) {
                //还款余额
                BigDecimal balance = contractExecution.getBalance();
                BigDecimal principal = detail.getPrincipal();
                balance = balance.subtract(principal);
                contractExecution.setBalance(balance);
                //已还
                BigDecimal repaidMoney = info.getFundAmount().subtract(balance);
                BigDecimal repaymentProgress = repaidMoney.divide(info.getFundAmount(), 3, RoundingMode.HALF_UP);
                contractExecution.setRepaymentProgress(repaymentProgress);
                bizContractExecutionService.updateById(contractExecution);
                //如果还款进度达到1,那么合同状态改为完成
                if (repaymentProgress.compareTo(BigDecimal.ONE) >= 0) {
                    remoteCollectInfoService.finish(info);
                }
            }
        }
        return R.ok(this.updateById(bizCollectionSchedule));
    }

}
