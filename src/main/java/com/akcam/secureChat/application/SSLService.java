package com.akcam.secureChat.application;

import com.akcam.secureChat.domain.message.SessionKeyMessage;
import com.akcam.secureChat.infrastructure.CipherUtil;
import com.akcam.secureChat.infrastructure.IKeyRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.security.Key;
import java.security.KeyPair;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Log4j2
public class SSLService {
    private final ConfigurableApplicationContext applicationContext;
    private final IKeyRepository keyRepository;
    //TODO: add timeout for saved session keys
    Key createKeyForClient(UUID clientId) {
        KeyPair rsaKey = applicationContext.getBean(KeyPair.class);
        Preconditions.checkNotNull(rsaKey, "Key cant be null");
        keyRepository.savePrivateKey(clientId, rsaKey.getPrivate());
        return rsaKey.getPublic();
    }

    public void saveSessionKey(UUID clientID, ByteBuffer sessionKey) {
        keyRepository.saveSessionKey(clientID, sessionKey);
    }

    public Optional<Key> getPrivateKey(UUID clientID) {
        return keyRepository.getPrivateKey(clientID);

    }

    public void handleSessionKey(UUID clientID, SessionKeyMessage key) {
        //TODO: Make irrelevant methods private
        var privateKey = getPrivateKey(clientID).orElseThrow();
        var cipher = CipherUtil.getDecryptModeCipher(privateKey).orElseThrow();
        cipher.update(key.getSessionKey().array());
        byte[] sessionKey;
        try {
            sessionKey = cipher.doFinal();
            saveSessionKey(clientID, ByteBuffer.wrap(sessionKey));
        } catch (Exception e) {
            log.warn("An error occurred while decrypting the session key {}", e.toString());
        }
    }
}
