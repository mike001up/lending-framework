package com.pig4cloud.pig.admin.cypher.concrete;

import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;
import com.pig4cloud.pig.admin.cypher.AbstractRepaymentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 等额本金（每期本金固定，利息递减）
 * 示例：
 * 第1期：利息 = 10000 * 0.05
 * 第2期：利息 = (10000 - 1000) * 0.05
 */
public class EqualPrincipalCalculator extends AbstractRepaymentCalculator {

    @Override
    public RepaymentDetailVo calculate(BigDecimal principal,
                                       BigDecimal periodRate,
                                       int totalPeriods,
                                       int currentPeriod,
                                       boolean hasPreviousLate,
                                       BigDecimal lateFeeRate) {

        // 每期本金
        BigDecimal principalPerPeriod = principal
                .divide(BigDecimal.valueOf(totalPeriods), 2, RoundingMode.CEILING);

        // 还款前剩余本金
        BigDecimal remainingPrincipalBefore = principal
                .subtract(principalPerPeriod.multiply(BigDecimal.valueOf(currentPeriod - 1)))
                .setScale(2, RoundingMode.CEILING);

        // 当前期利息
        BigDecimal interest = remainingPrincipalBefore.multiply(periodRate).setScale(2, RoundingMode.CEILING);

        // 本期应还总额（本金 + 利息）
        BigDecimal totalAmount = principalPerPeriod.add(interest).setScale(2, RoundingMode.CEILING);

        // 滞纳金计算
        BigDecimal lateFee = BigDecimal.ZERO;
        if (hasPreviousLate && lateFeeRate != null && lateFeeRate.compareTo(BigDecimal.ZERO) > 0) {
            lateFee = totalAmount.multiply(lateFeeRate).setScale(2, RoundingMode.CEILING);
        }

        // 还款后剩余本金
        BigDecimal remainingPrincipalAfter = principal
                .subtract(principalPerPeriod.multiply(BigDecimal.valueOf(currentPeriod)))
                .setScale(2, RoundingMode.CEILING);

        // ✅ 计算最小与最大还款金额
        BigDecimal minRepaymentAmount = principalPerPeriod.setScale(2, RoundingMode.CEILING);
        BigDecimal maxRepaymentAmount = totalAmount.add(lateFee).setScale(2, RoundingMode.CEILING);

        // 封装结果
        RepaymentDetailVo detail = new RepaymentDetailVo();
        detail.setPeriod(currentPeriod);
        detail.setPrincipal(principalPerPeriod);
        detail.setInterest(interest);
        detail.setTotalAmount(totalAmount);
        detail.setLateFee(lateFee);
        detail.setRemainingPrincipalBefore(remainingPrincipalBefore.max(BigDecimal.ZERO));
        detail.setRemainingPrincipal(remainingPrincipalAfter.max(BigDecimal.ZERO));

        // ✅ 设置最小/最大还款金额
        detail.setMinRepaymentAmount(minRepaymentAmount);
        detail.setMaxRepaymentAmount(maxRepaymentAmount);

        return detail;
    }

}
