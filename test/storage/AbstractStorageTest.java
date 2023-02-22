package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NAME_1 = "fullName1";
    private static final String NAME_2 = "fullName2";
    private static final String NAME_3 = "fullName3";
    private static final String NAME_4 = "fullName4";
    private static final String UUID_NOT_EXIST = "notExistUuid";
    private static final String NEW_NAME = "newName";
    private static final Resume RESUME_1 = new Resume(UUID_1, NAME_1);
    private static final Resume RESUME_2 = new Resume(UUID_2, NAME_2);
    private static final Resume RESUME_3 = new Resume(UUID_3, NAME_3);
    private static final Resume RESUME_4 = new Resume(UUID_4, NAME_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;

    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Resume[0], storage.getAllSorted().toArray());
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_1, NAME_4);
        storage.update(updateResume);
        assertSame(updateResume, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() {
        Resume updateResume = new Resume(NEW_NAME);
        assertThrows(NotExistStorageException.class, () -> storage.update(updateResume));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveAlreadyExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    public void getAll() {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertArrayEquals(expected.toArray(), actual.toArray());
        assertSize(storage.getAllSorted().size());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}