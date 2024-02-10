package com.example.backend.controller;

import com.example.backend.dto.AddDealRequest;
import com.example.backend.dto.RemoveDealRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.Deal;
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

    @GetMapping("/deals")
    public List<Deal> findDials() {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.findDealsByUserId(currentUserId);
    }

    @GetMapping("/tokens")
    public List<String> findTokens() {
        return userService.getTokens();
    }

    @GetMapping("/user/{token}")
    public String findStrategyToken(@PathVariable String token) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.getStrategyToken(currentUserId, token);
    }

    @PostMapping("add/strategy")
    public Deal addDeal(@RequestBody AddDealRequest addStrategyRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.addDeal(currentUserId, addStrategyRequest);
    }

    @PostMapping("/edit")
    public User editUser(@RequestBody UserEditRequest userEditRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        return userService.editUser(currentUserId, userEditRequest);
    }

    @DeleteMapping("remove/strategy")
    public void removeStrategy(@RequestBody RemoveDealRequest removeDealRequest) {
        long currentUserId = userService.getCurrentUser().getId();
        userService.deleteDeal(currentUserId, removeDealRequest);
    }

    // for test
    @GetMapping("/admin")
    public User admin() {
        User currentUser = userService.getCurrentUser();
        userService.getAdmin();
        return currentUser;
    }
}