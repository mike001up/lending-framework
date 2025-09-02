package com.pig4cloud.pig.flowable.config;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
@DS("flowable")  // Flowable 引擎专用 flowable 数据源
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration config) {
        config.setDatabaseSchemaUpdate("true"); // 自动建表
    }
}
