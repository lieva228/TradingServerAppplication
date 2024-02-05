package com.example.backend.controller;

import com.example.backend.dto.AddStrategyRequest;
import com.example.backend.model.Assets;
import com.example.backend.model.Strategy;
import com.example.backend.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetsController {
    private final AssetsService assetsService;

    @GetMapping("/{id}")
    public Assets findAssetsByUser(@PathVariable Long id) {
        return assetsService.findAssertsByUserId(id);
    }

    @PostMapping("/add/{id}")
    public Strategy addStrategy(@PathVariable Long id, @RequestBody AddStrategyRequest addStrategyRequest) {
        return assetsService.addStrategy(addStrategyRequest);
    }
}
