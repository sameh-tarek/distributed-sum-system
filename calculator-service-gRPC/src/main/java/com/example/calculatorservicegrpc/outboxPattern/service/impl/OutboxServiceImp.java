package com.example.calculatorservicegrpc.outboxPattern.service.impl;

import com.example.calculatorservicegrpc.outboxPattern.entity.DomainEvent;
import com.example.calculatorservicegrpc.outboxPattern.enums.EventStatus;
import com.example.calculatorservicegrpc.outboxPattern.repository.DomainEventRepository;
import com.example.calculatorservicegrpc.outboxPattern.service.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxServiceImp implements OutboxService {
    private final DomainEventRepository repository;

    @Transactional
    @Override
    public void saveEvent(String aggregateType, String payload) {
        log.info("Saving event for aggregate type: {}, payload: {}", aggregateType, payload);
        DomainEvent event = DomainEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .aggregateType(aggregateType)
                .payload(payload)
                .status(EventStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        log.info("Event created: {}", event);
        repository.save(event);
    }
}
