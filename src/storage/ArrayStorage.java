package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private final Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = checkAvailability(r.getUuid());
        if (index == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR: Storage full");
            }
        } else {
            System.out.println("ERROR: Resume with uuid " + r.getUuid() + " is already in storage");
        }
    }

    public void update(Resume r) {
        int index = checkAvailability(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Resume " + r.getUuid() + " missing from the storage");
        }
    }

    public Resume get(String uuid) {
        int index = checkAvailability(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("ERROR: Resume " + uuid + " missing from the storage");
        return null;
    }

    public void delete(String uuid) {
        int index = checkAvailability(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume missing from the storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int checkAvailability(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
