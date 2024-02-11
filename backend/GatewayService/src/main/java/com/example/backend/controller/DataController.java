package com.example.backend.controller;

import com.example.backend.model.Strategy;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {
    private final UserService userService;

    @GetMapping("/strategies")
    public Strategy[] findAllStrategies() {
        return Strategy.values();
    }

    @GetMapping("/tokens")
    public List<String> findAllTokens() {
        return userService.getTokens();
    }
}