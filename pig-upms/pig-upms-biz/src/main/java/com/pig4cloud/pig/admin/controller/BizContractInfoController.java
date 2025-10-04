package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.IdGenerator;
import com.pig4cloud.pig.common.core.util.QueryWrapperBuilder;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.BizContractInfoService;

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
 * 合同表
 *
 * @author pig
 * @date 2025-09-29 12:58:48
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizContractInfo" )
@Tag(description = "bizContractInfo" , name = "合同管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizContractInfoController {

    private final  BizContractInfoService bizContractInfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bizContractInfo 合同表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizContractInfo_view")
    public R getBizContractInfoPage(@ParameterObject Page page, @ParameterObject BizContractInfo bizContractInfo) {
        String[] likeFields = {"userName", "realName"};
        QueryWrapper<BizContractInfo> wrapper = QueryWrapperBuilder.build(bizContractInfo, likeFields, null);
        wrapper.orderByDesc("create_time");
        //  TODO 关联用户信息,kyc信息,抵押物信息
        return R.ok(bizContractInfoService.page(page, wrapper));
    }


    /**
     * 通过条件查询合同表
     * @param bizContractInfo 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizContractInfo_view")
    public R getDetails(@ParameterObject BizContractInfo bizContractInfo) {
        return R.ok(bizContractInfoService.list(Wrappers.query(bizContractInfo)));
    }

    /**
     * 新增合同表
     * @param bizContractInfo 合同表
     * @return R
     */
    @Operation(summary = "新增合同" , description = "新增合同" )
    @SysLog("新增合同" )
    @PostMapping
    @HasPermission("admin_bizContractInfo_add")
    public R save(@RequestBody BizContractInfo bizContractInfo) {
        bizContractInfo.setContractId(Long.valueOf(IdGenerator.nextId()));
        bizContractInfo.setCreateTime(DateTimeUtil.now());
        bizContractInfo.setCreateBy(SecurityUtils.getUser().getUsername());
        return R.ok(bizContractInfoService.save(bizContractInfo));
    }

    /**
     * 修改合同表
     * @param bizContractInfo 合同表
     * @return R
     */
    @Operation(summary = "修改合同" , description = "修改合同" )
    @SysLog("修改合同" )
    @PutMapping
    @HasPermission("admin_bizContractInfo_edit")
    public R updateById(@RequestBody BizContractInfo bizContractInfo) {
        bizContractInfo.setUpdateTime(DateTimeUtil.now());
        bizContractInfo.setUpdateBy(SecurityUtils.getUser().getUsername());
        return R.ok(bizContractInfoService.updateById(bizContractInfo));
    }

    /**
     * 通过id删除合同表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除合同表" , description = "通过id删除合同表" )
    @SysLog("通过id删除合同表" )
    @DeleteMapping
    @HasPermission("admin_bizContractInfo_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizContractInfoService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizContractInfo 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizContractInfo_export")
    public List<BizContractInfo> exportExcel(BizContractInfo bizContractInfo, Long[] ids) {
        return bizContractInfoService.list(Wrappers.lambdaQuery(bizContractInfo).in(ArrayUtil.isNotEmpty(ids), BizContractInfo::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizContractInfoList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizContractInfo_export")
    public R importExcel(@RequestExcel List<BizContractInfo> bizContractInfoList, BindingResult bindingResult) {
        return R.ok(bizContractInfoService.saveBatch(bizContractInfoList));
    }
}
