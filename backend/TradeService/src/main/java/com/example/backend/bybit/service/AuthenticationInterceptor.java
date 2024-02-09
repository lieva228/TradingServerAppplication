package com.example.backend.bybit.service;


import com.example.backend.bybit.constant.BybitApiConstants;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

import static com.example.backend.bybit.constant.HmacSHA256Signer.sign;

public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;

    private final String secret;

    private final Long recvWindow;

    public AuthenticationInterceptor(String apiKey, String secret, Long recvWindow) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.recvWindow = recvWindow;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        boolean isSignatureRequired = original.header(BybitApiConstants.SIGN_TYPE_HEADER) != null;

        // Endpoint requires signing the payload
        String payload = "";
        if ("GET".equals(original.method())) {
            payload = original.url().query(); // extract query params
            newRequestBuilder.get();
        }else if ("POST".equals(original.method()) && original.body() != null) {
            Buffer buffer = new Buffer();
            original.body().writeTo(buffer);
            payload = buffer.readUtf8();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(payload, mediaType);
            newRequestBuilder.post(body);
        }

        if (isSignatureRequired) {
            long timestamp = Instant.now().toEpochMilli();
            String signature = sign(apiKey, secret, StringUtils.isEmpty(payload) ? ""  : payload, timestamp, recvWindow);
            newRequestBuilder.addHeader(BybitApiConstants.API_KEY_HEADER, apiKey);
            newRequestBuilder.addHeader(BybitApiConstants.SIGN_HEADER, signature);
            newRequestBuilder.addHeader(BybitApiConstants.TIMESTAMP_HEADER, String.valueOf(timestamp));
            newRequestBuilder.addHeader(BybitApiConstants.RECV_WINDOW_HEADER, String.valueOf(recvWindow));
            newRequestBuilder.addHeader(BybitApiConstants.API_CONTENT_TYPE, BybitApiConstants.DEFAULT_CONTENT_TYPE);
        }
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secret);
    }
}