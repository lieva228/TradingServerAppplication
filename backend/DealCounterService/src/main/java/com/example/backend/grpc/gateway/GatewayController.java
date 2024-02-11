package com.example.backend.grpc.gateway;

import com.example.backend.dealcounter.DealCounterServiceGrpc;
import com.example.backend.dealcounter.GetTokensRequest;
import com.example.backend.dealcounter.TokensResponse;
import com.example.backend.model.Token;
import com.example.backend.service.TokenService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import java.util.stream.Collectors;

@GRpcService
@RequiredArgsConstructor
public class GatewayController extends DealCounterServiceGrpc.DealCounterServiceImplBase {
    private final TokenService tokenService;

    @Override
    public void getTokens(GetTokensRequest request, StreamObserver<TokensResponse> responseObserver) {
        responseObserver.onNext(
                TokensResponse.newBuilder()
                .addAllTokens(tokenService.findAllTokens()
                        .stream().map(Token::getToken).collect(Collectors.toList()))
                .build()
        );
        responseObserver.onCompleted();
    }
}
