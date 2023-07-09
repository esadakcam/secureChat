package com.akcam.secureChat.infrastructure;

import com.akcam.secureChat.domain.message.Message;
import com.google.common.base.Preconditions;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageRepository implements IMessageRepository {
    private final HashMap<UUID, List<Message>> messages = new HashMap<>();

    @Override
    public void save(@NonNull Message message, @NonNull UUID chatId) {
        var messageContent = message.getContent();
        Preconditions.checkNotNull(messageContent, "Message content cannot be null!");

        message.setChatId(chatId);

        var messageList = messages.computeIfAbsent(chatId, k -> new LinkedList<Message>());
        messageList.add(message);
    }

    @Override
    public List<Message> getMessagesOfChat(UUID chatId) {
        return messages.get(chatId)
                .stream()
                .sorted((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()))
                .toList();
    }

    @Override
    public List<Message> getMessagesOfChat(List<UUID> userChats, Date date) {
        var userMessagesSinceDate = new LinkedList<Message>();
        for (var elem : userChats) {
            var chatMessages = messages.get(elem).stream()
                    .filter(message -> message.getTimestamp().after(date))
                    .toList();
            userMessagesSinceDate.addAll(chatMessages);
        }
        return userMessagesSinceDate;
    }
}
