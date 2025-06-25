
package com.example.passwordmanager.repository;

import com.example.passwordmanager.entity.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für PasswordEntry. übernimmt alle Standard-Datenbankoperationen automatisch
public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
}
