package kr.luxsoft.iot.mqtt.paho;

import kr.luxsoft.iot.mqtt.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.function.Consumer;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Slf4j
public class MqttClientSubscriber implements SubscriberService, MqttCallback{
    MqttClient client;
    String topic;
    Consumer<byte[]> handler;


    public MqttClientSubscriber(MqttClient client, String topic, Consumer<byte[]> handler) throws MqttException {
        this.client = client;
        this.topic = topic;
        this.handler = handler;
    }

    public void subscribe() throws MqttException {
        client.setCallback(this);
        client.subscribe(topic);
    }

    @Override
    public void connectionLost(Throwable t) {
        log.error("{}",t.getMessage());
        if(log.isDebugEnabled()){
            t.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message)  {
        handler.accept(message.getPayload());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
