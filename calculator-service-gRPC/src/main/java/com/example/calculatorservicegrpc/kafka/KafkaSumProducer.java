package com.example.calculatorservicegrpc.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaSumProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.sum}")
    private String topic;

    public void sendSum(int sum) {
        String message = String.valueOf(sum);
        kafkaTemplate.send(topic, message);
        log.info("Sent sum {} to Kafka topic {}", sum, topic);
    }
}
