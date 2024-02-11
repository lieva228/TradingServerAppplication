package com.example.backend.service;

import com.example.backend.model.Token;
import com.example.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public List<Token> findAllTokens() {
        return tokenRepository.findAll();
    }

    public void addToken(Token token) {
        tokenRepository.save(token);
    }
}
