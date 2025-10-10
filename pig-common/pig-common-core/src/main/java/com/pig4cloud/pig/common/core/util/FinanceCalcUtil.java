package com.pig4cloud.pig.common.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanceCalcUtil {

    /**
     * 计算利息
     * @param amount     金额（本金/实付金额）
     * @param monthRate  利率（百分比，例如0.12表示12%）
     * @return           利息金额（保留2位小数，四舍五入）
     */
    public static BigDecimal calculateInterest(BigDecimal amount, BigDecimal monthRate) {
        if (amount == null || monthRate == null) {
            return BigDecimal.ZERO;
        }
        return amount.multiply(monthRate).setScale(2, RoundingMode.HALF_UP);
    }
}
