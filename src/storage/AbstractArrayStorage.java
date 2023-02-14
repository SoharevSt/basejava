package storage;

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

    public final void updateStorage(Object key, Resume r) {
        storage[(int) key] = r;
    }

    public final Resume getFromStorage(Object key) {
        return storage[(int) key];
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }

    public final boolean isExist(Object key) {
        return (int) key >= 0;
    }
}
