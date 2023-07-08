package com.akcam.secureChat.infrastructure;

import com.akcam.secureChat.domain.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMessageRepository {
    void save(Message message, UUID chatId);
    List<Message> getMessagesOfChat(UUID chatId);

}
