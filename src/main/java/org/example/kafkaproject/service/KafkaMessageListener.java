package org.example.kafkaproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class KafkaMessageListener {
    @Autowired
    @Qualifier("applicationTaskExecutor")
    private TaskExecutor taskExecutor;

    @Async
    @KafkaListener(topics = "newTopic", groupId = "gary-group")
    public void consume(String message) throws InterruptedException {
        log.info("Sleep for 1 second");
        TimeUnit.SECONDS.sleep(1);
        log.info("Received message: {}", message);
        log.info("Active threads: {}", ((ThreadPoolTaskExecutor) taskExecutor).getActiveCount());
    }
}
