package com.example.backend.grpc;

import com.example.backend.dto.RemoveDealRequest;
import com.example.backend.dto.UserEditRequest;
import com.example.backend.model.Deal;
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

    public void createUser(User user, String apiKey, String secretKey) {
        List<DealRequest> deals = new ArrayList<>();
        CreateUserRequest createUserRequest = CreateUserRequest.newBuilder()
                .setUserId(user.getId())
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .addAllDeals(deals)
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

    public void addDeal(long userId, Deal deal) {
        DealRequest dealRequest
                = DealRequest.newBuilder()
                .setStrategy(deal.strategy().name())
                .setAmount(deal.amount())
                .setToken(deal.token())
                .build();
        EmptyResponse response = stub.addDealToUser(
                AddDealToUserRequest.newBuilder()
                        .setUserId(userId)
                        .setDeal(dealRequest)
                        .build()
        );
        System.out.println(response.getAnswer());
    }

    public void deleteDeal(long userId, RemoveDealRequest request) {
        EmptyResponse response = stub.deleteDealFromUser(
                DeleteDealFromUserRequest.newBuilder()
                        .setUserId(userId)
                        .setToken(request.token())
                        .build()
        );
        System.out.println(response.getAnswer());
    }

    public List<Deal> getDealsFromUser(long userId) {
        DealsResponse dealsResponse = stub.getDealsFromUser(
                GetDealsFromUserRequest.newBuilder()
                        .setUserId(userId)
                        .build()
        );
        List<Deal> deals = new ArrayList<>();
        dealsResponse.getDealsList().forEach(
                dealRequest -> deals.add(
                        new Deal(
                                Strategy.fromString(dealRequest.getStrategy()),
                                dealRequest.getAmount(),
                                dealRequest.getToken()
                        )
                )
        );
        return deals;
    }

    public Boolean checkApiKey(String apiKey) {
        BooleanResponse response = stub.checkApiKey(
                CheckApiKeyRequest.newBuilder()
                        .setApiKey(apiKey)
                        .build()
        );
        return response.getAnswer();
    }

    public List<String> getTokens() {
        return null;
    }

    public String getTokenStrategyFromUser(long userId, String token) {
        StrategyResponse response = stub.getTokenStrategyFromUser(
                GetTokenStrategyFromUserRequest.newBuilder()
                        .setUserId(userId)
                        .setToken(token)
                        .build()
        );
        return response.getStrategy();
    }
}
