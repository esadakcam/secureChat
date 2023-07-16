package com.akcam.secureChat.infrastructure;

import lombok.NonNull;

import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Optional;
import java.util.UUID;

public interface IKeyRepository {
    void savePrivateKey(@NonNull UUID clientID, @NonNull Key key);

    void saveSessionKey(@NonNull UUID clientID, @NonNull ByteBuffer sessionKey);

    Optional<Key> getPrivateKey(@NonNull UUID clientID);
}
