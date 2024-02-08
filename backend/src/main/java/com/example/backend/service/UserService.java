package com.example.backend.service;

import com.example.backend.dto.AddStrategyRequest;
import com.example.backend.dto.RemoveStrategyRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Role;
import com.example.backend.model.Strategy;
import com.example.backend.model.Token;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.proto.UserProtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final StrategyService strategyService;
    private final UserProtoService userProtoService;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with strategyId " + id + " not found")
        );
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Strategy> findStrategiesByUserId(Long id) {
        User user = getById(id);
        return user.getStrategies();
    }

    public void create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException(user.getUsername() + " is taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(user.getEmail() + " is taken");
        }
        if (userRepository.existsByApiKey(user.getApiKey())) {
            throw new RuntimeException(user.getApiKey() + " is taken");
        }
        save(user);
        userProtoService.createUser(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username " + username + " not found")
        );
    }

    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    public User editUser(Long id, UserEditRequest userEditRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with strategyId " + id + " not found")
        );
        if (userRepository.existsByApiKey(userEditRequest.apiKey())) {
            throw new RuntimeException(user.getApiKey() + " is taken");
        } else {
            user.setApiKey(userEditRequest.apiKey());
            user.setSecretKey(userEditRequest.secretKey());
        }
        userProtoService.editUser(userEditRequest);
        return save(user);
    }

    public Strategy addStrategy(long userId, AddStrategyRequest request) {
        User user = getById(userId);
        List<Strategy> strategies = findStrategiesByUserId(userId);
        Token token = tokenService.findToken(request.tokenId());
        Strategy strategy = Strategy.builder()
                .strategy(request.type())
                .amount(request.amount())
                .token(token)
                .build();
        strategies.add(strategy);
        user.setStrategies(strategies);
        save(user);
        strategy = strategyService.save(strategy);
        userProtoService.addStrategy(userId, strategy);
        return strategy;
    }

    public void removeStrategy(long userId, RemoveStrategyRequest request) {
        User user = getById(userId);
        List<Strategy> strategies = findStrategiesByUserId(userId);
        Strategy toDelete = strategyService.findStrategy(request.strategyId());
        strategies.remove(toDelete);
        user.setStrategies(strategies);
        save(user);
        strategyService.delete(toDelete);
        userProtoService.deleteStrategy(userId, request);
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }

    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
