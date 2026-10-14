import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.common.data.entity.LoanOrder;
import com.pig4cloud.pig.common.data.mapper.LoanOrderMapper;
import com.pig4cloud.pig.common.data.query.LoanOrderQuery;
import com.pig4cloud.pig.common.data.service.LoanOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanOrderServiceImpl extends ServiceImpl<LoanOrderMapper, LoanOrder>
        implements LoanOrderService {

    @Override
    public Page<LoanOrder> page(LoanOrderQuery query) {
        // 业务代码完全不涉及数据权限逻辑
        return this.page(new Page<>(query.getCurrent(), query.getSize()),
                Wrappers.<LoanOrder>lambdaQuery()
                    .eq(LoanOrder::getStatus, query.getStatus()));
    }

}
