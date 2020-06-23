package com.kiselev.vk.api;

import com.vk.api.sdk.objects.account.AccountCounters;
import com.vk.api.sdk.objects.account.UserSettings;
import com.vk.api.sdk.objects.account.responses.ChangePasswordResponse;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class AccountAPI extends AbstractAPI {

    public AccountCounters getCounters() {
        return safe(
                () -> client.account()
                        .getCounters(user)
                        .execute()
        );
    }

    public OkResponse setOnline() {
        return safe(
                () -> client.account()
                        .setOnline(user)
                        .execute()
        );
    }

    public OkResponse setOffline() {
        return safe(
                () -> client.account()
                        .setOffline(user)
                        .execute()
        );
    }

    public OkResponse ban(Integer ownerId) {
        return safe(
                () -> client.account()
                        .ban(user)
                        .ownerId(ownerId)
                        .execute()
        );
    }

    public OkResponse unban(Integer ownerId) {
        return safe(
                () -> client.account()
                        .unban(user)
                        .ownerId(ownerId)
                        .execute()
        );
    }

    public List<Integer> getBanned() {
        return safe(
                () -> client.account()
                        .getBanned(user)
                        .execute()
                        .getItems()
        );
    }

    public ChangePasswordResponse changePassword(String newPassword) {
        return safe(
                () -> client.account()
                        .changePassword(user, newPassword)
                        .execute()
        );
    }

    public UserSettings getProfileInfo() {
        return safe(
                () -> client.account()
                        .getProfileInfo(user)
                        .execute()
        );
    }

    public boolean saveProfileInfo() {
        return safe(
                () -> client.account()
                        .saveProfileInfo(user)
                        .execute()
                        .isChanged()
        );
    }
}
