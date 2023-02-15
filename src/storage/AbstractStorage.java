package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object key = getExistingSearchKey(r.getUuid());
        doUpdate(key, r);
    }

    public void save(Resume r) {
        Object key = getNotExistingSearchKey(r.getUuid());
        doSave(key, r);
    }

    public Resume get(String uuid) {
        Object key = getExistingSearchKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        Object key = getExistingSearchKey(uuid);
        doDelete(key);
    }

    protected abstract boolean isExist(Object key);

    protected abstract Object findSearchKey(String uuid);

    protected abstract Resume doGet(Object key);

    protected abstract void doSave(Object key, Resume r);

    protected abstract void doDelete(Object key);

    protected abstract void doUpdate(Object key, Resume r);

    private Object getExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }
}
