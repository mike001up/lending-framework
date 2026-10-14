package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("sys_role_data_scope")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDataScope {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;
    private String businessTable;
    private String scopeType;
    private String ownerColumn;
    private String hierarchyType;
    private String customSql;

    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}