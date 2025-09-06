package com.pig4cloud.pig.common.core.service;

import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisher {

    private final MqttPahoMessageHandler mqttHandler;

    public MqttPublisher(MqttPahoMessageHandler mqttHandler) {
        this.mqttHandler = mqttHandler;
    }

    /**
     * 发送消息到指定 topic
     */
    public void send(String topic, String payload) {
        mqttHandler.handleMessage(
                MessageBuilder.withPayload(payload)
                        .setHeader("mqtt_topic", topic)
                        .build()
        );
    }


    /**
     * 广播消息到所有服务的默认 topic
     */
    public void broadcast(String payload) {
        send("pig/common", payload);
    }
}
