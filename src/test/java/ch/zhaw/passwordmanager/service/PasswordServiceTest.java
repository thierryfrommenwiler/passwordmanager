package ch.zhaw.passwordmanager.service;

import ch.zhaw.passwordmanager.entity.PasswordEntry;
import ch.zhaw.passwordmanager.repository.PasswordEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordServiceTest {

    @Mock
    private PasswordEntryRepository repository; // das gemockte Repository

    @InjectMocks
    private PasswordService service; // das zu testende Service-Objekt, Repository wird „eingespritzt“

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // initialisiert @Mock und @InjectMocks
    }

    @Test
    void testFindAll() {
        List<PasswordEntry> dummyList = List.of(new PasswordEntry(), new PasswordEntry());
        when(repository.findAll()).thenReturn(dummyList);

        List<PasswordEntry> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testFindByIdFound() {
        PasswordEntry entry = new PasswordEntry();
        entry.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entry));

        Optional<PasswordEntry> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<PasswordEntry> result = service.findById(99L);

        assertTrue(result.isEmpty());
        verify(repository).findById(99L);
    }

    @Test
    void testSave() {
        PasswordEntry entry = new PasswordEntry();
        entry.setUsername("test");

        when(repository.save(entry)).thenReturn(entry);

        PasswordEntry saved = service.save(entry);

        assertEquals("test", saved.getUsername());
        verify(repository).save(entry);
    }

    @Test
    void testDeleteById() {
        service.deleteById(5L);
        verify(repository).deleteById(5L);
    }

    @Test
    void testExistsByIdTrue() {
        when(repository.existsById(7L)).thenReturn(true);

        boolean result = service.existsById(7L);

        assertTrue(result);
        verify(repository).existsById(7L);
    }
}
