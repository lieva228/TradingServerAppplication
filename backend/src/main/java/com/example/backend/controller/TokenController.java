package com.example.backend.controller;

import com.example.backend.model.Token;
import com.example.backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @GetMapping
    public List<Token> findAll() {
        return tokenService.findAll();
    }

    @GetMapping("/{id}")
    public Token findProduct(@PathVariable Long id) {
        return tokenService.findToken(id);
    }
}