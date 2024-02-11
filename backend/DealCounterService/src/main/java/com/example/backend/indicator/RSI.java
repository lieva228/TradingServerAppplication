package com.example.backend.indicator;

import com.example.backend.model.Side;
import com.traidl.scalpbot.market.rest.response.data.Kline;

import java.util.List;

public class RSI implements Indicator {

    private double avgUp;
    private double avgDwn;
    private double prevClose;
    private final int period;
    public static int BUY_VALUE = 50;
    public static int SELL_VALUE = 50;

    public RSI(List<Kline> closingPrice, int period) {
        avgUp = 0;
        avgDwn = 0;
        this.period = period;
        init(closingPrice);
    }

    @Override
    public void init(List<Kline> closingPrices) {
        prevClose = closingPrices.get(0).close();
        for (int i = 1; i < period + 1; i++) {
            double change = closingPrices.get(i).close() - prevClose;
            if (change > 0) {
                avgUp += change;
            } else {
                avgDwn += Math.abs(change);
            }
        }

        //Initial SMA values
        avgUp = avgUp / (double) period;
        avgDwn = avgDwn / (double) period;

        //Dont use latest closed value
        for (int i = period + 1; i < closingPrices.size(); i++) {
            update(closingPrices.get(i).close());
        }
    }

    @Override
    public double get() {
        return 100 - 100.0 / (1 + avgUp / avgDwn);
    }

    @Override
    public double getTemp(double newPrice) {
        double change = newPrice - prevClose;
        double tempUp;
        double tempDwn;
        if (change > 0) {
            tempUp = (avgUp * (period - 1) + change) / (double) period;
            tempDwn = (avgDwn * (period - 1)) / (double) period;
        } else {
            tempDwn = (avgDwn * (period - 1) + Math.abs(change)) / (double) period;
            tempUp = (avgUp * (period - 1)) / (double) period;
        }
        return 100 - 100.0 / (1 + tempUp / tempDwn);
    }

    @Override
    public void update(double newPrice) {
        double change = newPrice - prevClose;
        if (change > 0) {
            avgUp = (avgUp * (period - 1) + change) / (double) period;
            avgDwn = (avgDwn * (period - 1)) / (double) period;
        } else {
            avgUp = (avgUp * (period - 1)) / (double) period;
            avgDwn = (avgDwn * (period - 1) + Math.abs(change)) / (double) period;
        }
        prevClose = newPrice;
        System.out.println("Current rsi : " + get());
    }

    @Override
    public Side check() {
        double temp = get();
        System.out.println("temp : " + temp);
        if (temp < BUY_VALUE) {
            return Side.BUY;
        }
        if (temp > SELL_VALUE) {
            return Side.SELL;
        }
        return Side.NONE;
    }
}