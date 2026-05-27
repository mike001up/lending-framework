package com.pig4cloud.pig.common.core.service.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.apache.rocketmq.common.message.Message;
import java.nio.charset.StandardCharsets;


@Component
@ConditionalOnBean(RocketMQTemplate.class)
public class RocketMqUtils {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通消息
     */
    @Async
    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }


    /**
     * 发送带 tag 的消息
     */
    @Async
    public void sendMessageWithTag(String topic, String tag, String message) {
        rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
    }


    /**
     * 发送顺序消息
     */
    @Async
    public void sendOrderedMessage(String topic, String payload, String hashKey) {
        rocketMQTemplate.syncSendOrderly(topic, MessageBuilder.withPayload(payload).build(), hashKey);
    }


    /**
     * 发送延迟消息
     * @param topic   主题
     * @param msg     消息内容
     * @param delayLevel 延迟等级 (1-18)
     *  时间   1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     *  等级   1  2  3  4   5  6  7  8  9  10  11 12 13 14  15 16  17 18
     */
    @Async
    public void sendDelayMessage(String topic, String msg, int delayLevel) {
        try {
            Message message = new Message(
                    topic,
                    msg.getBytes(StandardCharsets.UTF_8)
            );
            // 设置延迟等级
            message.setDelayTimeLevel(delayLevel);
            SendResult sendResult = rocketMQTemplate.getProducer().send(message);
            System.out.println("发送延迟消息结果: " + sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
