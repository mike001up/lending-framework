package com.pig4cloud.pig.workflow.controller;


import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.workflow.api.entity.LeaveRequest;
import com.pig4cloud.pig.workflow.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveService;

    @PostMapping("/submit")
    @Operation(summary = "提交请假申请", description = "提交请假申请")
    public R submit(@RequestBody LeaveRequest request,
                    @RequestParam String applyUser,
                    @RequestParam String manager,
                    @RequestParam String hr) {
        return R.ok(leaveService.submitLeave(request, applyUser, manager, hr));
    }

    @GetMapping("/tasks")
    @Operation(summary = "查看待办任务", description = "查看待办任务")
    public R tasks(@RequestParam String assignee) {
        return R.ok(leaveService.getTasks(assignee));
    }

    @PostMapping("/approve")
    @Operation(summary = "经理审批", description = "经理审批")
    public R approve(@RequestParam String taskId, @RequestParam boolean approved) {
        leaveService.approveTask(taskId, approved);
        return R.ok("ok");
    }


    @PostMapping("/manager/approve")
    @Operation(summary = "经理审批", description = "经理审批")
    public R managerApprove(@RequestParam String taskId,
                            @RequestParam boolean approved,
                            @RequestParam String hr) {
        leaveService.managerApprove(taskId, approved, hr);
        return R.ok(approved ? "经理审批通过" : "经理审批驳回");
    }

    @PostMapping("/hr/approve")
    @Operation(summary = "HR 审批", description = "HR 审批")
    public R hrApprove(@RequestParam String taskId,
                       @RequestParam boolean approved) {
        leaveService.hrApprove(taskId, approved);
        return R.ok(approved ? "人事审批通过，流程结束" : "人事审批驳回，流程结束");
    }



    @GetMapping("/finished")
    @Operation(summary = "查询所有已完成的请假流程", description = "查询所有已完成的请假流程")
    public R getFinishedProcesses() {
        return R.ok(leaveService.getFinishedProcesses());
    }
}
