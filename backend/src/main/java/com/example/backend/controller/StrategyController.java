package com.example.backend.controller;

import com.example.backend.model.Strategy;
import com.example.backend.service.StrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/strategies")
@RequiredArgsConstructor
public class StrategyController {
    private final StrategyService strategyService;

    @GetMapping
    public List<Strategy> findAll() {
        return strategyService.findAll();
    }

    @GetMapping("/{id}")
    public Strategy findStrategy(@PathVariable Long id) {
        return strategyService.findStrategy(id);
    }
}