package com.pig4cloud.pig.flowable.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class FlowableService {

    @DS("flowable")
    public void deployProcess() {
        // Flowable API
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("processes/demo.bpmn20.xml")
                .deploy();
    }
}
