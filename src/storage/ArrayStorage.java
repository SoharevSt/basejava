package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveInStorage(Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void deleteFromStorage(Object key) {
        storage[(int) key] = storage[size - 1];
        size--;
    }
}
