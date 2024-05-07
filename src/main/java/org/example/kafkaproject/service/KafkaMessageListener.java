package org.example.kafkaproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KafkaMessageListener {


    @Async(value = "asyncExecutor")
    @KafkaListener(topics = "newTopic", groupId = "gary-group")
    public void consume(String message, Acknowledgment ack) throws InterruptedException {
        log.info("----------------------------------------------");
        ack.acknowledge();
        TimeUnit.SECONDS.sleep(2);
        log.info("Received message: {}", message);
        log.info("Thread: {}", Thread.currentThread().getName());
    }

//    @Async(value = "asyncExecutor")
//    @KafkaListener(topics = "newTopic", groupId = "gary-group")
//    public void consume(String message) throws InterruptedException {
//        log.info("----------------------------------------------");
//        TimeUnit.SECONDS.sleep(1);
//        log.info("Received message: {}", message);
//        log.info("Thread: {}", Thread.currentThread().getName());
//    }
}
