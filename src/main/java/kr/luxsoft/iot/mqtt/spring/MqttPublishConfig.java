package kr.luxsoft.iot.mqtt.spring;

import kr.luxsoft.iot.mqtt.MqttPublishConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Slf4j
@Configuration
@EnableConfigurationProperties(MqttPublishConfigProperties.class)
public class MqttPublishConfig extends MqttConfig {

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(MqttPublishConfigProperties properties) {
        MqttPahoClientFactory factory = super.mqttPahoClientFactory(properties);
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(properties.getClientId(), factory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(properties.getTopic());
        messageHandler.setConverter(super.mqttMessageConverter());
        return messageHandler;
    }

    @Bean
    MqttService mqttService(@Qualifier("mqttOutboundChannel") MessageChannel outboundChannel,
                            GenericTransformer<Object, byte[]> jsonToByteArrayTransformer ){
        return new MqttService(outboundChannel,jsonToByteArrayTransformer);
    }


    @Bean
    public GenericTransformer<Object, byte[]> jsonToByteArrayTransformer() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return source -> {
            try {
                return converter.getObjectMapper().writeValueAsBytes(source);
            } catch (Exception e) {
                throw new RuntimeException("Failed to convert JSON to byte array", e);
            }
        };
    }



}
