package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizContractCollateral;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.api.entity.SysUserKyc;
import com.pig4cloud.pig.admin.api.vo.BizContractInfoVo;
import com.pig4cloud.pig.admin.service.BizContractCollateralService;
import com.pig4cloud.pig.admin.service.SysUserKycService;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.IdGenerator;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;
import com.pig4cloud.pig.guaranty.api.feign.RemoteWareHouseService;
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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final SysUserKycService sysUserKycService;
    private final BizContractCollateralService bizContractCollateralService;
    private final RemoteWareHouseService remoteWareHouseService;

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
        IPage<BizContractInfoVo> bizContractInfoPage = bizContractInfoService.getPage(page, bizContractInfo);
        List<Long> userIdList = bizContractInfoPage.getRecords().stream()
                .map(BizContractInfoVo::getUserId)
                .distinct()
                .collect(Collectors.toList());
        //根据userId 查询 kyc信息
        List<SysUserKyc> kycList = sysUserKycService.list(
                Wrappers.<SysUserKyc>lambdaQuery()
                        .in(SysUserKyc::getUserId, userIdList)
        );
        Map<Long, SysUserKyc> kycMap = kycList.stream()
                .collect(Collectors.toMap(SysUserKyc::getUserId, Function.identity(), (a, b) -> a));
        bizContractInfoPage.getRecords().forEach(contract -> {
            SysUserKyc kyc = kycMap.get(contract.getUserId());
            if (kyc != null) {
                contract.setSysUserKyc(kyc); // 你的 VO 可以新增字段接收
            }
        });
        //组装抵押物
        List<Long> contractIdList = bizContractInfoPage.getRecords().stream()
                .map(BizContractInfoVo::getContractId)
                .distinct()
                .collect(Collectors.toList());

        // 批量查抵押物
        List<BizContractCollateral> collaterals = bizContractCollateralService.list(
                Wrappers.<BizContractCollateral>lambdaQuery().in(BizContractCollateral::getContractId, contractIdList)
        );

        // 收集仓库ID
        List<Long> wareHouseIds = collaterals.stream()
                .map(BizContractCollateral::getCollateralId)
                .distinct()
                .collect(Collectors.toList());

        // 远程批量查仓库
        List<BizWareHouse> wareHouses = Optional.ofNullable(remoteWareHouseService.listByIds(wareHouseIds))
                .orElse(Collections.emptyList());

        // 建立 ID -> 仓库 map
        Map<Long, BizWareHouse> wareHouseMap = wareHouses.stream()
                .collect(Collectors.toMap(BizWareHouse::getId, Function.identity(), (a, b) -> a));

        // 回填仓库信息
        bizContractInfoPage.getRecords().forEach(contract -> {
            List<BizContractCollateral> contractCollaterals = collaterals.stream()
                    .filter(c -> c.getContractId().equals(contract.getContractId()))
                    .toList();

            List<BizWareHouse> contractWareHouses = contractCollaterals.stream()
                    .map(c -> wareHouseMap.get(c.getCollateralId()))
                    .filter(Objects::nonNull)
                    .toList();

            contract.setHouseList(contractWareHouses);
        });
        return R.ok(bizContractInfoPage);
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
