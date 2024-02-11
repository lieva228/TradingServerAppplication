package com.example.backend.config;

import com.example.backend.grpc.trade.UserTradeProtoServiceProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({UserTradeProtoServiceProperty.class})
public class ProtoConfig {}
