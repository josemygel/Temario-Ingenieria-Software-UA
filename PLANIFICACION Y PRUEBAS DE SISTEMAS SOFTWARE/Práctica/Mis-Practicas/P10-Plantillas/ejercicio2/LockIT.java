package ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockIT {

    @Test
    public void unlockOK() {
        MyClass certificado = new MyClass();

        certificado.setName("Clave");
        certificado.setId(2);
        certificado.setSecret(5);

        Lock candado = new Lock(10);
        Assertions.assertEquals(true, candado.unlock(certificado.calculateKey()));
   }

    @Test
    public void unlockFailed() {
        MyClass certificado = new MyClass();

        certificado.setName("Clave");
        certificado.setId(2);
        certificado.setSecret(6);

        Lock candado = new Lock(10);
        Assertions.assertEquals(false, candado.unlock(certificado.calculateKey()));
    }
}
