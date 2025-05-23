
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
        return repository.save(entry);
    }
}
