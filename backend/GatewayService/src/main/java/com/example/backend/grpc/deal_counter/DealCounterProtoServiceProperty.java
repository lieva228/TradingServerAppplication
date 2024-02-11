package com.example.backend.grpc.deal_counter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dealcounter.server.grpc")
public record DealCounterProtoServiceProperty(String host, int port) {}
