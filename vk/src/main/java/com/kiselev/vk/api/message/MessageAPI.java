package com.kiselev.vk.api.message;

import com.kiselev.vk.api.AbstractAPI;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Dialog;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetDialogsResponse;
import com.vk.api.sdk.objects.messages.responses.GetHistoryResponse;
import com.vk.api.sdk.queries.messages.MessagesGetDialogsQuery;
import com.vk.api.sdk.queries.messages.MessagesGetHistoryQuery;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageAPI extends AbstractAPI {

    public MessageAPI(VkApiClient client, UserActor user) {
        super(client, user);
    }

    public static MessageAPI create(VkApiClient client, UserActor user) {
        return new MessageAPI(
                client,
                user
        );
    }

    public List<Dialog> dialogs() {
        try {
            MessagesGetDialogsQuery query = client.messages()
                    .getDialogs(user)
                    .count(200);

            int offset = 0;
            GetDialogsResponse response = query.execute();
            List<Dialog> dialogs = response.getItems();

            while (200 == response.getItems().size()) {
                offset += 200;
                response = query.offset(offset).execute();
                dialogs.addAll(response.getItems());
            }

            return dialogs;
        } catch (ApiException | ClientException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public Map<Integer, List<Message>> history() {
        List<Dialog> dialogs = dialogs();

        return dialogs.stream()
                .map(Dialog::getMessage)
                .map(Message::getUserId)
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
}
