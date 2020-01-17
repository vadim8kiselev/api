package com.kiselev.vk.api.friend;

import com.kiselev.vk.api.AbstractAPI;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

public class FriendAPI extends AbstractAPI {

    public FriendAPI(VkApiClient client, UserActor user) {
        super(client, user);
    }

    public static FriendAPI create(VkApiClient client, UserActor user) {
        return new FriendAPI(
                client,
                user
        );
    }
}
