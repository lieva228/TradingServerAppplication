package com.example.backend.bybit;

import com.example.backend.bybit.constant.ApiCallback;
import com.example.backend.bybit.request.BatchOrderRequest;
import com.example.backend.bybit.service.AuthenticationInterceptor;
import com.example.backend.bybit.service.BybitApiCallbackAdapter;
import com.example.backend.bybit.service.BybitJsonConverter;
import io.micrometer.common.util.StringUtils;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class BybitApiAsyncTradeRestClientImplementation {
    private final BybitApiService bybitApiService;
    private final BybitJsonConverter converter = new BybitJsonConverter();

    public BybitApiAsyncTradeRestClientImplementation(String apiKey, String secret, String baseUrl, long recvWindow) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        OkHttpClient sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .pingInterval(20, TimeUnit.SECONDS)
                .build();
        Converter.Factory converterFactory = JacksonConverterFactory.create();
        bybitApiService = createService(BybitApiService.class, apiKey, secret, baseUrl, recvWindow, sharedClient, converterFactory);
    }

    public void createBatchOrder(BatchOrderRequest batchOrderRequest, ApiCallback<Object> callback) {
        var placeBatchOrderRequest = converter.convertToPlaceBatchOrderRequest(batchOrderRequest);
        bybitApiService.createBatchOrder(placeBatchOrderRequest).enqueue(new BybitApiCallbackAdapter<>(callback));
    }

    public void cancelBatchOrder(BatchOrderRequest batchOrderRequest, ApiCallback<Object> callback) {
        var cancelBatchOrderRequest = converter.convertToCancelBatchOrderRequest(batchOrderRequest);
        bybitApiService.cancelBatchOrder(cancelBatchOrderRequest).enqueue(new BybitApiCallbackAdapter<>(callback));
    }

    public static <S> S createService(Class<S> serviceClass, String apiKey, String secret, String baseUrl, long recvWindow, OkHttpClient sharedClient, Converter.Factory converterFactory) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory);
        OkHttpClient.Builder clientBuilder = sharedClient.newBuilder();
        if (!StringUtils.isEmpty(apiKey) && !StringUtils.isEmpty(secret)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret, recvWindow);
            clientBuilder.addInterceptor(interceptor);
        }
        retrofitBuilder.client(clientBuilder.build());
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }
}