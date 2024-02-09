package com.example.backend.controller;

import com.example.backend.dto.AddTokenRequest;
import com.example.backend.model.Token;
import com.example.backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TokenService tokenService;

    @PostMapping("/add")
    public Token addTokenAdmin(@RequestBody AddTokenRequest addTokenRequest) {
        return tokenService.addToken(addTokenRequest);
    }
}