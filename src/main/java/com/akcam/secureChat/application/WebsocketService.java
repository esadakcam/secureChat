package com.akcam.secureChat.application;

import com.akcam.secureChat.domain.message.FetchMessage;
import com.akcam.secureChat.domain.message.HandshakeMessage;
import com.akcam.secureChat.domain.message.Message;
import com.akcam.secureChat.domain.message.SessionKeyMessage;
import com.akcam.secureChat.infrastructure.IMessageRepository;
import com.akcam.secureChat.infrastructure.IUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Log4j2
public class WebsocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;
    private final SSLService sslService;

    //TODO: Get session key and handle messages using this key
    public void handleMessage(@NonNull Message message) {
        var chatID = !userRepository.isValidChat(message.getChatId())
                ? userRepository.findChatID(message.getSenderId(), message.getRecipientId())
                : message.getChatId();
        messageRepository.save(message, chatID);
        simpMessagingTemplate.convertAndSendToUser(message.getRecipientId().toString(), "/queue/messages", message.getContent());
    }

    public void handleMessage(@NonNull FetchMessage message) {
        var userChats = userRepository.findChatsByUser(message.getSenderId());
        var messages = messageRepository.getMessagesOfChat(userChats, message.getFrom());
        simpMessagingTemplate.convertAndSendToUser(message.getSenderId().toString(), "/queue/messages",
                messages);
    }

    public void handleMessage(@NonNull HandshakeMessage message) {
        //Does not implement prototype, key is always the same. Use a bean factory
        var publicKey = sslService.getPublicKey(message.getSenderId());
        var response = new JSONObject();
        response.put("key",publicKey.getEncoded());
        simpMessagingTemplate.convertAndSendToUser(message.getSenderId().toString(), "/queue/messages",
                response.toString());
    }

    public void handleMessage(@NonNull SessionKeyMessage key) {
        var clientID = key.getSenderId();
        sslService.handleSessionKey(clientID, key.getSessionKey());

    }
}
