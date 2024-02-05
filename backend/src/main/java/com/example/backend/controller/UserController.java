package com.example.backend.controller;

import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/current")
    public User findUser() {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.getById(currentUserId);
    }

    @GetMapping("/hi")
    public String check() {
        return "Hello, world!";
    }

    @PostMapping("/edit")
    public User editUser(@RequestBody UserEditRequest userEditRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.editUser(currentUserId, userEditRequest);
    }
}