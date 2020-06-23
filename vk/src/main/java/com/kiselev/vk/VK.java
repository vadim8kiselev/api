package com.kiselev.vk;

import com.google.gson.Gson;
import com.kiselev.vk.api.*;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VK {

    private final VkApiClient client;

    private final UserActor user;

    public static VK auth(Integer userId, String accessToken) {
        return new VK(
                new VkApiClient(
                        HttpTransportClient.getInstance(),
                        new Gson(),
                        3
                ),
                new UserActor(userId, accessToken)
        );
    }

    public MessageAPI messages() {
        return MessageAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public UserAPI users() {
        return UserAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public FriendAPI friends() {
        return FriendAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public GroupAPI groups() {
        return GroupAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public AccountAPI account() {
        return AccountAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public LikeAPI likes() {
        return LikeAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public SearchAPI search() {
        return SearchAPI.builder()
                .client(client)
                .user(user)
                .build();
    }

    public StatusAPI status() {
        return StatusAPI.builder()
                .client(client)
                .user(user)
                .build();
    }
}
