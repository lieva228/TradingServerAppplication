package com.example.backend.bybit.request;

import com.example.backend.bybit.request.data.CategoryType;
import com.example.backend.bybit.request.data.service.CategoryTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BatchOrderRequest {
    public BatchOrderRequest(CategoryType category, List<TradeOrderRequest> request) {
        this.category = category;
        this.request = request;
    }

    public void clear() {
        request.clear();
    }

    public void addRequest(TradeOrderRequest tradeOrderRequest) {
        request.add(tradeOrderRequest);
    }

    @JsonSerialize(using = CategoryTypeSerializer.class)
    private CategoryType category;
    private List<TradeOrderRequest> request;
}
