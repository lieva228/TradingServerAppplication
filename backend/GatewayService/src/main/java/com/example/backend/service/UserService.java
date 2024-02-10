package com.example.backend.service;

import com.example.backend.dto.AddDealRequest;
import com.example.backend.dto.RemoveDealRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.grpc.UserProtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserProtoService userProtoService;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with strategyId " + id + " not found")
        );
    }

    public List<String> getTokens() {
        return userProtoService.getTokens();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Deal> findDealsByUserId(Long userId) {
        return userProtoService.getDealsFromUser(userId);
    }

    public void create(User user, String apiKey, String secretKey) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException(user.getUsername() + " is taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(user.getEmail() + " is taken");
        }
        if (userProtoService.checkApiKey(apiKey)) {
            throw new RuntimeException(apiKey + " is taken");
        }
        save(user);
        userProtoService.createUser(user, apiKey, secretKey);
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
        if (userProtoService.checkApiKey(userEditRequest.apiKey())) {
            throw new RuntimeException(userEditRequest.apiKey() + " is taken");
        } else {
            userProtoService.editUser(userEditRequest);
        }
        userProtoService.editUser(userEditRequest);
        return save(user);
    }

    public Deal addDeal(long userId, AddDealRequest request) {
        Deal deal = new Deal(request.type(), request.amount(), request.token());
        userProtoService.addDeal(userId, deal);
        return deal;
    }

    public void deleteDeal(long userId, RemoveDealRequest request) {
        userProtoService.deleteDeal(userId, request);
    }

    public String getStrategyToken(long userId, String token) {
        return userProtoService.getTokenStrategyFromUser(userId, token);
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
