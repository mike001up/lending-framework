package com.pig4cloud.pig.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import com.pig4cloud.pig.common.mq.event.BaseMessage;

/**
 * 消息消费者抽象基类
 */
@Slf4j
public abstract class MessageConsumer<T> {

    /**
     * 消费消息的主入口
     */
    public void consume(Message<BaseMessage<T>> rawMessage) {
        BaseMessage<T> message = rawMessage.getPayload();
        String traceId = message.getTraceId();
        String eventType = message.getEventType();

        log.info("收到消息 [traceId: {}, eventType: {}]", traceId, eventType);

        try {
            if (isProcessed(message)) {
                log.warn("消息已处理过 [traceId: {}, eventType: {}], 跳过", traceId, eventType);
                return;
            }

            handleMessage(message);
            markProcessed(message);

        } catch (Exception e) {
            log.error("消息消费失败 [traceId: {}, eventType: {}]", traceId, eventType, e);
            throw e;
        }
    }

    /**
     * 子类实现具体业务逻辑
     */
    protected abstract void handleMessage(BaseMessage<T> message);

    /**
     * 幂等性检查（可覆盖，默认返回 false）
     */
    protected boolean isProcessed(BaseMessage<T> message) {
        return false;
    }

    /**
     * 标记已处理（可覆盖，默认空实现）
     */
    protected void markProcessed(BaseMessage<T> message) {
        // 可结合 Redis 实现
    }
}