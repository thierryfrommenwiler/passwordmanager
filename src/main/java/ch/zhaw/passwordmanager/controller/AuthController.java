
package ch.zhaw.passwordmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//REST Controller. Methoden darunter liefern JSON oder HTTP-Antworten zurück
@RestController
//setzt den Pfad Endpunkte in dieser Klasse auf "/api/auth"
@RequestMapping("/api/auth")
public class AuthController {

    //setzt MasterPW
    private static final String MASTER_PASSWORD = "master123";

    // Methode wenn jemand eine POST request an /api/auth/login sendet
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        //Prüfung ob das eingegebene PW mit dem MasterPW übereinstimmt
        if (MASTER_PASSWORD.equals(request.getMasterPassword())) {
            //PW stimmt überein
            return ResponseEntity.ok().build();
        }
        //PW stimmt nicht übereien
        return ResponseEntity.status(401).build();
    }
    // Diese Klasse ist dafür da, das Passwort zu speichern, das der Benutzer beim Login eingibt
    public static class AuthRequest {
        private String masterPassword;
        public String getMasterPassword() { return masterPassword; }
        public void setMasterPassword(String masterPassword) { this.masterPassword = masterPassword; }
    }
}
