package kr.luxsoft.iot.app;

import kr.luxsoft.iot.mqtt.PublishService;
import kr.luxsoft.iot.mqtt.paho.MqttClientPublisher;
import kr.luxsoft.iot.mqtt.spring.EventLog;
import kr.luxsoft.iot.mqtt.spring.MemEventLog;
import kr.luxsoft.iot.mqtt.spring.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Slf4j
@RestController
public class MqttController {

    @Autowired
    PublishService  service;

    @Autowired
    MemEventLog<EventLog> memLog;


    @PostMapping("pms")
    public void post(@RequestBody Map<String,Object> data) throws  Exception {
      log.info("{}",data);
      memLog.push(new EventLog(log.getName(), data,System.currentTimeMillis()));
      service.send(data);
    }



    @GetMapping("pms")
    public Object log(){
        return memLog.events();
    }

}
