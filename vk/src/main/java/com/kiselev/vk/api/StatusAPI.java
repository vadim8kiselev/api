package com.kiselev.vk.api;

import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.status.Status;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class StatusAPI extends AbstractAPI {

    public Status get() {
        return safe(
                () -> client.status()
                        .get(user)
                        .execute()
        );
    }

    public OkResponse set() {
        return safe(
                () -> client.status()
                        .set(user)
                        .execute()
        );
    }
}
