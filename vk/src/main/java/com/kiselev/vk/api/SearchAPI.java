package com.kiselev.vk.api;

import com.vk.api.sdk.objects.search.Hint;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class SearchAPI extends AbstractAPI {

    public List<Hint> getHints() {
        return safe(
                () -> client.search()
                        .getHints(user)
                .execute()
                .getItems()
        );
    }
}
