package com.example.calculatorservicegrpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalculatorServiceGRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorServiceGRpcApplication.class, args);
    }

}
