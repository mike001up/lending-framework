package com.pig4cloud.pig.guaranty.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.guaranty.api.entity.DicCollateralType;
import com.pig4cloud.pig.guaranty.service.DicCollateralTypeService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 抵押物类型字典表
 *
 * @author pig
 * @date 2025-09-29 18:59:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dicCollateralType" )
@Tag(description = "dicCollateralType" , name = "抵押物类型字典表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DicCollateralTypeController {

    private final DicCollateralTypeService dicCollateralTypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dicCollateralType 抵押物类型字典表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_dicCollateralType_view")
    public R getDicCollateralTypePage(@ParameterObject Page page, @ParameterObject DicCollateralType dicCollateralType) {
        LambdaQueryWrapper<DicCollateralType> wrapper = Wrappers.lambdaQuery();
        return R.ok(dicCollateralTypeService.page(page, wrapper));
    }


    /**
     * 通过条件查询抵押物类型字典表
     * @param dicCollateralType 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_dicCollateralType_view")
    public R getDetails(@ParameterObject DicCollateralType dicCollateralType) {
        return R.ok(dicCollateralTypeService.list(Wrappers.query(dicCollateralType)));
    }

    /**
     * 新增抵押物类型字典表
     * @param dicCollateralType 抵押物类型字典表
     * @return R
     */
    @Operation(summary = "新增抵押物类型字典表" , description = "新增抵押物类型字典表" )
    @SysLog("新增抵押物类型字典表" )
    @PostMapping
    @HasPermission("admin_dicCollateralType_add")
    public R save(@RequestBody DicCollateralType dicCollateralType) {
        return R.ok(dicCollateralTypeService.save(dicCollateralType));
    }

    /**
     * 修改抵押物类型字典表
     * @param dicCollateralType 抵押物类型字典表
     * @return R
     */
    @Operation(summary = "修改抵押物类型字典表" , description = "修改抵押物类型字典表" )
    @SysLog("修改抵押物类型字典表" )
    @PutMapping
    @HasPermission("admin_dicCollateralType_edit")
    public R updateById(@RequestBody DicCollateralType dicCollateralType) {
        return R.ok(dicCollateralTypeService.updateById(dicCollateralType));
    }

    /**
     * 通过id删除抵押物类型字典表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除抵押物类型字典表" , description = "通过id删除抵押物类型字典表" )
    @SysLog("通过id删除抵押物类型字典表" )
    @DeleteMapping
    @HasPermission("admin_dicCollateralType_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(dicCollateralTypeService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param dicCollateralType 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_dicCollateralType_export")
    public List<DicCollateralType> exportExcel(DicCollateralType dicCollateralType, Long[] ids) {
        return dicCollateralTypeService.list(Wrappers.lambdaQuery(dicCollateralType).in(ArrayUtil.isNotEmpty(ids), DicCollateralType::getId, ids));
    }

    /**
     * 导入excel 表
     * @param dicCollateralTypeList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_dicCollateralType_export")
    public R importExcel(@RequestExcel List<DicCollateralType> dicCollateralTypeList, BindingResult bindingResult) {
        return R.ok(dicCollateralTypeService.saveBatch(dicCollateralTypeList));
    }
}
