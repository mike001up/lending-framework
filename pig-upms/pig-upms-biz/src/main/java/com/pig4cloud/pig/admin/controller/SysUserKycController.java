package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysUserKyc;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.SysUserKycService;

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
 * 用户KYC信息表
 *
 * @author pig
 * @date 2025-09-17 14:26:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysUserKyc" )
@Tag(description = "sysUserKyc" , name = "用户KYC信息表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysUserKycController {

    private final  SysUserKycService sysUserKycService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysUserKyc 用户KYC信息表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_sysUserKyc_view")
    public R getSysUserKycPage(@ParameterObject Page page, @ParameterObject SysUserKyc sysUserKyc) {
        LambdaQueryWrapper<SysUserKyc> wrapper = Wrappers.lambdaQuery();
        return R.ok(sysUserKycService.page(page, wrapper));
    }


    /**
     * 通过条件查询用户KYC信息表
     * @param sysUserKyc 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_sysUserKyc_view")
    public R getDetails(@ParameterObject SysUserKyc sysUserKyc) {
        return R.ok(sysUserKycService.list(Wrappers.query(sysUserKyc)));
    }

    /**
     * 新增用户KYC信息表
     * @param sysUserKyc 用户KYC信息表
     * @return R
     */
    @Operation(summary = "新增用户KYC信息" , description = "新增用户KYC信息" )
    @SysLog("新增用户KYC信息" )
    @PostMapping
    @HasPermission("admin_sysUserKyc_add")
    public R save(@RequestBody SysUserKyc sysUserKyc) {
        sysUserKyc.setCreateTime(DateTimeUtil.now());
        sysUserKyc.setCreateBy(SecurityUtils.getUser().getUsername());
        SysUserKyc kyc = sysUserKycService.getOne(Wrappers.<SysUserKyc>lambdaQuery().eq(SysUserKyc::getUserId, sysUserKyc.getUserId()));
        if (kyc != null) {
            return R.failed(MsgUtils.getMessage("sys.kyc.exists"));
        }
        return R.ok(sysUserKycService.save(sysUserKyc));
    }

    /**
     * 修改用户KYC信息表
     * @param sysUserKyc 用户KYC信息表
     * @return R
     */
    @Operation(summary = "修改用户KYC信息" , description = "修改用户KYC信息" )
    @SysLog("修改用户KYC信息" )
    @PutMapping
    @HasPermission("admin_sysUserKyc_edit")
    public R updateById(@RequestBody SysUserKyc sysUserKyc) {
        sysUserKyc.setUpdateBy(SecurityUtils.getUser().getUsername());
        sysUserKyc.setUpdateTime(DateTimeUtil.now());
        SysUserKyc kyc = sysUserKycService.getById(sysUserKyc.getId());
        if (kyc != null && !sysUserKyc.getUserId().equals(kyc.getUserId())) {
            return R.failed(MsgUtils.getMessage("sys.kyc.id.no.update"));
        }
        return R.ok(sysUserKycService.updateById(sysUserKyc));
    }

    /**
     * 通过id删除用户KYC信息表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除用户KYC信息" , description = "通过id删除用户KYC信息" )
    @SysLog("通过id删除用户KYC信息" )
    @DeleteMapping
    @HasPermission("admin_sysUserKyc_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(sysUserKycService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param sysUserKyc 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_sysUserKyc_export")
    public List<SysUserKyc> exportExcel(SysUserKyc sysUserKyc, Long[] ids) {
        return sysUserKycService.list(Wrappers.lambdaQuery(sysUserKyc).in(ArrayUtil.isNotEmpty(ids), SysUserKyc::getId, ids));
    }

    /**
     * 导入excel 表
     * @param sysUserKycList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_sysUserKyc_export")
    public R importExcel(@RequestExcel List<SysUserKyc> sysUserKycList, BindingResult bindingResult) {
        return R.ok(sysUserKycService.saveBatch(sysUserKycList));
    }
}
