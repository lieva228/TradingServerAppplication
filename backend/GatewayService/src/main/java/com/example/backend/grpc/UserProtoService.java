package com.example.backend.grpc;

import com.example.backend.dto.RemoveStrategyRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.Strategy;
import com.example.backend.model.User;
import com.example.backend.user.creation.*;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProtoService {
    private final UserTradeServiceGrpc.UserTradeServiceBlockingStub stub;

    public UserProtoService(UserProtoServiceProperty property) {
        Channel channel = ManagedChannelBuilder
                .forAddress(property.host(), property.port())
                .usePlaintext()
                .build();
        this.stub = UserTradeServiceGrpc.newBlockingStub(channel);
    }

    public void createUser(User user) {
        List<UserTradeServiceStrategy> strategies = new ArrayList<>();
        for (Strategy strategy : user.getStrategies()) {
            strategies.add(
                    UserTradeServiceStrategy.newBuilder()
                            .setStrategyId(strategy.getId())
                            .setAmount(strategy.getAmount())
                            .setType(strategy.getStrategy().name())
                            .build()
            );
        }
        CreateUserRequest createUserRequest = CreateUserRequest.newBuilder()
                .setUserId(user.getId())
                .setApiKey(user.getApiKey())
                .setSecretKey(user.getSecretKey())
                .addAllStrategies(strategies)
                .build();
        EmptyResponse response = stub.createUser(createUserRequest);
        System.out.println(response.getAnswer());
    }

    public void editUser(UserEditRequest userEditRequest) {
        EditUserRequest editUserRequest = EditUserRequest.newBuilder()
                .setApiKey(userEditRequest.apiKey())
                .setSecretKey(userEditRequest.secretKey())
                .build();
        EmptyResponse response = stub.editUser(editUserRequest);
        System.out.println(response.getAnswer());
    }

    public void addStrategy(long userId, Strategy strategy) {
        UserTradeServiceStrategy userTradeServiceStrategy
                = UserTradeServiceStrategy.newBuilder()
                .setStrategyId(strategy.getId())
                .setAmount(strategy.getAmount())
                .setType(strategy.getStrategy().name())
                .build();
        EmptyResponse response = stub.addStrategyToUser(
                AddStrategyToUserRequest.newBuilder()
                        .setUserId(userId)
                        .setStrategy(userTradeServiceStrategy)
                        .build()
        );
        System.out.println(response.getAnswer());
    }

    public void deleteStrategy(long userId, RemoveStrategyRequest request) {
        DeleteStrategyFromUserRequest deleteStrategyFromUserRequest
                = DeleteStrategyFromUserRequest.newBuilder()
                .setUserId(userId)
                .setStrategyId(request.strategyId())
                .build();
        EmptyResponse response = stub.deleteStrategyFromUser(deleteStrategyFromUserRequest);
        System.out.println(response.getAnswer());
    }
}
