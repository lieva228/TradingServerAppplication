package com.example.backend.grpc.trade;

import com.example.backend.dto.NewDealRequest;
import com.example.backend.trade.AddDealRequest;
import com.example.backend.trade.UserTradeServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserTradeProtoService {
    private final UserTradeServiceGrpc.UserTradeServiceBlockingStub stub;

    public UserTradeProtoService(UserTradeProtoServiceProperty property) {
        Channel channel = ManagedChannelBuilder
                .forAddress(property.host(), property.port())
                .usePlaintext()
                .build();
        this.stub = UserTradeServiceGrpc.newBlockingStub(channel);
    }

    public void addDeal(NewDealRequest newDealRequest) {
        stub.addDeal(AddDealRequest.newBuilder()
                        .setToken(newDealRequest.token())
                        .setStrategy(newDealRequest.strategy().name())
                        .setSide(newDealRequest.side().name())
                        .build()
        );
    }
}
