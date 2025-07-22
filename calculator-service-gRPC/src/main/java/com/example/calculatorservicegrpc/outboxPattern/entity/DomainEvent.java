package com.example.calculatorservicegrpc.outboxPattern.entity;

import com.example.calculatorservicegrpc.outboxPattern.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "outbox_event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String aggregateType;
    private String payload;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private Instant createdAt;
    private Instant publishedAt;
}

