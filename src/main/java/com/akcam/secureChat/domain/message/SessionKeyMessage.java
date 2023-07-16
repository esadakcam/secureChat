package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.nio.ByteBuffer;

@EqualsAndHashCode(callSuper = true)
@Data
public class SessionKeyMessage extends AbstractMessage {
    private final ByteBuffer sessionKey;
}
