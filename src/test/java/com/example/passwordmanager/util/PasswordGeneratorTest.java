package com.example.passwordmanager.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void testGeneratedPasswordHasCorrectLength() {
        String password = PasswordGenerator.generate();
        assertEquals(12, password.length());
    }

    @Test
    void testGeneratedPasswordIsNotNull() {
        String password = PasswordGenerator.generate();
        assertNotNull(password);
    }
}
