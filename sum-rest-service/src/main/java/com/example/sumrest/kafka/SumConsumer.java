package com.example.sumrest.kafka;

import com.example.sumrest.service.SumFileService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SumConsumer {

    private final SumFileService sumFileService;

    @KafkaListener(topics = "${kafka.topic.sum}", groupId = "sum-group")
    @Timed(value = "kafka.message.processing", description = "Kafka message processing time")
    public void consume(String sumValue) {
        log.info("Received from Kafka: {}", sumValue);
        sumFileService.addToCurrentSum(sumValue);
    }
}
