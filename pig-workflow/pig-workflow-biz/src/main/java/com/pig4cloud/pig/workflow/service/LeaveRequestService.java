package com.pig4cloud.pig.workflow.service;


import com.pig4cloud.pig.workflow.api.entity.LeaveRequest;
import com.pig4cloud.pig.workflow.mapper.LeaveRequestMapper;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestMapper leaveRequestMapper;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryService historyService;

    @Transactional
    public LeaveRequest submitLeave(LeaveRequest leaveRequest, String applyUser, String manager, String hr) {
        leaveRequest.setStatus("审批中");
        leaveRequestMapper.insert(leaveRequest);

        Map<String, Object> variables = new HashMap<>();
        variables.put("applyUser", applyUser);
        variables.put("manager", manager);
        variables.put("hr", hr);

        // 启动流程，并使用 businessKey 为业务表 id
        var pi = runtimeService.startProcessInstanceByKey("leaveProcess", String.valueOf(leaveRequest.getId()), variables);

        leaveRequest.setProcessInstanceId(pi.getId());
        leaveRequestMapper.updateById(leaveRequest);
        // ====== 新增部分：自动完成申请节点 ======
        Task applyTask = taskService.createTaskQuery().processInstanceId(pi.getId()).taskAssignee(applyUser).singleResult();

        if (applyTask != null) {
            // 如果流程定义里需要条件判断，比如 ${approved == true}
            variables.put("approved", true);
            taskService.complete(applyTask.getId(), variables);
        }
        // =====================================
        return leaveRequest;
    }


    @Transactional
    public List<Map<String, Object>> getTasks(String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();

        return tasks.stream().map(task -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("processInstanceId", task.getProcessInstanceId());
            map.put("createTime", task.getCreateTime());
            map.put("assignee", task.getAssignee());
            return map;
        }).toList();
    }

    @Transactional
    public void approveTask(String taskId, boolean approved) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("approved", approved);
        taskService.complete(taskId, vars);

        // 完成后可查询流程实例并更新业务表（示例：若流程结束则设置状态）
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            // 任务完成后若流程结束，需要通过历史或 runtime 查询对应业务
            // 这里略：可通过 processInstanceId -> businessKey 查找 leave_request
        }
    }


    /**
     * 经理审批
     */
    @Transactional
    public void managerApprove(String taskId, boolean approved, String hr) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);
        variables.put("hr", hr);
        taskService.complete(taskId, variables);
    }


    /**
     * HR 审批
     */
    @Transactional
    public void hrApprove(String taskId, boolean approved) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);
        taskService.complete(taskId, variables);
    }

    @Transactional
    public List<Map<String, Object>> getFinishedProcesses() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 查询已经完成的流程实例
        List<HistoricProcessInstance> instances = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("leaveProcess").finished().orderByProcessInstanceEndTime().desc().list();
        List<Map<String, Object>> result = new ArrayList<>();
        for (HistoricProcessInstance instance : instances) {
            Map<String, Object> map = new HashMap<>();
            map.put("processInstanceId", instance.getId());
            map.put("businessKey", instance.getBusinessKey());
            map.put("startTime", instance.getStartTime() != null ? sdf.format(instance.getStartTime()) : null);
            map.put("endTime", instance.getEndTime() != null ? sdf.format(instance.getEndTime()) : null);
            map.put("durationInMillis", instance.getDurationInMillis());
            // 查询该流程实例的历史任务
            List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(instance.getId()).orderByHistoricTaskInstanceEndTime().asc().list();
            List<Map<String, Object>> taskList = new ArrayList<>();
            for (HistoricTaskInstance task : tasks) {
                Map<String, Object> taskMap = new HashMap<>();
                taskMap.put("taskId", task.getId());
                taskMap.put("taskName", task.getName());
                taskMap.put("assignee", task.getAssignee());
                taskMap.put("startTime", task.getStartTime() != null ? sdf.format(task.getStartTime()) : null);
                taskMap.put("endTime", task.getEndTime() != null ? sdf.format(task.getEndTime()) : null);
                taskMap.put("durationInMillis", task.getDurationInMillis());
                // 获取每个任务节点的审批变量（如 approved）
                List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().processInstanceId(instance.getId()).taskId(task.getId()).list();
                Map<String, Object> varMap = new HashMap<>();
                for (HistoricVariableInstance var : variables) {
                    varMap.put(var.getVariableName(), var.getValue());
                }
                taskMap.put("variables", varMap);
                taskList.add(taskMap);
            }
            map.put("tasks", taskList);
            result.add(map);
        }
        return result;
    }
}
