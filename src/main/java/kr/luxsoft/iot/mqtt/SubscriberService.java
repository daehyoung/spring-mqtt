package kr.luxsoft.iot.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
public interface SubscriberService {
    void messageArrived(String topic, MqttMessage message);
}