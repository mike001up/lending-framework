package com.pig4cloud.pig.guaranty.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("slave2")
public interface BizWareHouseMapper extends BaseMapper<BizWareHouse> {

    IPage<BizWareHouse> getPage(Page page, @Param("query") BizWareHouse bizWareHouse);

    BizWareHouse getOneById(@Param("id") Long id);

    List<BizWareHouse> getListByIds(@Param("ids") List<Long> ids);
}
