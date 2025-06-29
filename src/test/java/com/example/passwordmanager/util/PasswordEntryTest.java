package com.example.passwordmanager.util;


import com.example.passwordmanager.entity.PasswordEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEntryTest {

    @Test
    void testGettersAndSetters() {
        PasswordEntry entry = new PasswordEntry();

        entry.setId(1L);
        entry.setWebsite("example.com");
        entry.setUsername("user");
        entry.setPassword("secret");

        assertEquals(1L, entry.getId());
        assertEquals("example.com", entry.getWebsite());
        assertEquals("user", entry.getUsername());
        assertEquals("secret", entry.getPassword());
    }
}