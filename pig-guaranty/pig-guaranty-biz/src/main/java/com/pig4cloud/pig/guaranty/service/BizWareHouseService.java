package com.pig4cloud.pig.guaranty.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;

import java.util.List;

public interface BizWareHouseService extends IService<BizWareHouse> {

    IPage<BizWareHouse> getPage(Page page, BizWareHouse bizWareHouse);

    BizWareHouse getOneById(Long id);

    List<BizWareHouse> getListByIds(List<Long> ids);
}
