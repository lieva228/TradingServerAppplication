package com.example.backend.config;

import com.example.backend.grpc.UserProtoServiceProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({UserProtoServiceProperty.class})
public class ProtoConfig {}