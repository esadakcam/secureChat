package com.akcam.secureChat.application;

import com.akcam.secureChat.domain.key.SessionKey;
import com.akcam.secureChat.infrastructure.CipherUtil;
import com.akcam.secureChat.infrastructure.IKeyRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Log4j2
public class SSLService {
    private static final int KEY_SIZE = 2048;
    private final IKeyRepository keyRepository;
    private final KeyPair keyPair;
    public void saveSessionKey(UUID clientID, ByteBuffer sessionKey) {
//        keyRepository.save(clientID, new SessionKey(clientID, new String(sessionKey.array())));
        keyRepository.save(new SessionKey(clientID, new String(sessionKey.array())));
    }

    public void handleSessionKey(@NonNull UUID clientID, @NonNull String encryptedSessionKey) {
        saveSessionKey(clientID, StandardCharsets.UTF_8.encode("ESAD"));

//        var privateKey = keyPair.getPrivate();
//        var cipher = CipherUtil.getDecryptModeCipher(privateKey).orElseThrow();
//        cipher.update(encryptedSessionKey.array());
//        byte[] sessionKey;
//        try {
//            sessionKey = cipher.doFinal();
//            saveSessionKey(clientID, ByteBuffer.wrap(sessionKey));
//        } catch (Exception e) {
//            log.warn("An error occurred while decrypting the session key {}", e.toString());
//        }
    }

    public Key getPublicKey(@NonNull UUID senderId) {
        return keyPair.getPublic();
    }

}
