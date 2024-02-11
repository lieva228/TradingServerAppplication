package com.example.backend.strategy.RSIStrategy;


import com.example.backend.indicator.Indicator;
import com.example.backend.indicator.Kline;
import com.example.backend.indicator.RSI;
import com.example.backend.model.Side;
import com.example.backend.model.Strategy;
import com.example.backend.strategy.Condition;
import com.example.backend.strategy.Position;
import com.example.backend.strategy.StrategyAbstract;

import java.util.List;
import java.util.UUID;

public class RSIStrategy extends StrategyAbstract {
    String token;
    private Condition condition = Condition.READY;
    public RSIStrategy(List<Kline> closingPrice, int period, String token) {
        indicators.put("RSI", new RSI(closingPrice, period));
        this.token = token;
    }

    public Side update(double newPrice) {
        Indicator RSI = indicators.get("RSI");
        RSI.update(newPrice);
        if (position == Position.NONE) {
            switch (condition) {
                case READY -> {
                    return RSI.check();
                }
                case LONG_STOP -> {
                    if (RSI.get() >= 50) condition = Condition.READY;
                    if (RSI.check() == Side.SELL) {
                        return Side.SELL;
                    }
                }
                case SHORT_STOP -> {
                    if (RSI.get() <= 50) condition = Condition.READY;
                    if (RSI.check() == Side.BUY) {
                        return Side.BUY;
                    }
                }
            }
        }
        return Side.NONE;
    }
}
