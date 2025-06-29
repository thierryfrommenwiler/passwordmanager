package com.example.passwordmanager.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionConverterTest {

    private final EncryptionConverter converter = new EncryptionConverter();

    @Test
    void testEncryptionAndDecryption() {
        String original = "mypassword123!";
        String encrypted = converter.convertToDatabaseColumn(original);
        String decrypted = converter.convertToEntityAttribute(encrypted);

        assertNotNull(encrypted);
        assertNotEquals(original, encrypted);
        assertEquals(original, decrypted);
    }

}
