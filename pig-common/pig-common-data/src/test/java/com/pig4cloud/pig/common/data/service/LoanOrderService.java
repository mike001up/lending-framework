package com.pig4cloud.pig.common.data.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.data.entity.LoanOrder;
import com.pig4cloud.pig.common.data.query.LoanOrderQuery;

public interface LoanOrderService {
    Page<LoanOrder> page(LoanOrderQuery query);
}
