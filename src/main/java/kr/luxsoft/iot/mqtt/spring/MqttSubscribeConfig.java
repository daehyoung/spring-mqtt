package kr.luxsoft.iot.mqtt.spring;

import kr.luxsoft.iot.mqtt.MqttSubscribeConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;


@Slf4j
@Configuration
@EnableConfigurationProperties(MqttSubscribeConfigProperties.class)
public class MqttSubscribeConfig extends MqttConfig {


    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageProducer inbound( MqttSubscribeConfigProperties properties) {
        MqttPahoClientFactory factory = super.mqttPahoClientFactory(properties);

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        properties.getClientId()
                        , factory
                        , properties.getTopic());
        adapter.setCompletionTimeout(properties.getTimeout());
        adapter.setConverter( super.mqttMessageConverter());
        adapter.setQos(properties.getQos());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                log.info("{}",message.getPayload());
            }

        };
    }

}
