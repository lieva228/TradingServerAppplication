package com.example.backend.controller;

import com.example.backend.dto.AddStrategyRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.Strategy;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/info")
    public User findUser() {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.getById(currentUserId);
    }

    @GetMapping("/strategies")
    public List<Strategy> findStrategies() {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.findStrategiesByUserId(currentUserId);
    }

    @GetMapping("add/strategy")
    public Strategy addStrategy(@RequestBody AddStrategyRequest addStrategyRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.addStrategy(currentUserId, addStrategyRequest);
    }

    @PostMapping("/edit")
    public User editUser(@RequestBody UserEditRequest userEditRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.editUser(currentUserId, userEditRequest);
    }
}