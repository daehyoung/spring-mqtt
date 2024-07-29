package kr.luxsoft.iot.mqtt;

import lombok.Data;


/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Data
public class MqttConfigProperties {
    String url;
    String clientId;
    String topic;
    String username;
    String password;

    Integer qos =1;
    Integer timeout = 10000;

    Integer keepAliveInterval= 60;
    Boolean cleanSession = false;
}
