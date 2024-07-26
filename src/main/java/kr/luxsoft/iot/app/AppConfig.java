package kr.luxsoft.iot.app;

import kr.luxsoft.iot.mqtt.MqttPublishConfigProperties;
import kr.luxsoft.iot.mqtt.MqttSubscribeConfigProperties;
import kr.luxsoft.iot.mqtt.paho.PublisherConfig;
import kr.luxsoft.iot.mqtt.paho.SubscriberConfig;
import kr.luxsoft.iot.mqtt.spring.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Slf4j
@EnableConfigurationProperties({MqttPublishConfigProperties.class, MqttSubscribeConfigProperties.class})
@Import({ SubscriberConfig.class, PublisherConfig.class})
@Configuration
public class AppConfig {
    @Bean
    MemEventLog<EventLog> memLog(){
        return new MemLog();
    }

}
