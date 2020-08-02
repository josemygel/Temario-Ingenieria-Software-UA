package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.MockType;
import org.easymock.internal.MocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaStubTest {
    //Creamos el Mock Controller en modo ESTRICTO (Basado en comportamiento sin relajar reglas)
    IMocksControl imc = new MocksControl(MockType.NICE);
    Reserva sut;
    FactoriaBOs stubFactoria;
    IOperacionBO stubOperacion;
    String login;
    String password;
    String socio;
    String[] isbns;
    String esperado;


    @BeforeEach
    void initState(){
        //Creamos el mock parcial (para controlar el acceso a sut.compruebaPermisos)
        imc.resetToNice();
        sut = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").createMock(imc);
        stubFactoria = partialMockBuilder(FactoriaBOs.class).addMockedMethod("getOperacionBO").createMock(imc);
        stubOperacion = imc.createMock(IOperacionBO.class);
        isbns = new String[]{};//reset isbsn
    }

    @Test
    void realizaReservaC1() {
        //Entradas
        login = password = "xxxx";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        esperado = "ERROR de permisos; ";

        //Programamos expectativas
        expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(false);
        //'Grabamos' y si es un StrictMock y las invocaciones del SUT no coinciden con las expectativas
        // programadas: (nº invocaciones, parámetros y orden), en cambio en un Mock no importa el orden!!
        imc.replay();
        //Invocamos al SUT
        ReservaException reserva = assertThrows(ReservaException.class,()->sut.realizaReserva(login,password,socio,isbns));
        //Informamos
        assertEquals(esperado, reserva.getMessage());
        //Verificamos y reseeteamos
        imc.verify();
    }

    @Test
    void realizaReservaC2() {
        //Entradas
        login = password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222", "33333"};
        //esperado;

        //Programamos expectativas
        expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true);
        stubFactoria.op = stubOperacion;

        //INYECTAMOS en la factoria el STUB programado


        //'Grabamos' y si es un StrictMock y las invocaciones del SUT no coinciden con las expectativas
        //assertDoesNotThrow(()->opStub.operacionReserva()).andVoid().times(1));
        // programadas: (nº invocaciones, parámetros y orden), en cambio en un Mock no importa el orden!!
        imc.replay();
        //Invocamos al SUT
        assertDoesNotThrow(()->sut.realizaReserva(login,password,socio,isbns));
        //Informamos
//        assertEquals(esperado, reserva.getMessage());
        //Verificamos y reseeteamos
        imc.verify();
    }

    @Test
    void realizaReservaC3() {
        //Entradas
        login = password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111"};
        esperado = "ISBN invalido:11111; ";

        //Programamos expectativas
        expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true);

        //Inyectamos en la factoria
        assertDoesNotThrow(()->{
            //Haremos que el mock no devuelva ninguna excepcion con las llamadas
            stubOperacion.operacionReserva(anyString(),anyString()); expectLastCall().andThrow(new IsbnInvalidoException());
        });
        stubFactoria.op = stubOperacion;


        //'Grabamos' y si es un StrictMock y las invocaciones del SUT no coinciden con las expectativas
        // programadas: (nº invocaciones, parámetros y orden), en cambio en un Mock no importa el orden!!
        imc.replay();
        //Invocamos al SUT
        ReservaException reserva = assertThrows(ReservaException.class,()->sut.realizaReserva(login,password,socio,isbns));
        //Informamos
        assertEquals(esperado, reserva.getMessage());
        //Verificamos y reseeteamos
        imc.verify();
    }

    @Test
    void realizaReservaC4() {
        //Entradas
        login = password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        esperado = "SOCIO invalido; ";

        //Programamos expectativas
        expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true);

        //Inyectamos en la factoria
        assertDoesNotThrow(()->{
            //Haremos que el mock no devuelva ninguna excepcion con las llamadas
            stubOperacion.operacionReserva(anyString(),anyString()); expectLastCall().andThrow(new SocioInvalidoException());
        });
        stubFactoria.op = stubOperacion;


        //'Grabamos' y si es un StrictMock y las invocaciones del SUT no coinciden con las expectativas
        // programadas: (nº invocaciones, parámetros y orden), en cambio en un Mock no importa el orden!!
        imc.replay();
        //Invocamos al SUT
        ReservaException reserva = assertThrows(ReservaException.class,()->sut.realizaReserva(login,password,socio,isbns));
        //Informamos
        assertEquals(esperado, reserva.getMessage());
        //Verificamos y reseeteamos
        imc.verify();
    }

    @Test
    void realizaReservaC5() {
        //Entradas
        login = password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        esperado = "CONEXION invalida; ";

        //Programamos expectativas
        expect(sut.compruebaPermisos(anyString(),anyString(),anyObject(Usuario.class))).andReturn(true);

        //Inyectamos en la factoria
        assertDoesNotThrow(()->{
            //Haremos que el mock no devuelva ninguna excepcion con las llamadas
            stubOperacion.operacionReserva(anyString(),anyString()); expectLastCall().andThrow(new JDBCException());
        });
        stubFactoria.op = stubOperacion;


        //'Grabamos' y si es un StrictMock y las invocaciones del SUT no coinciden con las expectativas
        // programadas: (nº invocaciones, parámetros y orden), en cambio en un Mock no importa el orden!!
        imc.replay();
        //Invocamos al SUT
        ReservaException reserva = assertThrows(ReservaException.class,()->sut.realizaReserva(login,password,socio,isbns));
        //Informamos
        assertEquals(esperado, reserva.getMessage());
        //Verificamos y reseeteamos
        imc.verify();
    }
}