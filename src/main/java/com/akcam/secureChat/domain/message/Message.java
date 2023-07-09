package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class Message extends AbstractMessage {
    private UUID chatId;
    private UUID recipientId;
    private String content;
    private Date timestamp;
    private MessageStatus status;

}
