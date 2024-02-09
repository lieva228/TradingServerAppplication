package com.example.backend.bybit.service;


import com.example.backend.bybit.request.*;
import com.example.backend.bybit.request.data.TriggerBy;

import java.util.ArrayList;
import java.util.List;

public class BybitJsonConverter {

    public PlaceBatchOrderRequest convertToPlaceBatchOrderRequest(BatchOrderRequest batchOrderRequest) {
        List<PlaceOrderRequest> list = new ArrayList<>();
        for (TradeOrderRequest tradeOrderRequest : batchOrderRequest.getRequest()) {
            list.add(convertTradeToPlaceOrderRequest(tradeOrderRequest));
        }
        return PlaceBatchOrderRequest.builder()
                .category(batchOrderRequest.getCategory())
                .request(list)
                .build();
    }

    public CancelBatchOrderRequest convertToCancelBatchOrderRequest(BatchOrderRequest batchOrderRequest) {
        List<CancelOrderRequest> list = new ArrayList<>();
        for (TradeOrderRequest tradeOrderRequest : batchOrderRequest.getRequest()) {
            list.add(convertTradeToCancelOrderRequest(tradeOrderRequest));
        }
        return CancelBatchOrderRequest.builder()
                .category(batchOrderRequest.getCategory())
                .request(list)
                .build();
    }

    public CancelOrderRequest convertTradeToCancelOrderRequest(TradeOrderRequest tradeOrderRequest) {
        return CancelOrderRequest.builder()
                .category(tradeOrderRequest.getCategory() == null ? null : tradeOrderRequest.getCategory().getCategoryTypeId())
                .symbol(tradeOrderRequest.getSymbol())
                .orderId(tradeOrderRequest.getOrderId())
                .orderLinkId(tradeOrderRequest.getOrderLinkId())
                .orderFilter(tradeOrderRequest.getOrderFilter() == null ? null : tradeOrderRequest.getOrderFilter().getOrderFilterType())
                .build();
    }

    public PlaceOrderRequest convertTradeToPlaceOrderRequest(TradeOrderRequest tradeOrderRequest) {
        return PlaceOrderRequest.builder()
                .category(tradeOrderRequest.getCategory() == null ? null : tradeOrderRequest.getCategory().getCategoryTypeId())
                .symbol(tradeOrderRequest.getSymbol())
                .isLeverage(tradeOrderRequest.getIsLeverage())
                .side(tradeOrderRequest.getSide().getTransactionSide())
                .orderType(tradeOrderRequest.getOrderType().getOType())
//                .marketUnit(tradeOrderRequest.getMarketUnit().getUnit())
                .qty(tradeOrderRequest.getQty())
                .price(tradeOrderRequest.getPrice())
                .triggerDirection(tradeOrderRequest.getTriggerDirection())
                .orderFilter(tradeOrderRequest.getOrderFilter() == null ? null : tradeOrderRequest.getOrderFilter().getOrderFilterType())  // Optional
                .triggerPrice(tradeOrderRequest.getTriggerPrice()) // Optional
                .triggerBy(tradeOrderRequest.getTriggerBy() == null ? null : tradeOrderRequest.getTriggerBy().getTrigger()) // Optional
                .orderIv(tradeOrderRequest.getOrderIv())        // Optional
                .timeInForce(tradeOrderRequest.getTimeInForce() == null ? null : tradeOrderRequest.getTimeInForce().getDescription()) // Optional and default value depends on order type
                .positionIdx(tradeOrderRequest.getPositionIdx() == null ? null : tradeOrderRequest.getPositionIdx().getIndex()) // Optional
                .orderLinkId(tradeOrderRequest.getOrderLinkId()) // Optional
                .takeProfit(tradeOrderRequest.getTakeProfit())  // Optional
                .stopLoss(tradeOrderRequest.getStopLoss())      // Optional
                .tpTriggerBy(tradeOrderRequest.getTpTriggerBy() == null ? null : TriggerBy.LAST_PRICE.getTrigger()) // Optional, default to LastPrice
                .slTriggerBy(tradeOrderRequest.getSlTriggerBy() == null ? null : TriggerBy.LAST_PRICE.getTrigger()) // Optional, default to LastPrice
                .reduceOnly(tradeOrderRequest.getReduceOnly())  // Optional, default to false
                .closeOnTrigger(tradeOrderRequest.getCloseOnTrigger())  // Optional, default to false
                .smpType(tradeOrderRequest.getSmpType() == null ? null : tradeOrderRequest.getSmpType().getDescription()) // Optional, replace DEFAULT_SMP_TYPE_VALUE with a real default if needed
                .mmp(tradeOrderRequest.getMmp())               // Optional, default to false
                .tpslMode(tradeOrderRequest.getTpslMode())      // Optional
                .tpLimitPrice(tradeOrderRequest.getTpLimitPrice()) // Optional
                .slLimitPrice(tradeOrderRequest.getSlLimitPrice()) // Optional
                .tpOrderType(tradeOrderRequest.getTpOrderType() == null ? null : tradeOrderRequest.getTpOrderType().getOType())  // Optional, default to Market
                .slOrderType(tradeOrderRequest.getSlOrderType() == null ? null : tradeOrderRequest.getSlOrderType().getOType())  // Optional, default to Market
                .build();
    }
}