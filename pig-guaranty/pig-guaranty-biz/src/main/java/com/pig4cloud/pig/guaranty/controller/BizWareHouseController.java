package com.pig4cloud.pig.guaranty.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.guaranty.api.entity.BizWareHouse;
import com.pig4cloud.pig.guaranty.service.BizWareHouseService;
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
 * 抵押物库存信息
 *
 * @author pig
 * @date 2025-09-29 18:53:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizWareHouse" )
@Tag(description = "bizWareHouse" , name = "抵押物库存信息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizWareHouseController {

    private final BizWareHouseService bizWareHouseService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param bizWareHouse 抵押物库存信息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizWareHouse_view")
    public R getBizWareHousePage(@ParameterObject Page page, @ParameterObject BizWareHouse bizWareHouse) {
        IPage<BizWareHouse> bizWareHousePage = bizWareHouseService.getPage(page, bizWareHouse);
        return R.ok(bizWareHousePage);
    }


    /**
     * 通过条件查询抵押物库存信息
     * @param bizWareHouse 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizWareHouse_view")
    public R getDetails(@ParameterObject BizWareHouse bizWareHouse) {
        return R.ok(bizWareHouseService.list(Wrappers.query(bizWareHouse)));
    }

    /**
     * 新增抵押物库存信息
     * @param bizWareHouse 抵押物库存信息
     * @return R
     */
    @Operation(summary = "新增抵押物库存信息" , description = "新增抵押物库存信息" )
    @SysLog("新增抵押物库存信息" )
    @PostMapping
    @HasPermission("admin_bizWareHouse_add")
    public R save(@RequestBody BizWareHouse bizWareHouse) {
        bizWareHouse.setOperatorId(SecurityUtils.getUser().getId());
        bizWareHouse.setUserName(SecurityUtils.getUser().getUsername());
        bizWareHouse.setCreateTime(DateTimeUtil.now());
        return R.ok(bizWareHouseService.save(bizWareHouse));
    }

    /**
     * 修改抵押物库存信息
     * @param bizWareHouse 抵押物库存信息
     * @return R
     */
    @Operation(summary = "修改抵押物库存信息" , description = "修改抵押物库存信息" )
    @SysLog("修改抵押物库存信息" )
    @PutMapping
    @HasPermission("admin_bizWareHouse_edit")
    public R updateById(@RequestBody BizWareHouse bizWareHouse) {
        bizWareHouse.setUpdateTime(DateTimeUtil.now());
        bizWareHouse.setUpdateUserId(SecurityUtils.getUser().getId());
        bizWareHouse.setUpdateUserName(SecurityUtils.getUser().getUsername());
        return R.ok(bizWareHouseService.updateById(bizWareHouse));
    }

    /**
     * 通过id删除抵押物库存信息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除抵押物库存信息" , description = "通过id删除抵押物库存信息" )
    @SysLog("通过id删除抵押物库存信息" )
    @DeleteMapping
    @HasPermission("admin_bizWareHouse_del")
    @DS("slave2")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizWareHouseService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizWareHouse 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizWareHouse_export")
    public List<BizWareHouse> exportExcel(BizWareHouse bizWareHouse, Long[] ids) {
        return bizWareHouseService.list(Wrappers.lambdaQuery(bizWareHouse).in(ArrayUtil.isNotEmpty(ids), BizWareHouse::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizWareHouseList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizWareHouse_export")
    public R importExcel(@RequestExcel List<BizWareHouse> bizWareHouseList, BindingResult bindingResult) {
        return R.ok(bizWareHouseService.saveBatch(bizWareHouseList));
    }

    @Operation(summary = "根据ID获取抵押物信息", description = "根据ID获取抵押物信息")
    @GetMapping("/getWareHouseById")
    @Inner
    public BizWareHouse getWareHouseById(Long id) {
        BizWareHouse bizWareHouse = bizWareHouseService.getOneById(id);
        return bizWareHouse;
    }

    @Operation(summary = "批量查询仓库信息", description = "批量查询仓库信息")
    @PostMapping("/listByIds")
    @Inner
    public List<BizWareHouse> listByIds(@RequestBody List<Long> ids) {
        List<BizWareHouse> bizWareHouses = bizWareHouseService.getListByIds(ids);
        return bizWareHouses;
    }
}
