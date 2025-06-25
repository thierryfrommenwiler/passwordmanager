
package com.example.passwordmanager.util;

import java.security.SecureRandom;

// Diese Klasse erzeugt ein sicheres, zufälliges Passwort
public class PasswordGenerator {

    //Passwort Generierung
    public static String generate() {
        int length = 12; //Passwortlänge

        // Zeichen, die verwendet werden dürfen
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        // SecureRandom ist kryptographisch sicherer als Math.random()
        SecureRandom random = new SecureRandom();

        // StringBuilder zum schrittweisen Aufbau des Passworts
        StringBuilder sb = new StringBuilder(length);

        // Schleife baut das Passwort Zeichen für Zeichen zufällig zusammen
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        // Rückgabe des fertigen Passworts
        return sb.toString();
    }
}
