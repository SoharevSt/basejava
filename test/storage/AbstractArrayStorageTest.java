package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");
    private static final Resume RESUME_4 = new Resume("uuid4");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;

    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void update() {
        Resume updateResume = new Resume("uuid1");
        storage.update(updateResume);
        assertEquals(updateResume, storage.get("uuid1"));
    }

    @Test
    void updateNotExist() {
        Resume updateResume = new Resume();
        assertThrows(NotExistStorageException.class, () -> storage.update(updateResume));
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get("uuid4"));
    }

    @Test()
    void saveAlreadyExist() throws ExistStorageException {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test()
    void saveOverflow() throws ExistStorageException {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            fail("The overflow occurred early");
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void get() {
        assertEquals(RESUME_1, storage.get("uuid1"));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("not_exist"));
    }

    @Test
    void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid1"));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("not_exist"));
    }

    @Test
    void getAll() {
        Resume[] allResume = storage.getAll();
        assertEquals(3, allResume.length);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}