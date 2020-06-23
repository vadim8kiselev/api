package com.kiselev.vk.api;

import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.enums.GroupsFilter;
import com.vk.api.sdk.objects.groups.*;
import com.vk.api.sdk.objects.groups.responses.GetBannedResponse;
import com.vk.api.sdk.objects.groups.responses.IsMemberExtendedResponse;
import com.vk.api.sdk.objects.groups.responses.SearchResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class GroupAPI extends AbstractAPI {

    public BoolInt isMember(String groupId) {
        return safe(
                () -> client.groups()
                        .isMember(user, groupId)
                        .execute()
        );
    }

    public BoolInt isMember(String groupId, List<Integer> userIds) {
        return safe(
                () -> client.groups()
                        .isMember(user, groupId)
                        .userIds(userIds)
                        .execute()
        );
    }

    public IsMemberExtendedResponse isMemberExtended(String groupId) {
        return safe(
                () -> client.groups()
                        .isMemberExtended(user, groupId)
                        .execute()
        );
    }

    public IsMemberExtendedResponse isMemberExtended(String groupId, List<Integer> userIds) {
        return safe(
                () -> client.groups()
                        .isMemberExtended(user, groupId)
                        .userIds(userIds)
                        .execute()
        );
    }

    public List<GroupFull> getById() {
        return safe(
                () -> client.groups()
                        .getById(user)
                        .execute()
        );
    }

    public List<Integer> get() {
        return safe(
                () -> client.groups()
                        .get(user)
                        .execute()
                        .getItems()
        );
    }

    public List<GroupFull> getExtended() {
        return safe(
                () -> client.groups()
                        .getExtended(user)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getMembers() {
        return safe(
                () -> client.groups()
                        .getMembers(user)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getMembers(List<Fields> fields) {
        return safe(
                () -> client.groups()
                        .getMembers(user)
                        .fields(fields)
                        .execute()
                        .getItems()
        );
    }

    public List<Integer> getMembers(GroupsFilter filter) {
        return safe(
                () -> client.groups()
                        .getMembers(user)
                        .filter(filter)
                        .execute()
                        .getItems()
        );
    }

    public OkResponse join() {
        return safe(
                () -> client.groups()
                        .join(user)
                        .execute()
        );
    }

    public OkResponse leave(Integer groupId) {
        return safe(
                () -> client.groups()
                        .leave(user, groupId)
                        .execute()
        );
    }

    public SearchResponse search(String q) {
        return safe(
                () -> client.groups()
                        .search(user, q)
                        .execute()
        );
    }

    public List<Group> getCatalog() {
        return safe(
                () -> client.groups()
                        .getCatalog(user)
                        .execute()
                        .getItems()
        );
    }

    public List<GroupCategory> getCatalogInfo() {
        return safe(
                () -> client.groups()
                        .getCatalogInfo(user)
                        .execute()
                        .getCategories()
        );
    }

    public List<GroupXtrInvitedBy> getInvites() {
        return safe(
                () -> client.groups()
                        .getInvites(user)
                        .execute()
                        .getItems()
        );
    }

    public List<GroupXtrInvitedBy> getInvitesExtended() {
        return safe(
                () -> client.groups()
                        .getInvitesExtended(user)
                        .execute()
                        .getItems()
        );
    }

    public List<UserFull> getInvitedUsers(Integer groupId) {
        return safe(
                () -> client.groups()
                        .getInvitedUsers(user, groupId)
                        .execute()
                        .getItems()
        );
    }

    public OkResponse ban(Integer groupId, Integer ownerId) {
        return safe(
                () -> client.groups()
                        .ban(user, groupId)
                        .ownerId(ownerId)
                        .execute()
        );
    }

    public OkResponse unban(Integer groupId, Integer ownerId) {
        return safe(
                () -> client.groups()
                        .unban(user, groupId)
                        .ownerId(ownerId)
                        .execute()
        );
    }

    public GetBannedResponse getBanned(Integer groupId) {
        return safe(
                () -> client.groups()
                        .getBanned(user, groupId)
                        .execute()
        );
    }

    public Group create(String title) {
        return safe(
                () -> client.groups()
                        .create(user, title)
                        .execute()
        );
    }

    public OkResponse edit(Integer groupId) {
        return safe(
                () -> client.groups()
                        .edit(user, groupId)
                        .execute()
        );
    }

    public GroupSettings getSettings(Integer groupId) {
        return safe(
                () -> client.groups()
                        .getSettings(user, groupId)
                        .execute()
        );
    }

    public List<Integer> getRequests(Integer groupId) {
        return safe(
                () -> client.groups()
                        .getRequests(user, groupId)
                        .fields(Fields.values())
                        .execute()
                        .getItems()
        );
    }

    public OkResponse editManager(Integer groupId, Integer userId) {
        return safe(
                () -> client.groups()
                        .editManager(user, groupId, userId)
                        .execute()
        );
    }

    public OkResponse invite(Integer groupId, Integer userId) {
        return safe(
                () -> client.groups()
                        .invite(user, groupId, userId)
                        .execute()
        );
    }

    public GroupLink addLink(Integer groupId, String link) {
        return safe(
                () -> client.groups()
                        .addLink(user, groupId, link)
                        .execute()
        );
    }

    public OkResponse deleteLink(Integer groupId, Integer linkId) {
        return safe(
                () -> client.groups()
                        .deleteLink(user, groupId, linkId)
                        .execute()
        );
    }

    public OkResponse editLink(Integer groupId, Integer linkId) {
        return safe(
                () -> client.groups()
                        .editLink(user, groupId, linkId)
                        .execute()
        );
    }

    public OkResponse reorderLink(Integer groupId, Integer linkId) {
        return safe(
                () -> client.groups()
                        .reorderLink(user, groupId, linkId)
                        .execute()
        );
    }

    public OkResponse removeUser(Integer groupId, Integer userId) {
        return safe(
                () -> client.groups()
                        .removeUser(user, groupId, userId)
                        .execute()
        );
    }

    public OkResponse approveRequest(Integer groupId, Integer userId) {
        return safe(
                () -> client.groups()
                        .approveRequest(user, groupId, userId)
                        .execute()
        );
    }
}
