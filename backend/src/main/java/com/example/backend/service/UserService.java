package com.example.backend.service;

import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        if (userRepository.existsByApiKey(user.getApiKey())) {
            throw new RuntimeException("Этот apiKey уже используется");
        }
        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь с именем " + username + " не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User editUser(Long id, UserEditRequest userEditRequest) {
        User user = userRepository.findById(id).orElseThrow();
        if (userRepository.existsByUsername(userEditRequest.username())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        } else {
            user.setUsername(userEditRequest.username());
        }
        if (userRepository.existsByApiKey(userEditRequest.apiKey())) {
            throw new RuntimeException("Этот apiKey уже используется");
        } else {
            user.setApiKey(userEditRequest.apiKey());
            user.setSecretKey(userEditRequest.secretKey());
        }
        return save(user);
    }
}
