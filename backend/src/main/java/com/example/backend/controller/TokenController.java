package com.example.backend.controller;

import com.example.backend.dto.AddTokenRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.Token;
import com.example.backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public Token findToken(@PathVariable Long id) {
        return tokenService.findToken(id);
    }
}