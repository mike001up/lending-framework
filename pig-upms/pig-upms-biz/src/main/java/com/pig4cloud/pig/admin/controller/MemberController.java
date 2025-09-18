package com.pig4cloud.pig.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
@Tag(description = "member", name = "会员用户管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class MemberController {

    private final SysUserService memberService;

    /**
     * 分页查询会员
     */
    @Operation(summary = "分页查询会员", description = "分页查询会员")
    @GetMapping("/page")
    @HasPermission("member:user:list")
    public R getMemberPage(Page<SysUser> page, SysUser user) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUserType, CommonConstants.FRONTEND)
                .like(StringUtils.isNotBlank(user.getUsername()), SysUser::getUsername, user.getUsername())
                .eq(user.getLockFlag() != null, SysUser::getLockFlag, user.getLockFlag())
                .eq(user.getDelFlag() != null, SysUser::getDelFlag, user.getDelFlag());
        return R.ok(memberService.page(page, wrapper));
    }

    /**
     * 新增会员
     */
    @Operation(summary = "新增会员", description = "新增会员")
    @SysLog("新增会员")
    @PostMapping
    @HasPermission("member:user:add")
    public R save(@RequestBody SysUser user) {
        user.setUserType(CommonConstants.FRONTEND);
        return R.ok(memberService.save(user));
    }

    /**
     * 修改会员
     */
    @Operation(summary = "修改会员", description = "修改会员")
    @SysLog("修改会员")
    @PutMapping
    @HasPermission("member:user:edit")
    public R updateById(@RequestBody SysUser user) {
        return R.ok(memberService.updateById(user));
    }

    /**
     * 删除会员
     */
    @Operation(summary = "删除会员", description = "删除会员")
    @SysLog("删除会员")
    @DeleteMapping("/{id}")
    @HasPermission("member:user:del")
    public R removeById(@PathVariable Long id) {
        return R.ok(memberService.removeById(id));
    }

}
