package com.example.backend.service.proto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "trade.server.grpc")
public record UserProtoServiceProperty(String host, int port) {}
