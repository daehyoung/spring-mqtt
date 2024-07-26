package kr.luxsoft.iot.mqtt.spring;

import java.util.Iterator;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
public class MemLog implements MemEventLog<EventLog> {

    CircularQueue<EventLog> queue;
    public MemLog() {
        queue = new CircularQueue<>(100);
    }

    @Override
    public void push(EventLog event) {
        queue.offer(event);
    }

    @Override
    public Iterator<EventLog> events() {
        return queue.iterator();
    }
}
