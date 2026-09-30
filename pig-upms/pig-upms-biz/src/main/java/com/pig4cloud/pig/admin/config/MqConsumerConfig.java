package com.pig4cloud.pig.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pig4cloud.pig.admin.mq.LoginFailureService;
import com.pig4cloud.pig.common.mq.event.LoginFailureMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.Message;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class MqConsumerConfig {
    private final LoginFailureService loginFailureService;

    @Bean
    public Consumer<Message<LoginFailureMessage>> loginFailureConsumer() {
        return message -> {
            LoginFailureMessage payload = message.getPayload();
            loginFailureService.handleLoginFailure(payload);
        };
    }
}
