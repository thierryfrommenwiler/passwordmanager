
package ch.zhaw.passwordmanager.controller;

import ch.zhaw.passwordmanager.entity.PasswordEntry;
import ch.zhaw.passwordmanager.service.PasswordService;
import ch.zhaw.passwordmanager.util.PasswordGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller zur Verwaltung von Passwort-Einträgen.
 * Dieser Controller stellt HTTP-Endpunkte für CRUD-Operationen bereit.
 */

@RestController
@RequestMapping("/api/passwords")
public class PasswordEntryController {

    private final PasswordService service;

    public PasswordEntryController(PasswordService service) {
        this.service = service;
    }

    //Liefert alle gespeicherten Passwort-Einträge
    @GetMapping
    public List<PasswordEntry> getAll() {
        return service.findAll();
    }

    //Speichert einen neuen Passwort-Eintrag
    @PostMapping
    public PasswordEntry save(@RequestBody PasswordEntry entry) {
        return service.save(entry);
    }

    //Aktualisiert einen bestehenden Eintrag mit der angegebenen ID
    @PutMapping("/{id}")
    public ResponseEntity<PasswordEntry> update(@PathVariable Long id, @RequestBody PasswordEntry updatedEntry) {
        return service.findById(id)
                .map(entry -> {
                    entry.setWebsite(updatedEntry.getWebsite());
                    entry.setUsername(updatedEntry.getUsername());
                    entry.setPassword(updatedEntry.getPassword());
                    return ResponseEntity.ok(service.save(entry));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Löscht den Eintrag mit der angegebenen ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Generiert ein zufälliges Passwort
    @GetMapping("/generate")
    public String generatePassword() {
        return PasswordGenerator.generate();
    }
}
