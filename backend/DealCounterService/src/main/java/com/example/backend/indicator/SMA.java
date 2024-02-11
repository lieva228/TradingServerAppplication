package com.example.backend.indicator;

import com.traidl.scalpbot.market.rest.response.data.Kline;

import java.util.LinkedList;
import java.util.List;

public class SMA implements Indicator {

    private double currentSum;
    private final int period;
    private final LinkedList<Double> prices;

    public SMA(List<Kline> closingPrices, int period) {
        this.period = period;
        prices = new LinkedList<>();
        init(closingPrices);
    }

    @Override
    public double get() {
        return currentSum / (double) period;
    }

    @Override
    public double getTemp(double newPrice) {
        return ((currentSum - prices.get(0) + newPrice) / (double) period);
    }

    @Override
    public void init(List<Kline> closingPrices) {
        if (period > closingPrices.size()) return;

        for (int i = closingPrices.size() - period - 1; i < closingPrices.size() - 1; i++) {
            prices.add(closingPrices.get(i).close());
            currentSum += (closingPrices.get(i).close());
        }
    }

    @Override
    public void update(double newPrice) {
        currentSum -= prices.get(0);
        prices.removeFirst();
        prices.add(newPrice);
        currentSum += newPrice;
    }

    @Override
    public Signal check() {
        return Signal.NONE;
    }
}