package storage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MapStorageTest extends AbstractStorageTest {

   public MapStorageTest() {
       super(new MapStorage());
   }

    @Disabled
    @Test
    public void saveOverflow() {

    }
}