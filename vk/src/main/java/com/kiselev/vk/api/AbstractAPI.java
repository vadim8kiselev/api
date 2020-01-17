package com.kiselev.vk.api;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

public abstract class AbstractAPI {

    protected VkApiClient client;

    protected UserActor user;

    protected AbstractAPI(VkApiClient client, UserActor user) {
        this.client = client;
        this.user = user;
    }
}
