package kr.luxsoft.iot.mqtt.paho;

import kr.luxsoft.iot.mqtt.MqttPublishConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Slf4j
@Configuration
public class PublisherConfig extends MqttPahoConfig {

    @Bean
    MqttClientPublisher MqttClientPublisher(MqttPublishConfigProperties properties) throws MqttException {
        log.info("{}",properties);
        return new MqttClientPublisher(client(properties), properties.getTopic());
    }

}
