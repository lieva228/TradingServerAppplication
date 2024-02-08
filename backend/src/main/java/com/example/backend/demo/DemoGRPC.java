package com.example.backend.demo;

import com.example.backend.user.creation.UserCreationRequest;
import com.example.backend.user.creation.UserCreationResponse;
import com.example.backend.user.creation.UserCreationServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class DemoGRPC extends UserCreationServiceGrpc.UserCreationServiceImplBase {
    @Override
    public void create(UserCreationRequest request, StreamObserver<UserCreationResponse> responseObserver) {
        responseObserver.onNext(UserCreationResponse.newBuilder().build());
        // TODO GRPC trailers
        responseObserver.onCompleted();
    }
}
