package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizContractCollateral;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.BizContractCollateralService;

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
 * 合同抵押物关联表
 *
 * @author pig
 * @date 2025-09-29 13:19:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizContractCollateral" )
@Tag(description = "bizContractCollateral" , name = "合同抵押物关联表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizContractCollateralController {

    private final  BizContractCollateralService bizContractCollateralService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bizContractCollateral 合同抵押物关联表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizContractCollateral_view")
    public R getBizContractCollateralPage(@ParameterObject Page page, @ParameterObject BizContractCollateral bizContractCollateral) {
        LambdaQueryWrapper<BizContractCollateral> wrapper = Wrappers.lambdaQuery();
        return R.ok(bizContractCollateralService.page(page, wrapper));
    }


    /**
     * 通过条件查询合同抵押物关联表
     * @param bizContractCollateral 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizContractCollateral_view")
    public R getDetails(@ParameterObject BizContractCollateral bizContractCollateral) {
        return R.ok(bizContractCollateralService.list(Wrappers.query(bizContractCollateral)));
    }

    /**
     * 新增合同抵押物关联表
     * @param bizContractCollateral 合同抵押物关联表
     * @return R
     */
    @Operation(summary = "新增合同抵押物关联表" , description = "新增合同抵押物关联表" )
    @SysLog("新增合同抵押物关联表" )
    @PostMapping
    @HasPermission("admin_bizContractCollateral_add")
    public R save(@RequestBody BizContractCollateral bizContractCollateral) {
        return R.ok(bizContractCollateralService.save(bizContractCollateral));
    }

    /**
     * 修改合同抵押物关联表
     * @param bizContractCollateral 合同抵押物关联表
     * @return R
     */
    @Operation(summary = "修改合同抵押物关联表" , description = "修改合同抵押物关联表" )
    @SysLog("修改合同抵押物关联表" )
    @PutMapping
    @HasPermission("admin_bizContractCollateral_edit")
    public R updateById(@RequestBody BizContractCollateral bizContractCollateral) {
        return R.ok(bizContractCollateralService.updateById(bizContractCollateral));
    }

    /**
     * 通过id删除合同抵押物关联表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除合同抵押物关联表" , description = "通过id删除合同抵押物关联表" )
    @SysLog("通过id删除合同抵押物关联表" )
    @DeleteMapping
    @HasPermission("admin_bizContractCollateral_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizContractCollateralService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizContractCollateral 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizContractCollateral_export")
    public List<BizContractCollateral> exportExcel(BizContractCollateral bizContractCollateral, Long[] ids) {
        return bizContractCollateralService.list(Wrappers.lambdaQuery(bizContractCollateral).in(ArrayUtil.isNotEmpty(ids), BizContractCollateral::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizContractCollateralList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizContractCollateral_export")
    public R importExcel(@RequestExcel List<BizContractCollateral> bizContractCollateralList, BindingResult bindingResult) {
        return R.ok(bizContractCollateralService.saveBatch(bizContractCollateralList));
    }
}
