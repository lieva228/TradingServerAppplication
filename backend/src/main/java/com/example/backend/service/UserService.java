package com.example.backend.service;

import com.example.backend.dto.UserEditRequest;
import com.example.backend.exception.ResourceNotFoundException;
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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username " + username + " not found")
        );
    }

    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    public User editUser(Long id, UserEditRequest userEditRequest) {
        System.out.println("we are here");
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
        if (userRepository.existsByApiKey(userEditRequest.apiKey())) {
            throw new RuntimeException("Этот apiKey уже используется");
        } else {
            user.setApiKey(userEditRequest.apiKey());
            user.setSecretKey(userEditRequest.secretKey());
        }
        System.out.println("and here");
        return save(user);
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }
}
