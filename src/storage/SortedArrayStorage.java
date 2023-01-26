package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveInStorage(Resume r) {
        int index = Math.abs(Arrays.binarySearch(storage, 0, size, r)) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteFromStorage(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}