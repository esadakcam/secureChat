package com.akcam.secureChat.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Nullable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Configuration
@Log4j2
public class SSLConfiguration {
    @Bean
    @Scope("prototype")
    @Nullable
    KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            log.warn("An error occurred while creating keypair. {}", e.toString());
            return null;
        }
    }


}
