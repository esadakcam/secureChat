package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode
@Getter
abstract class AbstractMessage {
    protected UUID senderId;
}
