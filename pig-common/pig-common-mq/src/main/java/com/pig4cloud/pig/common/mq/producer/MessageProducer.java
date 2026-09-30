package com.pig4cloud.pig.common.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.pig4cloud.pig.common.mq.event.BaseMessage;

@Slf4j
@Component
public class MessageProducer {

    private final StreamBridge streamBridge;

    public MessageProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    /**
     * 发送消息到指定 Topic
     */
    public <T> void send(String topic, BaseMessage<T> message) {
        // 填充公共头信息
        message.setTraceId(MDC.get("traceId") != null ? MDC.get("traceId") : "unknown");
        message.setTimestamp(System.currentTimeMillis());

        // 构建 Spring Message
        MessageBuilder<BaseMessage<T>> builder = MessageBuilder.withPayload(message);

        // 设置 Tag（如果存在）
        if (message.getTag() != null && !message.getTag().isEmpty()) {
            builder.setHeader("rocketmq_TAGS", message.getTag());
        }
        // 设置 Key（如果存在）
        if (message.getKey() != null && !message.getKey().isEmpty()) {
            builder.setHeader("rocketmq_KEYS", message.getKey());
        }
        Message<BaseMessage<T>> msg = builder.build();
        log.debug("发送消息到 Topic: {}, 事件类型: {}", topic, message.getEventType());
        streamBridge.send(topic, msg);
    }
}