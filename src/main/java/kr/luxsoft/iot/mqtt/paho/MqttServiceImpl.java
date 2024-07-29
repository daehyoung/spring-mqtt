package kr.luxsoft.iot.mqtt.paho;

import kr.luxsoft.iot.mqtt.MqttConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-29
 * @version: 1.0
 */
@Slf4j
public class MqttServiceImpl  {

    MqttConfigProperties properties;

    public void connect(MqttConfigProperties properties){
        this.properties = properties;
    }

    public MqttClient client(MqttConfigProperties properties) throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setKeepAliveInterval(properties.getKeepAliveInterval()); // 60초마다 ping
        options.setCleanSession(properties.getCleanSession());
        options.setServerURIs(new String[]{properties.getUrl()});
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(properties.getTimeout());
        options.setUserName(properties.getUsername());
        options.setPassword(properties.getPassword().toCharArray());
        MqttClient client = new MqttClient(properties.getUrl(), properties.getClientId()+"-"+System.currentTimeMillis());
        client.connect(options);
        return client;
    }



}
