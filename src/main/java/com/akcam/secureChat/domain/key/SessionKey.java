package com.akcam.secureChat.domain.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Document("sessionkeys")
public class SessionKey {
    @Id
    public UUID chatId;
    public String sessionKey;

    public static SessionKey of(@NonNull  String key){

        return new SessionKey(UUID.randomUUID(), key);
    }
}
