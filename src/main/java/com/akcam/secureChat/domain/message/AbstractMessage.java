package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@EqualsAndHashCode
@Getter
abstract class AbstractMessage {
    @NotNull protected UUID senderId;
}
