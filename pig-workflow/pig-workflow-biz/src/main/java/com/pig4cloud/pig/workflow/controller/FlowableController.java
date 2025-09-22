package com.pig4cloud.pig.workflow.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.pig4cloud.pig.common.core.constant.MqConstant;
import com.pig4cloud.pig.common.core.service.mqtt.MqttPublisher;
import com.pig4cloud.pig.common.core.service.rocketmq.RocketMqUtils;
import com.pig4cloud.pig.common.core.util.MqttMessage;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping("/workflow")
@Tag(description = "workflow", name = "工作流测试")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class FlowableController {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RocketMqUtils rocketMqUtils;
    private final MqttPublisher mqttPublisher;


    @PostMapping("/deploy")
    public R<?> deploy(@RequestParam("file") MultipartFile file) throws IOException {
        repositoryService.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
        return R.ok();
    }

    @PostMapping("/start/{processKey}")
    public R<?> start(@PathVariable String processKey) {
        runtimeService.startProcessInstanceByKey(processKey);
        return R.ok();
    }

    @GetMapping("/tasks")
    public R<?> tasks() {
        List<Task> list = taskService.createTaskQuery().list();
        // 转换为更友好的格式
        List<Map<String, Object>> result = list.stream().map(task -> {
            Map<String, Object> taskInfo = new HashMap<>();
            taskInfo.put("id", task.getId());
            taskInfo.put("name", task.getName());
            taskInfo.put("assignee", task.getAssignee());
            taskInfo.put("createTime", task.getCreateTime());
            taskInfo.put("processInstanceId", task.getProcessInstanceId());
            return taskInfo;
        }).collect(Collectors.toList());
        return R.ok(result);
    }

    @PostMapping("/task/complete/{taskId}")
    public R<?> complete(@PathVariable String taskId) {
        taskService.complete(taskId);
        return R.ok();
    }

    @GetMapping("/test")
    public R<?> test() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("name", "pig");
        jsonObject.put("age", 20);
        jsonObject.put("test", "flowable");
        MqttMessage mqttMessage = new MqttMessage("1", "NOTICE", DateUtil.now(),  "你刚刚登录成功");
        String payload = JSONObject.toJSONString(mqttMessage);
        rocketMqUtils.sendMessage(MqConstant.FLOWABLE_CESHI, jsonObject.toJSONString());
        mqttPublisher.send("pig/pig-flowable", payload);
        return R.ok("测试成功 - " + new Date());
    }
}


