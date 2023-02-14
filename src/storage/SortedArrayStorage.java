package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer findKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveInStorage(Resume r) {
        int index = Math.abs(Arrays.binarySearch(storage, 0, size, r)) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
        size++;
    }

    @Override
    protected void deleteFromStorage(Object key) {
        int numMoved = size - (int) key - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, (int) key + 1, storage, (int) key, numMoved);
            size--;
        }
    }
}
