
package com.example.passwordmanager.controller;

import com.example.passwordmanager.entity.PasswordEntry;
import com.example.passwordmanager.service.PasswordService;
import com.example.passwordmanager.util.PasswordGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordEntryController {

    private final PasswordService service;

    public PasswordEntryController(PasswordService service) {
        this.service = service;
    }

    @GetMapping
    public List<PasswordEntry> getAll() {
        return service.findAll();
    }

    @PostMapping
    public PasswordEntry save(@RequestBody PasswordEntry entry) {
        return service.save(entry);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/generate")
    public String generatePassword() {
        return PasswordGenerator.generate();
    }
}
