package com.akcam.secureChat.infrastructure;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {
    private final List<UUID> users = new LinkedList<>();
    private final HashMap<UUID, List<UUID>> chats = new HashMap<>();

    @Override
    public boolean isValidChat(UUID chatId) {
        return chats.containsKey(chatId);
    }

    @Override
    public Optional<List<UUID>> getUsersInChat(UUID chatId) {
        if (!isValidChat(chatId)) return Optional.empty();
        return Optional.of(chats.get(chatId));
    }

    @Override
    public void addUserToChat(UUID chatId, UUID userId) {
        Preconditions.checkArgument(isValidChat(chatId), "No such chat");
        Preconditions.checkArgument(chats.get(chatId).contains(userId), String.format("User %s already in chat %s",
                chatId.toString(), userId.toString()));
        chats.get(chatId).add(userId);
    }

    @Override
    public Optional<UUID> createChat(UUID user1, UUID user2) {
        if (containsBiChat(user1, user2))
            return Optional.empty();
        var chatID = UUID.randomUUID();
        chats.put(UUID.randomUUID(), List.of(user1, user2));
        return Optional.of(chatID);
    }

    @Override
    public UUID createUser() {
        var userID = UUID.randomUUID();
        users.add(userID);
        return userID;
    }

    @Override
    public UUID findChatID(UUID senderId, UUID recipientId) {
        var chatId = getBiChat(senderId, recipientId);
        return chatId.orElseGet(() -> createChat(senderId, recipientId).get());

    }

    @Override
    public List<UUID> findChatsByUser(UUID senderId) {
       return chats.keySet().stream()
               .filter(key -> chats.get(key).contains(senderId))
               .collect(Collectors.toList());
    }

    private boolean containsBiChat(UUID user1, UUID user2) {
        for (var key : chats.keySet()) {
            var users = chats.get(key);
            if (users.size() == 2 && users.contains(user1) && users.contains(user2))
                return true;
        }
        return false;
    }

    private Optional<UUID> getBiChat(UUID user1, UUID user2) {

        for (var key : chats.keySet()) {
            var users = chats.get(key);
            if (users.size() == 2 && users.contains(user1) && users.contains(user2))
                return Optional.of(key);
        }
        return Optional.empty();
    }
}
