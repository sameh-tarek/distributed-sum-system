package com.example.calculatorservicegrpc.outboxPattern.repository;

import com.example.calculatorservicegrpc.outboxPattern.entity.DomainEvent;
import com.example.calculatorservicegrpc.outboxPattern.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainEventRepository extends JpaRepository<DomainEvent, Long> {
    List<DomainEvent> findByStatus(EventStatus status);
}

