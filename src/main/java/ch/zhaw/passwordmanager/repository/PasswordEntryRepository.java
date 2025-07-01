
package ch.zhaw.passwordmanager.repository;

import ch.zhaw.passwordmanager.entity.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für PasswordEntry. übernimmt alle Standard-Datenbankzugriffe automatisch
public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
}
