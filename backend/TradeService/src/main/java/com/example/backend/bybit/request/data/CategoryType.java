package com.example.backend.bybit.request.data;

import lombok.Getter;

/**
 *  Category type. Spot,linear, inverse
 */
@Getter
public enum CategoryType {
    SPOT("spot"),
    LINEAR("linear"),
    INVERSE("inverse"),
    OPTION("option");
    private final String categoryTypeId;

    CategoryType(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }
}
