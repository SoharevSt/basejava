package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    protected Resume getFromStorage(Object key) {
        return list.get((Integer) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }

    @Override
    protected Integer findKey(String uuid) {
        for (Resume r : list) {
            if (uuid.equals(r.getUuid())) {
                return list.indexOf(r);
            }
        }
        return -1;
    }

    @Override
    protected void saveInStorage(Resume r) {
        list.add(r);
    }

    @Override
    protected void deleteFromStorage(Object key) {
        list.remove((int) key);
    }

    @Override
    protected void updateStorage(Object key, Resume r) {
        list.set((int) key, r);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
