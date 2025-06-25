
package com.example.passwordmanager.entity;

import jakarta.persistence.*;
import com.example.passwordmanager.util.EncryptionConverter;

@Entity
public class PasswordEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String website;
    private String username;

    @Convert(converter = EncryptionConverter.class)// benutzt einen speziellen Konverter zum Verschlüsseln/Entschlüsseln
    private String password;

    // Getter & Setter
    public Long getId() {
        return id; // Gibt die ID zurück
    }

    public void setId(Long id) {
        this.id = id; // Setzt eine neue ID
    }

    public String getWebsite() {
        return website; // Gibt die Website zurück
    }

    public void setWebsite(String website) {
        this.website = website; // Setzt die Website
    }

    public String getUsername() {
        return username; // Gibt den Benutzernamen zurück
    }

    public void setUsername(String username) {
        this.username = username; // Setzt den Benutzernamen
    }

    public String getPassword() {
        return password; // Gibt das Passwort zurück (entschlüsselt)
    }

    public void setPassword(String password) {
        this.password = password; // Setzt ein Passwort (wird beim Speichern verschlüsselt)
    }
}
