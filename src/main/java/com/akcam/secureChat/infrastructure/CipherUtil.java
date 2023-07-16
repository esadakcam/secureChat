package com.akcam.secureChat.infrastructure;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Optional;

@Log4j2
public final class CipherUtil {
    public static Optional<Cipher> getEncryptModeCipher(@NonNull Key publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Optional.of(cipher); // to encrypt cipher.update(message), cipher.doFinal();
        } catch (Exception e) {
            log.warn("Cipher could not generated", e);
            return Optional.empty();
        }
    }

    public static Optional<Cipher> getDecryptModeCipher(@NonNull Key privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return Optional.of(cipher); // to decrypt cipher.update(message), cipher.doFinal();
        } catch (Exception e) {
            log.warn("An error occurred while creating the cipher {}", e.toString());
            return Optional.empty();
        }
    }

}
