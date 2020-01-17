package com.kiselev.vk;

import com.google.gson.Gson;
import com.kiselev.vk.api.friend.FriendAPI;
import com.kiselev.vk.api.message.MessageAPI;
import com.kiselev.vk.api.user.UserAPI;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VK {

    private VkApiClient client;

    private UserActor user;

    private VK(VkApiClient client, UserActor user) {
        this.client = client;
        this.user = user;
    }

    public static VK auth(Integer userId, String accessToken) {
        return new VK(
                new VkApiClient(
                        HttpTransportClient.getInstance(),
                        new Gson(),
                        Integer.MAX_VALUE
                ),
                new UserActor(userId, accessToken)
        );
    }

    public MessageAPI messages() {
        return MessageAPI.create(client, user);
    }

    public UserAPI users() {
        return UserAPI.create(client, user);
    }

    public FriendAPI friends() {
        return FriendAPI.create(client, user);
    }
}
