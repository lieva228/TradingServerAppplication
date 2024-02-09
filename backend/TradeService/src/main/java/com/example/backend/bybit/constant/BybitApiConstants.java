package com.example.backend.bybit.constant;

import org.apache.commons.lang3.builder.ToStringStyle;

public class BybitApiConstants {
    public static final String API_CONTENT_TYPE = "Content-Type";

    public static final String API_KEY_HEADER = "X-BAPI-API-KEY";

    public static final String SIGN_HEADER = "X-BAPI-SIGN";

    public static final String SIGN_TYPE_HEADER = "X-BAPI-SIGN-TYPE";

    public static final String TIMESTAMP_HEADER = "X-BAPI-TIMESTAMP";

    public static final String RECV_WINDOW_HEADER = "X-BAPI-RECV-WINDOW";

    public static final long DEFAULT_RECEIVING_WINDOW = 5000;

    public static final int DEFAULT_PING_INTERVAL = 1;

    public static final String DEFAULT_MAX_ALIVE_TIME = "-1";

    public static final String DEFAULT_CONTENT_TYPE = "application/json";

    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = SIGN_TYPE_HEADER + ": 2";

    public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;
}
