package kr.luxsoft.iot.mqtt.spring;

import kr.luxsoft.iot.mqtt.PublishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author: Cho, Daehyoung
 * @contact: daehyoung.cho@gmail.com
 * @since: 2024-07-26
 * @version: 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class MqttService implements PublishService {

    final MessageChannel outboundChannel;
    private final GenericTransformer<Object, byte[]> jsonToByteArrayTransformer;


    @Override
    public void send( Object payload) throws Exception {
        log.info("{}",payload);
        byte[] transformedPayload = jsonToByteArrayTransformer.transform(payload);
        outboundChannel.send(new GenericMessage<>(transformedPayload));

    }
}
