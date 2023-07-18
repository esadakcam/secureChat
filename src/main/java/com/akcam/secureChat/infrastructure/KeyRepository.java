package com.akcam.secureChat.infrastructure;

import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Optional;
import java.util.UUID;

@Repository
public class KeyRepository implements IKeyRepository{
    @Override
    public void savePrivateKey(@NonNull UUID clientID, @NonNull Key key) {
        System.out.println(key.toString());
    }

    // Save with Date.now to be able to time out session key
    @Override
    public void saveSessionKey(@NonNull UUID clientID, @NonNull ByteBuffer sessionKey) {

        System.out.println(sessionKey.toString());
    }

    @Override
    public Optional<Key> getPrivateKey(@NonNull UUID clientID) {
        return Optional.empty();
    }
}
