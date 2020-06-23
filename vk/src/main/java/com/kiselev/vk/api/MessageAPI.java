package com.kiselev.vk.api;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.objects.messages.responses.*;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsQuery;
import com.vk.api.sdk.queries.messages.MessagesGetHistoryQuery;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuperBuilder
public class MessageAPI extends AbstractAPI {

    public List<ConversationWithMessage> conversations() {
        try {
            MessagesGetConversationsQuery query = client.messages()
                    .getConversations(user)
                    .count(200);

            int offset = 0;
            GetConversationsResponse response = query.execute();
            List<ConversationWithMessage> conversations = response.getItems();

            while (200 == response.getItems().size()) {
                offset += 200;
                response = query.offset(offset).execute();
                conversations.addAll(response.getItems());
            }

            return conversations;
        } catch (ApiException | ClientException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public Map<Integer, List<Message>> history() {
        List<ConversationWithMessage> conversations = conversations();

        return conversations.stream()
                .map(ConversationWithMessage::getLastMessage)
                .map(Message::getFromId)
                .collect(Collectors.toMap(userId -> userId, this::history, (o1, o2) -> o2));
    }

    public List<Message> history(Integer userId) {
        try {
            MessagesGetHistoryQuery query = client.messages()
                    .getHistory(user)
                    .count(200)
                    .userId(userId);

            int offset = 0;
            GetHistoryResponse response = query.execute();
            List<Message> messages = response.getItems();

            while (200 == response.getItems().size()) {
                offset += 200;
                response = query.offset(offset).execute();
                messages.addAll(response.getItems());
            }

            return messages;
        } catch (ApiException | ClientException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public List<Message> search() {
        return safe(
                () -> client.messages()
                        .search(user)
                        .execute()
                        .getItems()
        );
    }

    public List<Message> getHistory() {
        return safe(
                () -> client.messages()
                        .getHistory(user)
                        .execute()
                        .getItems()
        );
    }

    public List<HistoryAttachment> getHistoryAttachments(Integer peerId) {
        return safe(
                () -> client.messages()
                        .getHistoryAttachments(user, peerId)
                        .execute()
                        .getItems()
        );
    }

    public Integer send() {
        return safe(
                () -> client.messages()
                        .send(user)
                        .execute()
        );
    }

    public Map<Integer, String> delete(List<Integer> messageIds) {
        return safe(
                () -> client.messages()
                        .delete(user)
                        .messageIds(messageIds)
                        .execute()
        );
    }

    public DeleteConversationResponse deleteConversation() {
        return safe(
                () -> client.messages()
                        .deleteConversation(user)
                        .execute()
        );
    }

    public OkResponse restore(Integer messageId) {
        return safe(
                () -> client.messages()
                        .restore(user, messageId)
                        .execute()
        );
    }

    public OkResponse markAsRead() {
        return safe(
                () -> client.messages()
                        .markAsRead(user)
                        .execute()
        );
    }

    public List<Integer> markAsImportant() {
        return safe(
                () -> client.messages()
                        .markAsImportant(user)
                        .execute()
        );
    }

    public LongpollParams getLongPollServer() {
        return safe(
                () -> client.messages()
                        .getLongPollServer(user)
                        .execute()
        );
    }

    public GetLongPollHistoryResponse getLongPollHistory() {
        return safe(
                () -> client.messages()
                        .getLongPollHistory(user)
                        .execute()
        );
    }

    /*public Chat getChat() {
        return safe(
                () -> client.messages()
                        .getChat(user)
                        .execute()
        );
    }

    public MessagesGetChatQueryWithFields getChat(Fields... fields) {
        return safe(
                () -> client.messages()
                        .getChat(user, fields)
                        .execute()
        );
    }

    public MessagesGetChatQueryWithChatIds getChat(Integer... chatIds) {
        return safe(
                () -> client.messages()
                        .getChat(user, chatIds)
                        .execute()
        );
    }

    public MessagesGetChatQueryWithChatIds getChat(List<Integer> chatIds) {
        return safe(
                () -> client.messages()
                        .getChat(user, chatIds)
                        .execute()
        );
    }

    public MessagesGetChatQueryWithChatIdsFields getChat(List<Integer> chatIds, List<Fields> fields) {
        return safe(
                () -> client.messages()
                        .getChat(user, chatIds, fields)
                        .execute()
        );
    }*/

    public Integer createChat(List<Integer> userIds) {
        return safe(
                () -> client.messages()
                        .createChat(user)
                        .userIds(userIds)
                        .execute()
        );
    }

    public OkResponse editChat(Integer chatId, String title) {
        return safe(
                () -> client.messages()
                        .editChat(user, chatId, title)
                        .execute()
        );
    }

    /*public MessagesGetChatUsersQuery getChatUsers() {
        return safe(
                () -> client.messages()
                        .getChatUsers(user)
                        .execute()
        );
    }

    public MessagesGetChatUsersQueryWithFields getChatUsers(Fields... fields) {
        return safe(
                () -> client.messages()
                        .getChatUsers(user, fields)
                        .execute()
        );
    }

    public MessagesGetChatUsersQueryWithFields getChatUsers(List<Fields> fields) {
        return safe(
                () -> client.messages()
                        .getChatUsers(user, fields)
                        .execute()
        );
    }

    public MessagesGetChatUsersQueryWithChatIds getChatUsers(Integer... chatIds) {
        return safe(
                () -> client.messages()
                        .getChatUsers(user, chatIds)
                        .execute()
        );
    }

    public MessagesGetChatUsersQueryWithChatIdsFields getChatUsers(List<Integer> chatIds, Fields... fields) {
        return safe(
                () -> client.messages()
                        .getChatUsers(user, chatIds, fields)
                        .execute()
        );
    }

    public MessagesGetChatUsersQueryWithChatIdsFields getChatUsers(List<Integer> chatIds, List<Fields> fields) {
        return safe(
                () -> client.messages()
                        .getChatUsers(user, chatIds, fields)
                        .execute()
        );
    }*/

    public OkResponse setActivity() {
        return safe(
                () -> client.messages()
                        .setActivity(user)
                        .execute()
        );
    }

    public SearchConversationsResponse searchConversations() {
        return safe(
                () -> client.messages()
                        .searchConversations(user)
                        .execute()
        );
    }

    public OkResponse addChatUser(Integer chatId, Integer userId) {
        return safe(
                () -> client.messages()
                        .addChatUser(user, chatId)
                        .userId(userId)
                        .execute()
        );
    }

    public OkResponse removeChatUser(Integer chatId, Integer userId) {
        return safe(
                () -> client.messages()
                        .removeChatUser(user, chatId)
                        .userId(userId)
                        .execute()
        );
    }

    public LastActivity getLastActivity(Integer userId) {
        return safe(
                () -> client.messages()
                        .getLastActivity(user, userId)
                        .execute()
        );
    }

    public SetChatPhotoResponse setChatPhoto(String file) {
        return safe(
                () -> client.messages()
                        .setChatPhoto(user, file)
                        .execute()
        );
    }

    public DeleteChatPhotoResponse deleteChatPhoto(Integer chatId) {
        return safe(
                () -> client.messages()
                        .deleteChatPhoto(user, chatId)
                        .execute()
        );
    }

    public OkResponse denyMessagesFromCommunity(Integer groupId) {
        return safe(
                () -> client.messages()
                        .denyMessagesFromGroup(user, groupId)
                        .execute()
        );
    }

    public OkResponse allowMessagesFromCommunity(Integer groupId) {
        return safe(
                () -> client.messages()
                        .allowMessagesFromGroup(user, groupId)
                        .execute()
        );
    }

    public IsMessagesFromGroupAllowedResponse isMessagesFromGroupAllowed(Integer groupId, Integer userId) {
        return safe(
                () -> client.messages()
                        .isMessagesFromGroupAllowed(user, groupId, userId)
                        .execute()
        );
    }
}
