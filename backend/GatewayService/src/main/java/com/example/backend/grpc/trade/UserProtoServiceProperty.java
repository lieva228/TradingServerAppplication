package com.example.backend.grpc.trade;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "trade.server.grpc")
public record UserProtoServiceProperty(String host, int port) {}
