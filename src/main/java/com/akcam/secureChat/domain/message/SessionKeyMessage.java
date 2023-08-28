package com.akcam.secureChat.domain.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.nio.ByteBuffer;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor(onConstructor_ = {@JsonCreator})
public class SessionKeyMessage extends AbstractMessage {
    private final String sessionKey;
}
