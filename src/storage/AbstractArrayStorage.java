package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected final void doSave(Object key, Resume r) {
        if (size == AbstractArrayStorage.STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveInArrayStorage(r);
    }

    protected final void doUpdate(Object key, Resume r) {
        storage[(int) key] = r;
    }

    protected final Resume doGet(Object key) {
        return storage[(int) key];
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }

    protected final boolean isExist(Object key) {
        return (int) key >= 0;
    }

    protected abstract void saveInArrayStorage(Resume r);
}
