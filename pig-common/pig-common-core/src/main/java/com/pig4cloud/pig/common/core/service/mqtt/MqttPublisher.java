package com.pig4cloud.pig.common.core.service.mqtt;

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

    /**
     * 1. retain = true（保留消息）
     * Broker 会 保存这条消息为该 topic 的最新消息。
     * 新订阅者一连接就能立即收到这条消息，而不需要等待新的发布。
     * 常用场景：状态消息（例如用户余额、设备开关状态、最新价格）。
     * 特点：
     * Broker 内只保存 每个 topic 的最后一条消息。
     * 同一 topic 的新消息会覆盖旧消息。
     * 2. retain = false（普通消息）
     * Broker 不保存消息，只推送给当时在线的订阅者。
     * 离线的订阅者不会收到这条消息。
     * 常用场景：事件消息（例如交易账单、通知、日志）。
     */
    public void sendRetain(String topic, String payload, Boolean retain) {
        mqttHandler.handleMessage(
                MessageBuilder.withPayload(payload)
                        .setHeader("mqtt_topic", topic)
                        .setHeader("mqtt_retain", retain)
                        .build()
        );
    }
}
