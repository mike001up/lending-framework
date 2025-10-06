package com.pig4cloud.pig.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysMerchant;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.service.SysRoleService;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.util.DateTimeUtil;
import com.pig4cloud.pig.common.core.util.MsgUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.pig.admin.service.SysMerchantService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 商户表
 *
 * @author pig
 * @date 2025-09-15 18:30:48
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysMerchant")
@Tag(description = "sysMerchant", name = "商户表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysMerchantController {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final SysMerchantService sysMerchantService;

    private final SysRoleService sysRoleService;

    private final SysUserService userService;

    /**
     * 分页查询
     *
     * @param page        分页对象
     * @param sysMerchant 商户表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    @HasPermission("admin_sysMerchant_view")
    public R getSysMerchantPage(@ParameterObject Page page, @ParameterObject SysMerchant sysMerchant) {
        LambdaQueryWrapper<SysMerchant> wrapper = Wrappers.lambdaQuery();
        return R.ok(sysMerchantService.page(page, wrapper));
    }


    /**
     * 通过条件查询商户表
     *
     * @param sysMerchant 查询条件
     * @return R  对象列表
     */
    @Operation(summary = "通过条件查询", description = "通过条件查询对象")
    @GetMapping("/details")
    @HasPermission("admin_sysMerchant_view")
    public R getDetails(@ParameterObject SysMerchant sysMerchant) {
        return R.ok(sysMerchantService.list(Wrappers.query(sysMerchant)));
    }


    /**
     * 新增商户表
     *
     * @param sysMerchant 商户表
     * @return R
     */
    @Operation(summary = "新增商户", description = "新增商户")
    @SysLog("新增商户")
    @PostMapping
    @HasPermission("admin_sysMerchant_add")
    @Transactional
    public R save(@RequestBody SysMerchant sysMerchant) {
        SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>query().lambda().eq(SysRole::getRoleCode, CommonConstants.MERCHANT));
        if (sysRole == null) {
            return R.failed(MsgUtils.getMessage("sys.merchant.not.exists"));
        }
        //维护sys_user表
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, sysMerchant.getPlatName()));
        if (user != null) {
            return R.failed(MsgUtils.getMessage("sys.user.username.existing"));
        }
        SysUser sysUser = new SysUser();
        sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
        sysUser.setCreateBy(SecurityUtils.getUser().getUsername());
        sysUser.setPassword(ENCODER.encode(sysMerchant.getPassword()));
        sysUser.setUsername(sysMerchant.getPlatName());
        sysUser.setUserType(CommonConstants.MERCHANT);
        userService.save(sysUser);
        //维护角色表
        List<Long> roles = new ArrayList<>();
        //查询角色id
        roles.add(sysRole.getRoleId());
        // 插入用户角色关系表
        sysRoleService.saveByRoleList(roles, sysUser);
        sysMerchant.setCreateTime(DateTimeUtil.now());
        return R.ok(sysMerchantService.save(sysMerchant));
    }

    /**
     * 修改商户表
     *
     * @param sysMerchant 商户表
     * @return R
     */
    @Operation(summary = "修改商户表", description = "修改商户表")
    @SysLog("修改商户表")
    @PutMapping
    @HasPermission("admin_sysMerchant_edit")
    @Transactional
    public R updateById(@RequestBody SysMerchant sysMerchant) {
        if (StringUtils.isNotBlank(sysMerchant.getPassword()) && StringUtils.isNotBlank(sysMerchant.getPlatName())) {
            SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, sysMerchant.getPlatName()));
            user.setPassword(ENCODER.encode(sysMerchant.getPassword()));
            userService.updateById(user);
        }
        return R.ok(sysMerchantService.updateById(sysMerchant));
    }

    /**
     * 通过id删除商户表
     *
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除商户表", description = "通过id删除商户表")
    @SysLog("通过id删除商户表")
    @DeleteMapping
    @HasPermission("admin_sysMerchant_del")
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(sysMerchantService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     *
     * @param sysMerchant 查询条件
     * @param ids         导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @HasPermission("admin_sysMerchant_export")
    public List<SysMerchant> exportExcel(SysMerchant sysMerchant, Long[] ids) {
        return sysMerchantService.list(Wrappers.lambdaQuery(sysMerchant).in(ArrayUtil.isNotEmpty(ids), SysMerchant::getId, ids));
    }

    /**
     * 导入excel 表
     *
     * @param sysMerchantList 对象实体列表
     * @param bindingResult   错误信息列表
     * @return ok fail
     */
    @PostMapping("/import")
    @HasPermission("admin_sysMerchant_export")
    public R importExcel(@RequestExcel List<SysMerchant> sysMerchantList, BindingResult bindingResult) {
        return R.ok(sysMerchantService.saveBatch(sysMerchantList));
    }
}
