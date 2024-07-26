package kr.luxsoft.iot.app;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeProfiler {
    @Around("execution(*  kr.luxsoft.iot.mqtt.PublishService.send(..))")
    public Object profileToPublishService(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object output = pjp.proceed();
        long elapsedTime = System.nanoTime() - start;
        log.info("execution time for {}: {} µs", pjp.getSignature().getName(), elapsedTime / 1000);
        return output;
    }
    @Around("execution(*  kr.luxsoft.iot.mqtt.SubscriberService.messageArrived(..))")
    public Object profileToSubscriberService(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object output = pjp.proceed();
        long elapsedTime = System.nanoTime() - start;
        log.info("execution time for {}: {} µs", pjp.getSignature().getName(), elapsedTime / 1000);
        return output;
    }


}