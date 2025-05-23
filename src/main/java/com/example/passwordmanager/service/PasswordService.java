
package com.example.passwordmanager.service;

import com.example.passwordmanager.entity.PasswordEntry;
import com.example.passwordmanager.repository.PasswordEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordEntryRepository repository;

    public PasswordService(PasswordEntryRepository repository) {
        this.repository = repository;
    }

    public List<PasswordEntry> findAll() {
        return repository.findAll();
    }

    public Optional<PasswordEntry> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PasswordEntry save(PasswordEntry entry) {
        // Stelle sicher, dass die Website mit https:// oder http:// beginnt
        if (entry.getWebsite() != null &&
                !entry.getWebsite().startsWith("http://") &&
                !entry.getWebsite().startsWith("https://")) {
            entry.setWebsite("https://" + entry.getWebsite());
        }
        return repository.save(entry);
    }
}
