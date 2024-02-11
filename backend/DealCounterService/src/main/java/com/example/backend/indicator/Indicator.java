package com.example.backend.indicator;

import com.example.backend.model.Side;

import java.util.List;

public interface Indicator {

    double get();

    double getTemp(double newPrice);

    void init(List<Kline> closingPrices);

    void update(double newPrice);

    Side check();
}