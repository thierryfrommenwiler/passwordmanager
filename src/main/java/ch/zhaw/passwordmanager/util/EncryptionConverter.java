
package ch.zhaw.passwordmanager.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// Klasse um Passwörter verschlüsselt in der Datenbank zu speichern
@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {
    //Key für Verschlüsselung (AES-Verschlüsselung)
    private static final String SECRET_KEY = "secret1234567890";
    private static final SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

    // Werte verschlüsseln
    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            Cipher cipher = Cipher.getInstance("AES"); // AES-Verschlüsselung holen
            cipher.init(Cipher.ENCRYPT_MODE, key); // Modus: verschlüsseln

            // Verschlüsseltes Ergebnis wird in Base64 umgewandelt (damit es als Text gespeichert werden kann)
            return Base64.getEncoder()
                    .encodeToString(
                            cipher.doFinal(attribute.getBytes())
                    );
        } catch (Exception e) {
            //imfalle eines Fehlers wird hier eine Exception geworfen
            throw new IllegalStateException(e);
        }
    }

    // Werte entschlüsseln
    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            Cipher cipher = Cipher.getInstance("AES"); // AES-Verschlüsselung holen
            cipher.init(Cipher.DECRYPT_MODE, key); // Modus: entschlüsseln

            // Entschlüsseltes Ergebnis zurückgeben
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception e) {
            //imfalle eines Fehlers wird hier eine Exception geworfen
            throw new IllegalStateException(e);
        }
    }
}
