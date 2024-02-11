package com.example.backend.strategy;


import com.example.backend.indicator.Indicator;
import com.example.backend.model.Side;

import java.util.HashMap;
import java.util.Map;

public abstract class StrategyAbstract {
    protected Position position = Position.NONE;

    protected Map<String, Indicator> indicators = new HashMap<>();

    public abstract Side update(double newPrice);
}
