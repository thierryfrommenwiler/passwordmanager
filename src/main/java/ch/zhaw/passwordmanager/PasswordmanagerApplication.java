package ch.zhaw.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Haputklasse! Startpunkt der App
@SpringBootApplication
public class PasswordmanagerApplication {
    public static void main(String[] args) {

        // Startet die Spring Boot Anwendung
        SpringApplication.run(PasswordmanagerApplication.class, args);
    }
}
