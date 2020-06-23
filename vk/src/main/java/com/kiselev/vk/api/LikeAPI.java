package com.kiselev.vk.api;

import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.users.UserMin;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class LikeAPI extends AbstractAPI {

    public List<Integer> getList(Type type) {
        return safe(
                () -> client.likes()
                        .getList(user, type)
                        .execute()
                        .getItems()
        );
    }

    public List<UserMin> getListExtended(Type type) {
        return safe(
                () -> client.likes()
                        .getListExtended(user, type)
                        .execute()
                        .getItems()
        );
    }

    public Integer add(Type type, Integer itemId) {
        return safe(
                () -> client.likes()
                        .add(user, type, itemId)
                        .execute()
                        .getLikes()
        );
    }

    public Integer delete(Type type, Integer itemId) {
        return safe(
                () -> client.likes()
                        .delete(user, type, itemId)
                        .execute()
                        .getLikes()
        );
    }

    public BoolInt isLiked(Type type, Integer itemId) {
        return safe(
                () -> client.likes()
                        .isLiked(user, type, itemId)
                        .execute()
                        .getLiked()
        );
    }
}
