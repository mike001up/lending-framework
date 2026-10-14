package com.pig4cloud.pig.common.data.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.data.annotation.DataScope;
import com.pig4cloud.pig.common.data.entity.LoanOrder;
import com.pig4cloud.pig.common.data.query.LoanOrderQuery;
import com.pig4cloud.pig.common.data.service.LoanOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loan/order")
@RequiredArgsConstructor
public class LoanOrderController {

    private final LoanOrderService loanOrderService;

    @GetMapping("/list")
    @DataScope(tableAlias = "o")
    @PreAuthorize("@pms.hasPermission('loan_order_view')")
    public R<Page<LoanOrder>> list(LoanOrderQuery query) {
        return R.ok(loanOrderService.page(query));
    }
}
