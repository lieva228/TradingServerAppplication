package com.example.backend.bybit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebsocketMessageJsonParser {
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("data")
    private Data[] data;
    @JsonProperty("ts")
    private long ts;
    @JsonProperty("type")
    private String type;

    public static WebsocketMessageJsonParser parseMessage(String message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message, WebsocketMessageJsonParser.class);
    }

    @lombok.Data
    public static class Data {
        @JsonProperty("start")
        private long start;
        @JsonProperty("end")
        private long end;
        @JsonProperty("interval")
        private String interval;
        @JsonProperty("open")
        private String open;
        @JsonProperty("close")
        private String close;
        @JsonProperty("high")
        private String high;
        @JsonProperty("low")
        private String low;
        @JsonProperty("volume")
        private String volume;
        @JsonProperty("turnover")
        private String turnover;
        @JsonProperty("confirm")
        private boolean confirm;
        @JsonProperty("timestamp")
        private long timestamp;
    }
}