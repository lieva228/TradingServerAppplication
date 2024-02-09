package com.example.backend.grpc;

import com.example.backend.user.creation.*;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GatewayController extends UserTradeServiceGrpc.UserTradeServiceImplBase {

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<EmptyResponse> responseObserver) {
        responseObserver.onNext(EmptyResponse.newBuilder().setAnswer(
                "log from grpc service" +
                request.getApiKey() + "\n" +
                request.getSecretKey() + "\n" +
                request.getUserId() + "\n" +
                request.getStrategiesList()
        ).build());
        responseObserver.onCompleted();
    }

    @Override
    public void editUser(EditUserRequest request, StreamObserver<EmptyResponse> responseObserver) {
        super.editUser(request, responseObserver);
    }

    @Override
    public void addStrategyToUser(AddStrategyToUserRequest request, StreamObserver<EmptyResponse> responseObserver) {
        super.addStrategyToUser(request, responseObserver);
    }

    @Override
    public void deleteStrategyFromUser(DeleteStrategyFromUserRequest request, StreamObserver<EmptyResponse> responseObserver) {
        super.deleteStrategyFromUser(request, responseObserver);
    }
}
