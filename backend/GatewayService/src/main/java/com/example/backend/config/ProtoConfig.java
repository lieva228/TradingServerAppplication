package com.example.backend.config;

import com.example.backend.grpc.deal_counter.DealCounterProtoService;
import com.example.backend.grpc.deal_counter.DealCounterProtoServiceProperty;
import com.example.backend.grpc.trade.UserProtoServiceProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({UserProtoServiceProperty.class, DealCounterProtoServiceProperty.class})
public class ProtoConfig {}
