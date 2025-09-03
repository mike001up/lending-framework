package com.pig4cloud.pig.flowable.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@DS("flowable")
public class FlowableService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public String startProcess(String userId) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("applyUser", userId);
        vars.put("manager", "admin");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("leaveProcess", vars);
        return instance.getId();
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }
}
