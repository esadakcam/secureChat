package com.akcam.secureChat.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;
import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Configuration
@Log4j2
public class SSLConfiguration {
    @Bean
    @Nullable
    KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            log.warn("KeyPair could not generated", e);
            return null;
        }
    }

    @Bean("encryptCipher")
    @Autowired
    @Nullable
    Cipher getEncryptModeCipher(KeyPair keyPair) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            return cipher; // to encrypt cipher.update(message), cipher.doFinal();
        } catch (Exception e) {
            log.warn("Cipher could not generated", e);
            return null;
        }
    }

    @Bean("decryptCipher")
    @Autowired
    @Nullable
    Cipher getDecryptModeCipher(KeyPair keyPair) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            return cipher; // to decrypt cipher.update(message), cipher.doFinal();
        } catch (Exception e) {
            log.warn("Cipher could not generated", e);
            return null;
        }
    }

}
