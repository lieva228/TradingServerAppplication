package com.example.backend.service;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Strategy;
import com.example.backend.model.Token;
import com.example.backend.repository.StrategyRepository;
import com.example.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrategyService {
    private final StrategyRepository strategyRepository;

    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    public Strategy findStrategy(Long id) {
        return strategyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Strategy with id " + id + " not found")
        );
    }
}