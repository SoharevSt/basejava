package storage;

import exception.StorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected final void doSave(Resume r) {
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

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.stream(storage).limit(size).collect(Collectors.toCollection(ArrayList::new));
    }

    public final int size() {
        return size;
    }

    protected final boolean isExist(Object key) {
        return (int) key >= 0;
    }

    protected abstract void saveInArrayStorage(Resume r);
}
