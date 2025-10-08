package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import com.pig4cloud.pig.admin.api.entity.BizContractInfo;
import com.pig4cloud.pig.admin.api.vo.BizCollectionScheduleVo;
import com.pig4cloud.pig.admin.service.BizContractInfoService;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.QueryWrapperBuilder;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.BizCollectionScheduleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import org.apache.commons.beanutils.BeanUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 催款计划信息表
 *
 * @author pig
 * @date 2025-09-29 13:25:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bizCollectionSchedule" )
@Tag(description = "bizCollectionSchedule" , name = "催款计划信息表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BizCollectionScheduleController {

    private final  BizCollectionScheduleService bizCollectionScheduleService;
    private final  BizContractInfoService bizContractInfoService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param bizCollectionSchedule 催款计划信息表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @HasPermission("admin_bizCollectionSchedule_view")
    public R getBizCollectionSchedulePage(@ParameterObject Page page, @ParameterObject BizCollectionScheduleVo bizCollectionSchedule) {
        String[] likeFields = {"userName"};
        String[] rangeFields = {"createTimeStart", "createTimeEnd"};
        QueryWrapper<BizCollectionSchedule> wrapper = QueryWrapperBuilder.build(bizCollectionSchedule, likeFields, rangeFields);
        wrapper.orderByDesc("create_time");
        return R.ok(bizCollectionScheduleService.page(page, wrapper));
    }


    /**
     * 通过条件查询催款计划信息表
     * @param bizCollectionSchedule 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询" , description = "通过条件查询对象" )
    @GetMapping("/details" )
    @HasPermission("admin_bizCollectionSchedule_view")
    public R getDetails(@ParameterObject BizCollectionSchedule bizCollectionSchedule) {
        return R.ok(bizCollectionScheduleService.list(Wrappers.query(bizCollectionSchedule)));
    }

    /**
     * 新增催款计划信息表
     * @param bizCollectionSchedule 催款计划信息表
     * @return R
     */
    @Operation(summary = "新增催款计划信息表" , description = "新增催款计划信息表" )
    @SysLog("新增催款计划信息表" )
    @PostMapping
    @HasPermission("admin_bizCollectionSchedule_add")
    public R save(@RequestBody BizCollectionSchedule bizCollectionSchedule) {
        return R.ok(bizCollectionScheduleService.save(bizCollectionSchedule));
    }

    /**
     * 修改催款计划信息表
     * @param bizCollectionSchedule 催款计划信息表
     * @return R
     */
    @Operation(summary = "修改催款计划信息表" , description = "修改催款计划信息表" )
    @SysLog("修改催款计划信息表" )
    @PutMapping
    @HasPermission("admin_bizCollectionSchedule_edit")
    public R updateById(@RequestBody BizCollectionSchedule bizCollectionSchedule) {
        bizCollectionSchedule.setUpdateTime(DateTimeUtil.now());
        bizCollectionSchedule.setUpdateBy(SecurityUtils.getUser().getUsername());
        BizCollectionSchedule collectionScheduleServiceById = bizCollectionScheduleService.getById(bizCollectionSchedule.getId());
        //这个接口不允许修改审核状态
        if (collectionScheduleServiceById != null  && !bizCollectionSchedule.getApproveStatus().equals(collectionScheduleServiceById.getApproveStatus())) {
            return R.failed(MsgUtils.getMessage("sys.not.allowedchanged.status"));
        }
        return R.ok(bizCollectionScheduleService.updateById(bizCollectionSchedule));
    }

    /**
     * 通过id删除催款计划信息表
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除催款计划信息表" , description = "通过id删除催款计划信息表" )
    @SysLog("通过id删除催款计划信息表" )
    @DeleteMapping
    @HasPermission("admin_bizCollectionSchedule_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(bizCollectionScheduleService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param bizCollectionSchedule 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_bizCollectionSchedule_export")
    public List<BizCollectionSchedule> exportExcel(BizCollectionSchedule bizCollectionSchedule, Long[] ids) {
        return bizCollectionScheduleService.list(Wrappers.lambdaQuery(bizCollectionSchedule).in(ArrayUtil.isNotEmpty(ids), BizCollectionSchedule::getId, ids));
    }

    /**
     * 导入excel 表
     * @param bizCollectionScheduleList 对象实体列表
     * @param bindingResult 错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_bizCollectionSchedule_export")
    public R importExcel(@RequestExcel List<BizCollectionSchedule> bizCollectionScheduleList, BindingResult bindingResult) {
        return R.ok(bizCollectionScheduleService.saveBatch(bizCollectionScheduleList));
    }

    @Operation(summary = "审核" , description = "审核" )
    @SysLog("审核" )
    @PutMapping("/audit")
    @HasPermission("admin_bizCollectionSchedule_audit")
    public R audit(@RequestBody BizCollectionSchedule bizCollectionSchedule) {
        bizCollectionSchedule.setUpdateTime(DateTimeUtil.now());
        bizCollectionSchedule.setUpdateBy(SecurityUtils.getUser().getUsername());
        bizCollectionSchedule.setApproveUserId(SecurityUtils.getUser().getId());
        bizCollectionSchedule.setApproveTime(DateTimeUtil.now());
        bizCollectionSchedule.setApproveUserName(SecurityUtils.getUser().getUsername());
        BizContractInfo info = bizContractInfoService.getOne(Wrappers.<BizContractInfo>lambdaQuery().eq(BizContractInfo::getContractId, bizCollectionSchedule.getContractId()));
        return bizCollectionScheduleService.audit(bizCollectionSchedule, info);
    }
}
