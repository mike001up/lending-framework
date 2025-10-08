package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizContractExecution;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.BizContractExecutionService;

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
 * 合同执行情况表
 *
 * @author pig
 * @date 2025-09-29 13:14:01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizContractExecution" )
@Tag(description = "bizContractExecution" , name = "合同执行情况管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizContractExecutionController {

    private final  BizContractExecutionService bizContractExecutionService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bizContractExecution 合同执行情况表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizContractExecution_view")
    public R getBizContractExecutionPage(@ParameterObject Page page, @ParameterObject BizContractExecution bizContractExecution) {
        LambdaQueryWrapper<BizContractExecution> wrapper = Wrappers.lambdaQuery();
        return R.ok(bizContractExecutionService.page(page, wrapper));
    }


    /**
     * 通过条件查询合同执行情况表
     * @param bizContractExecution 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizContractExecution_view")
    public R getDetails(@ParameterObject BizContractExecution bizContractExecution) {
        return R.ok(bizContractExecutionService.list(Wrappers.query(bizContractExecution)));
    }

    /**
     * 新增合同执行情况表
     * @param bizContractExecution 合同执行情况表
     * @return R
     */
    @Operation(summary = "新增合同执行情况" , description = "新增合同执行情况" )
    @SysLog("新增合同执行情况" )
    @PostMapping
    @HasPermission("admin_bizContractExecution_add")
    public R save(@RequestBody BizContractExecution bizContractExecution) {
        return R.ok(bizContractExecutionService.save(bizContractExecution));
    }

    /**
     * 修改合同执行情况表
     * @param bizContractExecution 合同执行情况表
     * @return R
     */
    @Operation(summary = "修改合同执行情况" , description = "修改合同执行情况" )
    @SysLog("修改合同执行情况" )
    @PutMapping
    @HasPermission("admin_bizContractExecution_edit")
    public R updateById(@RequestBody BizContractExecution bizContractExecution) {
        return R.ok(bizContractExecutionService.updateById(bizContractExecution));
    }

    /**
     * 通过id删除合同执行情况表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除合同执行情况" , description = "通过id删除合同执行情况" )
    @SysLog("通过id删除合同执行情况" )
    @DeleteMapping
    @HasPermission("admin_bizContractExecution_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizContractExecutionService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizContractExecution 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizContractExecution_export")
    public List<BizContractExecution> exportExcel(BizContractExecution bizContractExecution, Long[] ids) {
        return bizContractExecutionService.list(Wrappers.lambdaQuery(bizContractExecution).in(ArrayUtil.isNotEmpty(ids), BizContractExecution::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizContractExecutionList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizContractExecution_export")
    public R importExcel(@RequestExcel List<BizContractExecution> bizContractExecutionList, BindingResult bindingResult) {
        return R.ok(bizContractExecutionService.saveBatch(bizContractExecutionList));
    }
}
