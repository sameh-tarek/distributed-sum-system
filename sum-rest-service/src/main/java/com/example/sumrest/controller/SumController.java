package com.example.sumrest.controller;

import com.example.sumrest.service.SumFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/total")
public class SumController {

    private final SumFileService sumFileService;

    @GetMapping
    public int getCurrentSum(){
        return sumFileService.getCurrentSum();
    }

}
