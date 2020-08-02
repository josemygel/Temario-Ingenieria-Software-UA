package ppss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.ReservaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    //@Test
    void templateTest() {
        // PREPARAR ENTRADAS
        // CREAR STUBs
        // INYECTAR STUBs
        // INVOCAR SUT
        // VERIFICAR RESULTADO
        // RESTAURAR DATOS(si es necesario)
    }

    @Test
    @DisplayName("Realizar Reserva C1")
    void realizaReserva_C1() throws Exception {
        // Preparar entradas
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Luis";
        String[] isbns = new String[]{"11111"};
        String esperado = "ERROR de permisos; ";

        // CREAR STUB
        IOperacionBO op = new OperacionStub();
        Reserva reserva = new ReservaStub();

        // INYECTAR STUB
        reserva.setOperacion(op);

        // INVOCAR SUT
        Throwable resultado = assertThrows(ReservaException.class,
                () -> reserva.realizaReserva(login,password,socio,isbns));

        // VERIFICAR RESULTADO
        assertEquals(esperado,resultado.getMessage());
    }

    @Test
    @DisplayName("Realizar Reserva C2")
    void realizaReserva_C2() throws Exception {
        // Preparar entradas
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = new String[]{"11111","22222"};

        // CREAR STUB
        IOperacionBO op = new OperacionStub();
        Reserva reserva = new ReservaStub();

        // INYECTAR STUB
        reserva.setOperacion(op);

        // INVOCAR SUT +
        // VERIFICAR RESULTADO
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                reserva.realizaReserva(login, password, socio, isbns);
            }
        });
    }

    @Test
    @DisplayName("Realizar Reserva C3")
    void realizaReserva_C3() throws Exception {
        // Preparar entradas
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = new String[]{"33333"};
        String esperado = "ISBN invalido:33333; ";

        // CREAR STUB
        Reserva reserva = new ReservaStub();
        OperacionStub op = new OperacionStub();
        op.setExcepcion("IsbnInvalido");

        // INYECTAR STUB
        reserva.setOperacion(op);

        // INVOCAR SUT
        Throwable resultado = assertThrows(ReservaException.class,
                () -> reserva.realizaReserva(login,password,socio,isbns));

        // VERIFICAR RESULTADO
        assertEquals(esperado,resultado.getMessage());
    }
    @Test
    @DisplayName("Realizar Reserva C4")
    void realizaReserva_C4() throws Exception {
        // Preparar entradas
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String[] isbns = new String[]{"33333"};
        String esperado = "CONEXION invalida; va";

        // CREAR STUB
        Reserva reserva = new ReservaStub();
        OperacionStub op = new OperacionStub();
        op.setExcepcion("JDBC");

        // INYECTAR STUB
        reserva.setOperacion(op);

        // INVOCAR SUT
        Throwable resultado = assertThrows(ReservaException.class,
                () -> reserva.realizaReserva(login,password,socio,isbns));

        // VERIFICAR RESULTADO
        assertEquals(esperado,resultado.getMessage());
    }
}