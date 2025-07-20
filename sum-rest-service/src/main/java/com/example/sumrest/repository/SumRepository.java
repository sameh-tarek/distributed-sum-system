package com.example.sumrest.repository;

import com.example.sumrest.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.*;


@Repository
@Slf4j
public class SumRepository {

    private static final String FILE_PATH = "sum.txt";

    public int readSum(){
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

    public void writeSum(int sum){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            log.info("Writing sum {} to file: {}", sum, FILE_PATH);
            writer.write(String.valueOf(sum));
            log.info("Sum written successfully to file: {}", FILE_PATH);
        } catch (IOException e) {
            log.error("Error writing to file: {}", FILE_PATH, e);
            throw new RuntimeException(e);
        }
    }
}
