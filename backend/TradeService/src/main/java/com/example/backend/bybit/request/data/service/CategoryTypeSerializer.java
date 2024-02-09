package com.example.backend.bybit.request.data.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.traidl.scalpbot.market.rest.request.data.CategoryType;

import java.io.IOException;

public class CategoryTypeSerializer extends JsonSerializer<CategoryType> {
    @Override
    public void serialize(CategoryType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getCategoryTypeId());
    }
}
