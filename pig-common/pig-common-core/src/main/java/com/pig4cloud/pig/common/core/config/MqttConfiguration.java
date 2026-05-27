package com.pig4cloud.pig.common.core.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

@Configuration
@ConditionalOnProperty(prefix = "spring.mqtt",name = "url")
public class MqttConfiguration {

    @Value("${spring.mqtt.url}")
    private String url;

    @Value("${spring.mqtt.client-id}")
    private String clientId;

    //@Value("${spring.mqtt.username}")
    //private String username;

    //@Value("${spring.mqtt.password}")
    //private String password;

    public static final String[] topics = {
            "pig/common",
            "pig/pig-upms-biz",
            "pig/pig-flowable",
            "pig/pig-auth",
    };


    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{url});
        //options.setUserName(username);
        //options.setPassword(password.toCharArray());
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MqttPahoMessageHandler mqttMessageHandler(MqttPahoClientFactory factory) {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(clientId, factory);
        handler.setAsync(true); // 异步发送
        return handler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        url,
                        clientId + "-inbound",
                        topics // 动态订阅 yml 配置的 topic 列表
                );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    // MQTT 入站通道适配器
    @Bean
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

}
