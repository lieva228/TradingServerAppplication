package com.example.backend.bybit;


import com.example.backend.bybit.constant.BybitApiConstants;
import com.example.backend.bybit.request.CancelBatchOrderRequest;
import com.example.backend.bybit.request.CancelOrderRequest;
import com.example.backend.bybit.request.PlaceBatchOrderRequest;
import com.example.backend.bybit.request.PlaceOrderRequest;
import com.example.backend.bybit.request.data.OrderStatus;
import retrofit2.Call;
import retrofit2.http.*;

public interface BybitApiService {
    @Headers(BybitApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/create-batch")
    Call<Object> createBatchOrder(@Body PlaceBatchOrderRequest placeBatchOrderRequest);

    @Headers(BybitApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/cancel-batch")
    Call<Object> cancelBatchOrder(@Body CancelBatchOrderRequest cancelBatchOrderRequest);
}