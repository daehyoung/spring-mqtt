package kr.luxsoft.iot.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */

@ConfigurationProperties(prefix = "mqtt.publish")
public class MqttPublishConfigProperties extends MqttConfigProperties{
}
