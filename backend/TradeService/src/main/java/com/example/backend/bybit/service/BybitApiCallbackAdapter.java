package com.example.backend.bybit.service;

import com.example.backend.bybit.constant.ApiCallback;
import com.example.backend.bybit.constant.exception.BybitApiError;
import com.example.backend.bybit.constant.exception.BybitApiException;
import io.micrometer.common.lang.Nullable;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;


public class BybitApiCallbackAdapter<T> implements Callback<T> {

    private final ApiCallback<T> callback;

    static Converter.Factory converterFactory = JacksonConverterFactory.create();
    @SuppressWarnings("unchecked")
    @Nullable
    private static final Converter<ResponseBody, BybitApiError> errorBodyConverter =
            (Converter<ResponseBody, BybitApiError>) converterFactory.responseBodyConverter(
                    BybitApiError.class, new Annotation[0], null);

    public BybitApiCallbackAdapter(ApiCallback<T> callback) {
        this.callback = callback;
    }

    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            if (response.code() == 504) {
                // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
                // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
                return;
            }
            try {
                BybitApiError apiError = getBybitApiError(response);
                onFailure(call, new BybitApiException(apiError));
            } catch (IOException e) {
                onFailure(call, new BybitApiException(e));
            }
        }
    }

    private static BybitApiError getBybitApiError(Response<?> response) throws IOException, BybitApiException {
        ResponseBody errorBody = response.errorBody();
        if (errorBody != null && errorBodyConverter != null) {
            return errorBodyConverter.convert(errorBody);
        }
        // Handle the case where there is no error converter or error body.
        throw new BybitApiException("Response error body was null or couldn't be converted.");
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable throwable) {
        if (throwable instanceof BybitApiException) {
            callback.onFailure(throwable);
        } else {
            callback.onFailure(new BybitApiException(throwable));
        }
    }
}