package com.akcam.secureChat.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    boolean isValidChat(UUID chatId);
    Optional<List<UUID>> getUsersInChat(UUID chatId);
    void addUserToChat(UUID chatId, UUID userId);
    Optional<UUID> createChat(UUID user1, UUID user2);
    UUID createUser();

    UUID findChatID(UUID senderId, UUID recipientId);
}
