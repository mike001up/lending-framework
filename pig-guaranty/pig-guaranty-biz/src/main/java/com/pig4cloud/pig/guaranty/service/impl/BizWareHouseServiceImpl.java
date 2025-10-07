package com.pig4cloud.pig.guaranty.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;
import com.pig4cloud.pig.guaranty.mapper.BizWareHouseMapper;
import com.pig4cloud.pig.guaranty.service.BizWareHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抵押物库存信息
 *
 * @author pig
 * @date 2025-09-29 18:53:34
 */
@Service
public class BizWareHouseServiceImpl extends ServiceImpl<BizWareHouseMapper, BizWareHouse> implements BizWareHouseService {

    @Override
    public IPage<BizWareHouse> getPage(Page page, BizWareHouse bizWareHouse) {
        return this.baseMapper.getPage(page, bizWareHouse);
    }

    @Override
    public BizWareHouse getOneById(Long id) {
        return this.baseMapper.getOneById(id);
    }

    @Override
    public List<BizWareHouse> getListByIds(List<Long> ids) {
        return this.baseMapper.getListByIds(ids);
    }
}
