package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveInArrayStorage(Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void doDelete(Object key) {
        storage[(int) key] = storage[size - 1];
        size--;
    }
}
