package com.akcam.secureChat.domain.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@EqualsAndHashCode
@Getter
abstract class AbstractMessage {
    @NotNull protected UUID senderId;
}
