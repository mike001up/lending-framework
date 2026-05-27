package com.pig4cloud.pig.workflow.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 请假申请表
 *
 * @author pig
 * @date 2025-10-15 11:39:53
 */
@Data
@TableName("leave_request")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "请假申请表")
public class LeaveRequest extends Model<LeaveRequest> {


    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="主键ID")
    private Long id;

    /**
     * 申请人ID
     */
    @Schema(description="申请人ID")
    private Long userId;

    /**
     * 请假开始时间
     */
    @Schema(description="请假开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 请假结束时间
     */
    @Schema(description="请假结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 请假原因
     */
    @Schema(description="请假原因")
    private String reason;

    /**
     * 请假状态（待审批、已通过、已驳回）
     */
    @Schema(description="请假状态（待审批、已通过、已驳回）")
    private String status;

    /**
     * 流程实例ID（与Flowable关联）
     */
    @Schema(description="流程实例ID（与Flowable关联）")
    private String processInstanceId;

    /**
     * createTime
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
