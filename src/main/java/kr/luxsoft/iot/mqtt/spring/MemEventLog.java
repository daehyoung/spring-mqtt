package kr.luxsoft.iot.mqtt.spring;

import java.util.Iterator;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-23
 * @version: 1.0
 */
public interface MemEventLog<T> {
    void push(T payload);
    Iterator<T> events();
}