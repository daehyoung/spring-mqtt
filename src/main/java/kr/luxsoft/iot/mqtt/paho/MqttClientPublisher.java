package kr.luxsoft.iot.mqtt.paho;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
// MqttPublisher.java
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.luxsoft.iot.mqtt.PublishService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@RequiredArgsConstructor
public class MqttClientPublisher implements PublishService {

    final MqttClient client;
    final String topic;
    ObjectMapper mapper =new ObjectMapper();


    @Override
    public void send(Object payload) throws  Exception  {
        MqttMessage message = new MqttMessage(mapper.writeValueAsBytes(payload));
        client.publish(topic, message);
    }

}