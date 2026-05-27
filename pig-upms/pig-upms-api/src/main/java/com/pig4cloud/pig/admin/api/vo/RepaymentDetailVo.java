package com.pig4cloud.pig.admin.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单期还款明细
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentDetailVo implements Serializable {
    /** 当前期数 */
    private int period;
    /** 当前本金 */
    private BigDecimal principal;
    /** 当前利息 */
    private BigDecimal interest;
    /** 当期应还总额 */
    private BigDecimal totalAmount;
    /** 还款后剩余本金 (收款后余额)*/
    private BigDecimal remainingPrincipal;
    // 还款前剩余本金（收款前余额）
    private BigDecimal remainingPrincipalBefore;
    // ✅ 滞纳金
    private BigDecimal lateFee;
    /** ✅ 每期最小还款金额（即仅还本金） */
    private BigDecimal minRepaymentAmount;
    /** ✅ 每期最大还款金额（本金 + 利息 + 滞纳金） */
    private BigDecimal maxRepaymentAmount;

}
