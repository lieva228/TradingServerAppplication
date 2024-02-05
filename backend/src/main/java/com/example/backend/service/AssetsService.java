package com.example.backend.service;

import com.example.backend.dto.AddStrategyRequest;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Assets;
import com.example.backend.model.Strategy;
import com.example.backend.model.Token;
import com.example.backend.model.User;
import com.example.backend.repository.AssetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetsService {
    private final AssetsRepository assetsRepository;
    private final UserService userService;
    private final TokenService tokenService;

    public Assets findAssertsByUserId(Long id) {
        User user = userService.getById(id);
        return assetsRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Assets for user with id " + id + " not found")
        );
    }

    public Strategy addStrategy(AddStrategyRequest request) {
        Assets assets = findAssertsByUserId(request.userId());
        Token token = tokenService.findToken(request.tokenId());
        List<Strategy> strategies = assets.getStrategies();
        Strategy strategy = Strategy.builder()
                .strategy(request.type())
                .assets(assets)
                .amount(request.amount())
                .token(token)
                .build();
        strategies.add(strategy);
        assets.setStrategies(strategies);
        assetsRepository.save(assets);
        return strategy;
    }
}
