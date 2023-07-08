package com.akcam.secureChat.domain.message;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
public class Message extends AbstractMessage {
    private UUID chatId;
    private UUID recipientId;
    private String content;
    private Date timestamp;
    private MessageStatus status;

}
