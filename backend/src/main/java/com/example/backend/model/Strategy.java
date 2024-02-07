package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
//    @JoinColumn(username = "token_id")
    private Token token;

    private double amount;

    @Enumerated(EnumType.STRING)
    private StrategyType strategy;

    public enum StrategyType {
        RSI,
        SMA,
    }
}