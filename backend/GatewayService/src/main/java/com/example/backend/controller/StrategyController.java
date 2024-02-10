package com.example.backend.controller;

import com.example.backend.model.Strategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategies")
@RequiredArgsConstructor
public class StrategyController {

    @GetMapping
    public Strategy[] findAll() {
        return Strategy.values();
    }
}