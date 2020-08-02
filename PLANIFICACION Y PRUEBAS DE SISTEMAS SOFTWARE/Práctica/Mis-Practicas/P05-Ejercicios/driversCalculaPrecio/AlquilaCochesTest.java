package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AlquilaCochesTest {
    ServicioStub servicioStub;
    CalendarioStub calendarioStub;

    @BeforeEach
    public void setup() {
        //Creamos el doble de servicio
        servicioStub = new ServicioStub();
        //Creamos el doble de calendario
        calendarioStub = new CalendarioStub();
    }

    //Caso de prueba sin dias festivos y sin excepciones
    @Test
    public void testCalculaPrecioC1()  {
        TipoCoche tipo = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2020, Month.MAY, 18);
        int ndias = 10;
        float esperado = 75f;

        //inyectamos los dobles
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.calendario=calendarioStub;
        
        Ticket ticket= assertDoesNotThrow(
                ()-> sut.calculaPrecio(tipo, inicio, ndias)
        );
        
        assertEquals(esperado, ticket.getPrecio_final(),0.02f);
    }

    //Caso de prueba CON dos festivos, y sin excepciones
    @Test
    public void testCalculaPrecioC2()  {
        TipoCoche tipo = TipoCoche.CARAVANA;
        LocalDate inicio = LocalDate.of(2020, Month.JUNE, 19);
        int ndias = 7;
        float esperado = 62.5f;

        //Programamos el calendario
        calendarioStub.setFestivos(new ArrayList<>(Arrays.asList("2020-06-20","2020-06-24")));

        //inyectamos los dobles
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.calendario=calendarioStub;

        Ticket ticket = assertDoesNotThrow(
                ()->sut.calculaPrecio(tipo, inicio, ndias)
        );

        assertEquals(esperado, ticket.getPrecio_final(),0.02f);
    }

    //Caso de prueba SIN festivos, y con excepciones
    @Test
    public void testCalculaPrecioC3() {
        TipoCoche tipo = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2020, Month.APRIL, 17);
        int ndias = 8;
        String observaciones_esperadas = "Error en dia: 2020-04-18; " +
                                         "Error en dia: 2020-04-21; " +
                                         "Error en dia: 2020-04-22; ";

        //Creamos el doble de servicio
        ServicioStub servicioStub = new ServicioStub();

        //Programamos el calendario
        calendarioStub.setExceptiones(new ArrayList<>(Arrays.asList("2020-04-18","2020-04-21", "2020-04-22")));

        //inyectamos los dobles
        AlquilaCochesTestable sut = new AlquilaCochesTestable(servicioStub);
        sut.calendario=calendarioStub;

        MensajeException ex = assertThrows(MensajeException.class,
                ()-> sut.calculaPrecio(tipo, inicio, ndias));

        assertEquals(observaciones_esperadas,ex.getMessage());
    }
}
