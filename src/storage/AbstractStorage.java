package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract boolean isExist(Object key);

    protected abstract Object findKey(String uuid);

    protected abstract Resume getFromStorage(Object key);

    protected abstract void saveInStorage(Resume r);

    protected abstract void deleteFromStorage(Object key);

    protected abstract void updateStorage(Object key, Resume r);

    public void update(Resume r) {
        Object key = findKey(r.getUuid());
        if (!isExist(key)) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateStorage(key, r);
    }

    public void save(Resume r) {
        Object key = findKey(r.getUuid());
        if (isExist(key)) {
            throw new ExistStorageException(r.getUuid());
        }
        saveInStorage(r);
    }

    public Resume get(String uuid) {
        Object key = findKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return getFromStorage(key);
    }

    public void delete(String uuid) {
        Object key = findKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(key);
    }
}
