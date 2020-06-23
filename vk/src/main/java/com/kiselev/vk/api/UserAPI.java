package com.kiselev.vk.api;

import com.google.common.collect.Lists;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.enums.UsersType;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.users.responses.GetFollowersResponse;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsExtendedResponse;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsResponse;
import com.vk.api.sdk.objects.users.responses.SearchResponse;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
public class UserAPI extends AbstractAPI {

    public List<UserXtrCounters> users(List<String> usersIds) {
        List<List<String>> lists = Lists.partition(usersIds, 1000);
        return lists.stream()
                .map(list -> {
                    try {
                        return client.users()
                                .get(user)
                                .fields(Fields.values())
                                .userIds(list)
                                .execute();
                    } catch (ApiException | ClientException exception) {
                        throw new RuntimeException(exception.getMessage(), exception);
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<UserXtrCounters> get() {
        return safe(
                () -> client.users()
                        .get(user)
                        .execute()
        );
    }

    public SearchResponse search() {
        return safe(
                () -> client.users()
                        .search(user)
                        .execute()
        );
    }

    public BoolInt isAppUser() {
        return safe(
                () -> client.users()
                        .isAppUser(user)
                        .execute()
        );
    }

    public GetSubscriptionsResponse getSubscriptions() {
        return safe(
                () -> client.users()
                        .getSubscriptions(user)
                        .execute()
        );
    }

    public GetSubscriptionsExtendedResponse getSubscriptionsExtended() {
        return safe(
                () -> client.users()
                        .getSubscriptionsExtended(user)
                        .execute()
        );
    }

    public GetFollowersResponse getFollowers() {
        return safe(
                () -> client.users()
                        .getFollowers(user)
                        .execute()
        );
    }

    public List<Integer> getFollowers(Fields... fields) {
        return safe(
                () -> client.users()
                        .getFollowers(user)
                        .fields(Fields.values())
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getFollowers(List<Fields> fields) {
        return safe(
                () -> client.users()
                        .getFollowers(user)
                        .fields(Fields.values())
                        .execute()
                        .getItems()
        );
    }

    public OkResponse report(Integer userId, UsersType type) {
        return safe(
                () -> client.users()
                        .report(user, userId, type)
                        .execute()
        );
    }
}
