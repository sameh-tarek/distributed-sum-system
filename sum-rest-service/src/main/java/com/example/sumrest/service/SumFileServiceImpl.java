package com.example.sumrest.service;

import com.example.sumrest.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class SumFileServiceImpl implements SumFileService {
    private static final String FILE_PATH = "sum.txt";

    @Override
    public int getCurrentSum() {
        log.info("Reading current sum from file: {}", FILE_PATH);
        Path path = Paths.get(FILE_PATH);
        if(!path.toFile().exists()) {
            log.error("File not found {}", FILE_PATH);
            throw new NotFoundException("File not found: " + FILE_PATH);
        }

        try{
            log.info("File found, reading content...");
            String content = Files.readString(path).trim();
            log.info("Content read from file: {}", content);
            return content.isEmpty() ? 0 : Integer.parseInt(content);
        } catch(IOException ex) {
            log.error("Error reading file: {}", FILE_PATH, ex);
            throw new RuntimeException("Error reading file: " + FILE_PATH, ex);
        }
    }
}
