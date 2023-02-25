package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public static final Comparator<Resume> RESUME_NAME_UUID_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void update(Resume r) {
        Object key = getExistingSearchKey(r.getUuid());
        doUpdate(key, r);
    }

    public void save(Resume r) {
        getNotExistingSearchKey(r.getUuid());
        doSave(r);
    }

    public Resume get(String uuid) {
        Object key = getExistingSearchKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        Object key = getExistingSearchKey(uuid);
        doDelete(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumes = doGetAll();
        resumes.sort(RESUME_NAME_UUID_COMPARATOR);
        return resumes;
    }

    protected abstract boolean isExist(Object key);

    protected abstract Object findSearchKey(String uuid);

    protected abstract Resume doGet(Object key);

    protected abstract void doSave(Resume r);

    protected abstract void doDelete(Object key);

    protected abstract void doUpdate(Object key, Resume r);

    protected abstract List<Resume> doGetAll();

    private Object getExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private void getNotExistingSearchKey(String uuid) {
        Object key = findSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
    }
}
