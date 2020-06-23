package com.kiselev.vk.api;

import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.friends.*;
import com.vk.api.sdk.objects.friends.responses.AddListResponse;
import com.vk.api.sdk.objects.friends.responses.DeleteResponse;
import com.vk.api.sdk.objects.friends.responses.SearchResponse;
import com.vk.api.sdk.objects.users.Fields;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class FriendAPI extends AbstractAPI {

    public List<Integer> get() {
        return safe(
                () -> client.friends()
                        .get(user)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> get(List<Fields> fields) {
        return safe(
                () -> client.friends()
                        .get(user)
                        .fields(fields)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getOnline() {
        return safe(
                () -> client.friends()
                        .getOnline(user)
                        .execute()
        );
    }

    public List<Integer> getOnlineWithOnlineMobile() {
        return safe(
                () -> client.friends()
                        .getOnlineWithOnlineMobile(user, true)
                        .execute()
                        .getOnlineMobile()
        );
    }

    public List<Integer> getMutual(Integer targetId) {
        return getMutual(user.getId(), targetId);
    }

    public List<Integer> getMutual(Integer sourceId, Integer targetId) {
        return safe(
                () -> client.friends()
                        .getMutual(user)
                        .sourceUid(sourceId)
                        .targetUid(targetId)
                        .execute()
        );
    }

    public List<MutualFriend> getMutualWithTargetUids(Integer sourceId, Integer... targetIds) {
        return safe(
                () -> client.friends()
                        .getMutualWithTargetUids(user, targetIds)
                        .sourceUid(sourceId)
                        .execute()
        );
    }

    public List<Integer> getRecent() {
        return safe(
                () -> client.friends()
                        .getRecent(user)
                        .execute()
        );
    }

    public List<Integer> getRequests() {
        return safe(
                () -> client.friends()
                        .getRequests(user)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getRequests(Boolean needMutual) {
        return safe(
                () -> client.friends()
                        .getRequests(user)
                        .needMutual(needMutual)
                        .execute()
                        .getItems()
        );
    }

    public List<RequestsXtrMessage> getRequestsExtended() {
        return safe(
                () -> client.friends()
                        .getRequestsExtended(user)
                        .execute()
                        .getItems()
        );
    }

    public String add(Integer userId) {
        return safe(
                () -> client.friends()
                        .add(user)
                        .userId(userId)
                        .execute()
                        .getValue()
        );
    }

    public OkResponse edit(Integer userId) {
        return safe(
                () -> client.friends()
                        .edit(user, userId)
                        .execute()
        );
    }

    public DeleteResponse delete(Integer userId) {
        return safe(
                () -> client.friends()
                        .delete(user)
                        .userId(userId)
                        .execute()
        );
    }

    public List<FriendsList> getLists() {
        return safe(
                () -> client.friends()
                        .getLists(user)
                        .execute().getItems()
        );
    }

    public AddListResponse addList(String name) {
        return safe(
                () -> client.friends()
                        .addList(user, name)
                        .execute()
        );
    }

    public OkResponse editList(Integer listId) {
        return safe(
                () -> client.friends()
                        .editList(user, listId)
                        .execute()
        );
    }

    public OkResponse deleteList(Integer listId) {
        return safe(
                () -> client.friends()
                        .deleteList(user, listId)
                        .execute()
        );
    }

    public List<Integer> getAppUsers() {
        return safe(
                () -> client.friends()
                        .getAppUsers(user)
                        .execute()
        );
    }

    public List<UserXtrPhone> getByPhones() {
        return safe(
                () -> client.friends()
                        .getByPhones(user)
                        .execute()
        );
    }

    public OkResponse deleteAllRequests() {
        return safe(
                () -> client.friends()
                        .deleteAllRequests(user)
                        .execute()
        );
    }

    public List<FriendStatus> areFriends(List<Integer> userIds) {
        return safe(
                () -> client.friends()
                        .areFriends(user, userIds)
                        .execute()
        );
    }

    public SearchResponse search(Integer userId) {
        return safe(
                () -> client.friends()
                        .search(user, userId)
                        .execute()
        );
    }
}
