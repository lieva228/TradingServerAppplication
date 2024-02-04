package com.example.backend.service;

import com.example.backend.model.Token;
import com.example.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    TokenRepository tokenRepository;

    public List<Token> findAll() {
        return tokenRepository.findAll();
    }

    public Token findToken(Long id) {
        return tokenRepository.findById(id).orElseThrow();
    }
}
