package com.pig4cloud.pig.common.core.service.mqtt;

import com.pig4cloud.pig.common.core.config.MqttConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean({MqttConfiguration.class})
public class MqttSubscriber {

    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public void handleMessage(String payload) {
        System.out.println("接收到 MQTT 消息: " + payload);
        // 可在这里分发到服务内部事件系统
    }
}
