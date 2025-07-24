package com.example.calculatorservicegrpc.kafka;

import com.example.calculatorservicegrpc.outboxPattern.entity.DomainEvent;
import com.example.calculatorservicegrpc.outboxPattern.enums.EventStatus;
import com.example.calculatorservicegrpc.outboxPattern.repository.DomainEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisher {
    private final DomainEventRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.sum}")
    private String topic;

    @Scheduled(fixedDelay = 5000) // every 5 seconds
    public void publishPendingEvents() {
        List<DomainEvent> events = repository.findByStatus(EventStatus.PENDING);

        for (DomainEvent event : events) {
            try {
                kafkaTemplate.send(topic, event.getPayload());
                event.setStatus(EventStatus.PUBLISHED);
                event.setPublishedAt(Instant.now());
                repository.save(event);
                log.info("Published event {} to Kafka", event.getEventId());
            } catch (Exception ex) {
                log.error("Failed to publish event {}: {}", event.getEventId(), ex.getMessage());
            }
        }
    }
}

