package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;
import static ppss.AsuncionesTablaRealizaReserva.*;

public class RealizaReservaTest {
    TestableRealizaReserva tr;
    OperacionStub opeStub;

    @BeforeEach
    public void setUp() throws Exception {
        tr = new TestableRealizaReserva();
        opeStub = new OperacionStub();
    }


    @Test
    public void realizaReservaTestC1() {
        String [] isbns = {isbnValido1};
        FactoriaOperaciones.setOperacion(opeStub);

        ReservaException excepcion =
                assertThrows(ReservaException.class,
                        ()->tr.realizaReserva(loginInvalido, passwordInvalido, socioValido, isbns));

        assertEquals("ERROR de permisos; ", excepcion.getMessage());
    }

    @Test
    public void realizaReservaTestC2()  {
        String [] isbns = {isbnValido1,isbnValido2};
        FactoriaOperaciones.setOperacion(opeStub);

        assertDoesNotThrow(
                ()-> tr.realizaReserva(loginValido, passwordValido, socioValido, isbns));

    }

    @Test
    public void realizaReservaTestC3()  {
        String [] isbns = {isbnInValido};
        FactoriaOperaciones.setOperacion(opeStub);

        ReservaException excepcion =
                assertThrows(ReservaException.class,
                        ()->tr.realizaReserva(loginValido, passwordValido, socioValido, isbns));

        assertEquals("ISBN invalido:33333; ", excepcion.getMessage());
    }

    @Test
    public void realizaReservaTestC4()  {
        String[] isbns = {isbnValido1};
        FactoriaOperaciones.setOperacion(opeStub);

        ReservaException excepcion =
                assertThrows(ReservaException.class,
                        () -> tr.realizaReserva(loginValido, passwordValido, socioInvalido, isbns));

        assertEquals("SOCIO invalido; ", excepcion.getMessage());

    }

    @Test
    public void realizaReservaTestC5()  {
        String [] isbns = {isbnValido1};
        opeStub.setAccesoBd(false);
        FactoriaOperaciones.setOperacion(opeStub);

        ReservaException excepcion =
                assertThrows(ReservaException.class,
                        () -> tr.realizaReserva(loginValido, passwordValido, socioValido, isbns));

        assertEquals("CONEXION invalida; ", excepcion.getMessage());

    }
}
