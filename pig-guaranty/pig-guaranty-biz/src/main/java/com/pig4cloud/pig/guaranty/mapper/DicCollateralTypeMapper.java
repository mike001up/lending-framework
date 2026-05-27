package com.pig4cloud.pig.guaranty.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.guaranty.api.entity.DicCollateralType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("slave2")
public interface DicCollateralTypeMapper extends BaseMapper<DicCollateralType> {

}
