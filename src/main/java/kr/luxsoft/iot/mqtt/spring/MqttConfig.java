package kr.luxsoft.iot.mqtt.spring;

import kr.luxsoft.iot.mqtt.MqttConfigProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;


public class MqttConfig {


    public DefaultPahoMessageConverter mqttMessageConverter() {
        return new DefaultPahoMessageConverter();
    }


    public MqttConnectOptions mqttConnectOptions(MqttConfigProperties properties) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setKeepAliveInterval(properties.getKeepAliveInterval()); // 60초마다 ping
        options.setCleanSession(properties.getCleanSession());
        options.setServerURIs(new String[]{properties.getUrl()});
        options.setUserName(properties.getUsername());
        options.setPassword(properties.getPassword().toCharArray());
        return options;
    }

    public MqttPahoClientFactory mqttPahoClientFactory(MqttConfigProperties properties) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions(properties));
        return factory;
    }

}
