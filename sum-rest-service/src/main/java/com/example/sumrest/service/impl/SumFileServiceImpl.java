package com.example.sumrest.service.impl;

import com.example.sumrest.repository.SumRepository;
import com.example.sumrest.service.SumFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SumFileServiceImpl implements SumFileService {
    private final SumRepository sumRepository;

    @Override
    public int getCurrentSum() {
        return sumRepository.readSum();
    }

    @Override
    public void addToCurrentSum(String value) {
        try {
            int incoming = Integer.parseInt(value);
            int current = sumRepository.readSum();
            int updated = current + incoming;

            sumRepository.writeSum(updated);
            log.info("Sum updated: {} + {} = {}", current, incoming, updated);

        } catch (NumberFormatException e) {
            log.error("Invalid number format: {}", value, e);
        }
    }
}
