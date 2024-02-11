package com.example.backend.bybit;

import com.example.backend.bybit.constant.BybitApiConfig;
import com.example.backend.model.Strategy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TradingWebsocketHandler {
    private Integer updateCount = 0;

    public TradingWebsocketHandler() {
    }

    public void handleMessage(String message) {
        try {
            WebsocketMessageJsonParser parsedMessage = WebsocketMessageJsonParser.parseMessage(message);
            if (parsedMessage.getTopic() != null && parsedMessage.getData()[0].isConfirm()) {
//                TradeOrderRequest tradeOrderRequest = strategyMap
//                        .get(parsedMessage.getTopic().substring(8))
//                        .update(Double.parseDouble(parsedMessage.getData()[0].getClose())
//                );
//                if (tradeOrderRequest != null) {
//                    batchOrderRequest.addRequest(tradeOrderRequest);
//                }
//                if (++updateCount == strategyMap.size()) {
//                    sendBatchOrderRequest();
//                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
