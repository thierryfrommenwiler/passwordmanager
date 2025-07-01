
package ch.zhaw.passwordmanager.service;

import ch.zhaw.passwordmanager.entity.PasswordEntry;
import ch.zhaw.passwordmanager.repository.PasswordEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Diese Klasse enthält die eigentliche Logik zum Verwalten der Passwörter
@Service
public class PasswordService {
    // Spring erkennt @Service und stellt das PasswordEntryRepository bereit und wird unten dem Konst. übergeben
    private final PasswordEntryRepository repository;

    // Repository wird über Konstruktor iniziert
    public PasswordService(PasswordEntryRepository repository) {
        this.repository = repository;
    }

    // Gibt alle gespeicherten Passworteinträge zurück
    public List<PasswordEntry> findAll() {
        return repository.findAll();
    }

    // Sucht einen Eintrag anhand der ID
    public Optional<PasswordEntry> findById(Long id) {
        return repository.findById(id);
    }

    // Prüft ob ein Eintrag mit der ID existiert
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    // Löscht den Eintrag mit der angegebenen ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Speichert einen neuen oder aktualisierten Eintrag
    public PasswordEntry save(PasswordEntry entry) {
        // Falls keine URL mit http/https angegeben wurde, ergänze "https://"
        if (entry.getWebsite() != null &&
                !entry.getWebsite().startsWith("http://") &&
                !entry.getWebsite().startsWith("https://")) {
            entry.setWebsite("https://" + entry.getWebsite());
        }
        // Speichern in der Datenbank
        return repository.save(entry);
    }
}
