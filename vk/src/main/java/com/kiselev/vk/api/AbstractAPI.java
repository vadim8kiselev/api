package com.kiselev.vk.api;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AbstractAPI {

    protected VkApiClient client;

    protected UserActor user;

    protected <T> T safe(APIOperation<T> operation) {
        try {
            return operation.execute();
        } catch (ClientException | ApiException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FunctionalInterface
    protected interface APIOperation<T> {

        T execute() throws ClientException, ApiException;
    }
}
