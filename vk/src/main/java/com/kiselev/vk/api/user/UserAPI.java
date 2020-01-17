package com.kiselev.vk.api.user;

import com.google.common.collect.Lists;
import com.kiselev.vk.api.AbstractAPI;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;

import java.util.List;
import java.util.stream.Collectors;

public class UserAPI extends AbstractAPI {

    public UserAPI(VkApiClient client, UserActor user) {
        super(client, user);
    }

    public static UserAPI create(VkApiClient client, UserActor user) {
        return new UserAPI(
                client,
                user
        );
    }

    public List<UserXtrCounters> users(List<String> usersIds) {
        List<List<String>> lists = Lists.partition(usersIds, 1000);
        return lists.stream()
                .map(list -> {
                    try {
                        return client.users()
                                .get(user)
                                .fields(UserField.values())
                                .userIds(list)
                                .execute();
                    } catch (ApiException | ClientException exception) {
                        throw new RuntimeException(exception.getMessage(), exception);
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
