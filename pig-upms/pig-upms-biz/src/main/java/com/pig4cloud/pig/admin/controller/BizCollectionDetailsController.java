package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizCollectionDetails;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.BizCollectionDetailsService;

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
 * 收款详情表
 *
 * @author pig
 * @date 2025-09-29 18:48:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizCollectionDetails" )
@Tag(description = "bizCollectionDetails" , name = "收款详情表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizCollectionDetailsController {

    private final  BizCollectionDetailsService bizCollectionDetailsService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bizCollectionDetails 收款详情表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizCollectionDetails_view")
    public R getBizCollectionDetailsPage(@ParameterObject Page page, @ParameterObject BizCollectionDetails bizCollectionDetails) {
        LambdaQueryWrapper<BizCollectionDetails> wrapper = Wrappers.lambdaQuery();
        return R.ok(bizCollectionDetailsService.page(page, wrapper));
    }


    /**
     * 通过条件查询收款详情表
     * @param bizCollectionDetails 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizCollectionDetails_view")
    public R getDetails(@ParameterObject BizCollectionDetails bizCollectionDetails) {
        return R.ok(bizCollectionDetailsService.list(Wrappers.query(bizCollectionDetails)));
    }

    /**
     * 新增收款详情表
     * @param bizCollectionDetails 收款详情表
     * @return R
     */
    @Operation(summary = "新增收款详情表" , description = "新增收款详情表" )
    @SysLog("新增收款详情表" )
    @PostMapping
    @HasPermission("admin_bizCollectionDetails_add")
    public R save(@RequestBody BizCollectionDetails bizCollectionDetails) {
        return R.ok(bizCollectionDetailsService.save(bizCollectionDetails));
    }

    /**
     * 修改收款详情表
     * @param bizCollectionDetails 收款详情表
     * @return R
     */
    @Operation(summary = "修改收款详情表" , description = "修改收款详情表" )
    @SysLog("修改收款详情表" )
    @PutMapping
    @HasPermission("admin_bizCollectionDetails_edit")
    public R updateById(@RequestBody BizCollectionDetails bizCollectionDetails) {
        return R.ok(bizCollectionDetailsService.updateById(bizCollectionDetails));
    }

    /**
     * 通过id删除收款详情表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除收款详情表" , description = "通过id删除收款详情表" )
    @SysLog("通过id删除收款详情表" )
    @DeleteMapping
    @HasPermission("admin_bizCollectionDetails_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizCollectionDetailsService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizCollectionDetails 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizCollectionDetails_export")
    public List<BizCollectionDetails> exportExcel(BizCollectionDetails bizCollectionDetails, Long[] ids) {
        return bizCollectionDetailsService.list(Wrappers.lambdaQuery(bizCollectionDetails).in(ArrayUtil.isNotEmpty(ids), BizCollectionDetails::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizCollectionDetailsList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizCollectionDetails_export")
    public R importExcel(@RequestExcel List<BizCollectionDetails> bizCollectionDetailsList, BindingResult bindingResult) {
        return R.ok(bizCollectionDetailsService.saveBatch(bizCollectionDetailsList));
    }
}
