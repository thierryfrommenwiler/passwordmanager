
package com.example.passwordmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String MASTER_PASSWORD = "master123"; // sicher speichern!

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if (MASTER_PASSWORD.equals(request.getMasterPassword())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }

    public static class AuthRequest {
        private String masterPassword;
        public String getMasterPassword() { return masterPassword; }
        public void setMasterPassword(String masterPassword) { this.masterPassword = masterPassword; }
    }
}
