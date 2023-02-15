package storage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Disabled
    @Test
    public void saveOverflow() {

    }
}