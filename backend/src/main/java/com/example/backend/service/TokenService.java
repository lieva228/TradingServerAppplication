package com.example.backend.service;

import com.example.backend.dto.AddTokenRequest;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Token;
import com.example.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public List<Token> findAll() {
        return tokenRepository.findAll();
    }

    public Token findToken(Long id) {
        return tokenRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Token with id " + id + " not found")
        );
    }

    public Token addToken(AddTokenRequest addTokenRequest) {
        Token token = Token.builder().token(addTokenRequest.token()).build();
        tokenRepository.save(token);
        return token;
    }
}
