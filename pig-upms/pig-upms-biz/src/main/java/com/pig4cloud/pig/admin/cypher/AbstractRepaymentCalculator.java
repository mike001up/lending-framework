package com.pig4cloud.pig.admin.cypher;

import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;

import java.math.BigDecimal;

/**
 * 还款计划抽象类
 * 所有还款方式继承此类并实现 calculateRepaymentPlan 方法
 */
public abstract class AbstractRepaymentCalculator {

    /**
     * 计算某一期的还款详情（必须传入滞纳金上下文）
     *
     * @param principal       总本金
     * @param periodRate      每期利率（注意：此处是每期利率，不是年利率）
     * @param totalPeriods    总期数
     * @param currentPeriod   当前期（从1开始）
     * @param hasPreviousLate 上一期是否逾期（用于计算本期滞纳金）
     * @param lateFeeRate     滞纳金率（例如 0.02 表示 2%）
     * @return RepaymentDetailVo
     */
    public abstract RepaymentDetailVo calculate(BigDecimal principal,
                                                BigDecimal periodRate,
                                                int totalPeriods,
                                                int currentPeriod,
                                                boolean hasPreviousLate,
                                                BigDecimal lateFeeRate);
}


