package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizIpLimit;
import com.pig4cloud.pig.admin.service.BizIpLimitService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台IP白名单
 *
 * @author pig
 * @date 2025-08-28 16:57:13
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizIpLimit")
@Tag(description = "bizIpLimit", name = "后台IP白名单管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizIpLimitController {

    private final BizIpLimitService bizIpLimitService;

    /**
     * 分页查询
     *
     * @param page       分页对象
     * @param bizIpLimit 后台IP白名单
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    @HasPermission("admin_bizIpLimit_view")
    public R getBizIpLimitPage(@ParameterObject Page page, @ParameterObject BizIpLimit bizIpLimit) {
        LambdaQueryWrapper<BizIpLimit> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(bizIpLimit.getIp())) {
            wrapper.eq(BizIpLimit::getIp, bizIpLimit.getIp());
        }
        return R.ok(bizIpLimitService.page(page, wrapper));
    }


    /**
     * 通过条件查询后台IP白名单
     *
     * @param bizIpLimit 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询", description = "通过条件查询对象")
    @GetMapping("/details")
    @HasPermission("admin_bizIpLimit_view")
    public R getDetails(@ParameterObject BizIpLimit bizIpLimit) {
        return R.ok(bizIpLimitService.list(Wrappers.query(bizIpLimit)));
    }

    /**
     * 新增后台IP白名单
     *
     * @param bizIpLimit 后台IP白名单
     * @return R
     */
    @Operation(summary = "新增后台IP白名单", description = "新增后台IP白名单")
    @SysLog("新增后台IP白名单")
    @PostMapping
    @HasPermission("admin_bizIpLimit_add")
    public R save(@RequestBody BizIpLimit bizIpLimit) {
        // 先根据 IP 判断数据库中是否存在
        long count = bizIpLimitService.lambdaQuery()
                .eq(BizIpLimit::getIp, bizIpLimit.getIp())
                .count();
        if (count > 0)  return R.ok();
        return R.ok(bizIpLimitService.save(bizIpLimit));
    }

    /**
     * 修改后台IP白名单
     *
     * @param bizIpLimit 后台IP白名单
     * @return R
     */
    @Operation(summary = "修改后台IP白名单", description = "修改后台IP白名单")
    @SysLog("修改后台IP白名单")
    @PutMapping
    @HasPermission("admin_bizIpLimit_edit")
    public R updateById(@RequestBody BizIpLimit bizIpLimit) {
        // 校验是否存在相同 IP，排除自己这条记录
        long count = bizIpLimitService.lambdaQuery()
                .eq(BizIpLimit::getIp, bizIpLimit.getIp())
                .ne(BizIpLimit::getId, bizIpLimit.getId())
                .count();
        if (count > 0) return R.ok();
        return R.ok(bizIpLimitService.updateById(bizIpLimit));
    }

    /**
     * 通过id删除后台IP白名单
     *
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除后台IP白名单", description = "通过id删除后台IP白名单")
    @SysLog("通过id删除后台IP白名单")
    @DeleteMapping
    @HasPermission("admin_bizIpLimit_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizIpLimitService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     *
     * @param bizIpLimit 查询条件
     * @param ids        导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizIpLimit_export")
    public List<BizIpLimit> exportExcel(BizIpLimit bizIpLimit, Long[] ids) {
        return bizIpLimitService.list(Wrappers.lambdaQuery(bizIpLimit).in(ArrayUtil.isNotEmpty(ids), BizIpLimit::getId, ids));
    }

    /**
     * 导入excel 表
     *
     * @param bizIpLimitList 对象实体列表
     * @param bindingResult  错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizIpLimit_export")
    public R importExcel(@RequestExcel List<BizIpLimit> bizIpLimitList, BindingResult bindingResult) {
        return R.ok(bizIpLimitService.saveBatch(bizIpLimitList));
    }

    @Inner
    @GetMapping("/isValidIP")
    public Boolean isValidIP(@RequestParam("remoteIP") String remoteIP) {
        if (StringUtils.isBlank(remoteIP)) {
            return false;
        }
        List<BizIpLimit> list = bizIpLimitService.list();
        if (CollUtil.isEmpty(list)) {
            return true;
        }
        return bizIpLimitService.isMatch(remoteIP, list);
    }
}
