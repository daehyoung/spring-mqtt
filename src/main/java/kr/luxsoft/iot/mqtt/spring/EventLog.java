package kr.luxsoft.iot.mqtt.spring;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class EventLog {
    String layer;
    Object event;
    long timestamp;
}
