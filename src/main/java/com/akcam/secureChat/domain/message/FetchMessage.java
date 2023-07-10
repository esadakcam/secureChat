package com.akcam.secureChat.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class FetchMessage extends AbstractMessage {
    @NotNull private Date from;
    public FetchMessage(@NotNull Date from, UUID senderID) {
        this.from = from;
        this.senderId = senderID;
    }
}
