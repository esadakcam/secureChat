package com.akcam.secureChat.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;
import java.security.*;

@Configuration
@Log4j2
public class SSLConfiguration {
    @Nullable
    @Bean(name = "keyPair")
    public KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            secureRandom.setSeed(System.currentTimeMillis());
            keyPairGenerator.initialize(4096, secureRandom);

            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            log.warn("An error occurred while creating keypair. {}", e.toString());
            return null;
        }
    }

}
