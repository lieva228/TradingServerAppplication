package com.example.backend.bybit.constant;

public class BybitApiConfig {
    public static final String MAINNET_DOMAIN = "https://api.bybit.com";
    public static final String STREAM_MAINNET_DOMAIN = "wss://stream.bybit.com";
    public static final long DEFAULT_RECEIVING_WINDOW = 5000;

    public static final String V5_PUBLIC_SPOT = "/v5/public/spot";
    public static final String V5_PUBLIC_LINEAR = "/v5/public/linear";
    public static final String V5_PUBLIC_INVERSE = "/v5/public/inverse";
    public static final String V5_PUBLIC_OPTION = "/v5/public/option";
    public static final String V3_PUBLIC_OPTION = "/option/usdc/public/v3";

    public static final String V3_CONTRACT_PRIVATE = "/contract/private/v3";
    public static final String V3_UNIFIED_PRIVATE = "/unified/private/v3";
    public static final String V3_CONTRACT_USDT_PUBLIC = "/contract/usdt/public/v3";
    public static final String V3_SPOT_PRIVATE = "/spot/private/v3";
    public static final String V5_PRIVATE = "/v5/private";
}
