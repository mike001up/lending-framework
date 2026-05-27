package com.pig4cloud.pig.admin.cypher;


import com.pig4cloud.pig.admin.api.vo.RepaymentDetailVo;
import com.pig4cloud.pig.admin.cypher.concrete.EqualPrincipalCalculator;
import com.pig4cloud.pig.common.core.constant.enums.RepaymentType;

import java.math.BigDecimal;

/**
 * 还款计算器工厂类
 */
public class RepaymentCalculatorFactory {

    /**
     * 根据还款类型创建不同的计算器实例
     */
    public static AbstractRepaymentCalculator getCalculator(RepaymentType type) {
        switch (type) {
            //等额本金
            case EQUAL_PRINCIPAL:
                // 等额本金
                return new EqualPrincipalCalculator();
            default:
                return new EqualPrincipalCalculator();
        }
    }

    public static void main(String[] args) {
        AbstractRepaymentCalculator calculator = new EqualPrincipalCalculator();
        RepaymentDetailVo detail = calculator.calculate(
                new BigDecimal("10000"),
                new BigDecimal("0.05"),
                10,
                1,
                true,
                new BigDecimal("0.02")
        );

        System.out.println(detail);

    }


}
