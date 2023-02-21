package storage;

import exception.StorageException;
import model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("newName" + i));
            }
        } catch (StorageException e) {
            fail("The overflow occurred early");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("newName")));
    }
}
