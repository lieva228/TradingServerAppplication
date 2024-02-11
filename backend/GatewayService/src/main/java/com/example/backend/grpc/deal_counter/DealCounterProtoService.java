package com.example.backend.grpc.deal_counter;

import com.example.backend.dealcounter.DealCounterServiceGrpc;
import com.example.backend.dealcounter.GetTokensRequest;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealCounterProtoService {
    private final DealCounterServiceGrpc.DealCounterServiceBlockingStub stub;

    public DealCounterProtoService(DealCounterProtoServiceProperty property) {
        Channel channel = ManagedChannelBuilder
                .forAddress(property.host(), property.port())
                .usePlaintext()
                .build();
        this.stub = DealCounterServiceGrpc.newBlockingStub(channel);
    }

    public List<String> getTokens() {
        return stub.getTokens(GetTokensRequest.newBuilder().build()).getTokensList();
    }
}
