package com.example.calculatorservicegrpc.outboxPattern.service;

public interface OutboxService {
    void saveEvent(String aggregateType, String payload);
}
