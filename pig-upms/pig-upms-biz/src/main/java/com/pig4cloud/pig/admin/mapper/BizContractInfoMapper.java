package com.pig4cloud.pig.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.api.vo.BizContractInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BizContractInfoMapper extends BaseMapper<BizContractInfo> {

    IPage<BizContractInfoVo> getPage(Page page, @Param("query") BizContractInfo bizContractInfo);
}
