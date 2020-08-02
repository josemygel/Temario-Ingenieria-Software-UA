package ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockTest {

    @Test
    public void testUnLockOK() {
        Lock candado = new Lock(10);

        candado.unlock(10);
        Assertions.assertEquals(false, candado.isLocked());
    }

    @Test
    public void testUnLockFailed() {
        Lock candado = new Lock(10);

        candado.unlock(11);
        Assertions.assertEquals(true, candado.isLocked());
    }
}
