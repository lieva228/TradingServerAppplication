package com.example.backend.bybit.request;

import com.example.backend.bybit.request.data.CategoryType;
import com.example.backend.bybit.request.data.service.CategoryTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class PlaceBatchOrderRequest {
    @JsonSerialize(using = CategoryTypeSerializer.class)
    private CategoryType category;
    private List<PlaceOrderRequest> request;
}