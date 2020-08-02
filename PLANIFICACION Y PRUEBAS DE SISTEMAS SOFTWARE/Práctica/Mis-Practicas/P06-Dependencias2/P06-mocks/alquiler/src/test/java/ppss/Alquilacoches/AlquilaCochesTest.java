package ppss.Alquilacoches;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.MockType;
import org.easymock.cglib.core.Local;
import org.easymock.internal.MocksControl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.Alquilacoches.excepciones.CalendarioException;
import ppss.Alquilacoches.excepciones.MensajeException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    IMocksControl imc = new MocksControl(MockType.STRICT);
    AlquilaCoches sut;          //SUT A PROBAR
    float precioPorDia = 10;    //Segun el enunciado asumimos siempre un valor de 10
    ArrayList<LocalDate> festivos;
    ArrayList<LocalDate> errores;
    int dias;
    String salida;
    Ticket esperado;
    Ticket real;
    LocalDate inicio;

    @BeforeEach
    void init(){
//        sut = partialMockBuilder(AlquilaCoches.class).createMock(imc);
        sut = new AlquilaCoches();
        sut.calendario = imc.createMock(Calendario.class);
        sut.servicio = imc.createMock(Servicio.class);
        festivos = new ArrayList<>();
        errores = new ArrayList<>();
        inicio = null;
        esperado = new Ticket();
        real = new Ticket();
    }

    @Test
    void calculaPrecioC1() {
        //I/O DIRECTAS
        esperado.setPrecio_final(75);
        TipoCoche tipo = TipoCoche.TURISMO;
        dias = 10;
        inicio = LocalDate.of(2020, 5, 18);


        //I/O INDIRECTAS Y ESPECTATIVAS
        expect(sut.servicio.consultaPrecio(TipoCoche.TURISMO)).andReturn(precioPorDia).times(1);


        //NUNCA USAR TRY-CATCH
        assertDoesNotThrow(()-> { //En estas lineas NO debe saltar una excepcion
            for (long i = 0; inicio.plusDays(i).compareTo(inicio.plusDays(dias)) < 0; i++) {
                if (errores.contains(inicio.plusDays(i))) {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andThrow(new CalendarioException());//.times(1);
                } else {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andReturn(festivos.contains(inicio.plusDays(i)));//.times(1);
                }
            }
        });

        //Grabamos
        imc.replay();
        //Invocamos
        real = assertDoesNotThrow(()-> sut.calculaPrecio(tipo,inicio,dias));
        //Comprobamos
        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
        imc.verify();
    }


    @Test
    void calculaPrecioC2() {
        //I/O DIRECTAS
        esperado.setPrecio_final(62.5f);
        TipoCoche tipo = TipoCoche.CARAVANA;
        dias = 7;
        inicio = LocalDate.of(2020, 6, 19);


        //I/O INDIRECTAS Y ESPECTATIVAS
        expect(sut.servicio.consultaPrecio(tipo)).andReturn(precioPorDia).times(1);

        festivos.add(LocalDate.of(2020,6,20));
        festivos.add(LocalDate.of(2020,6,24));



        //NUNCA USAR TRY-CATCH
        assertDoesNotThrow(()-> { //En estas lineas NO debe saltar una excepcion
            for (long i = 0; inicio.plusDays(i).compareTo(inicio.plusDays(dias)) < 0; i++) {
                if (errores.contains(inicio.plusDays(i))) {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andThrow(new CalendarioException());//.times(1);
                } else {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andReturn(festivos.contains(inicio.plusDays(i)));//.times(1);
                }
            }
        });

        //Grabamos
        imc.replay();
        //Invocamos
        real = assertDoesNotThrow(()-> sut.calculaPrecio(tipo,inicio,dias));
        //Comprobamos
        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
        imc.verify();
    }

    @Test
    void calculaPrecioC3() {
        //I/O DIRECTAS
        salida  ="Error en dia: 2020-04-18; Error en dia: 2020-04-21; Error en dia: 2020-04-22; ";
        TipoCoche tipo = TipoCoche.TURISMO;
        dias = 7;
        inicio = LocalDate.of(2020, 4, 17);


        //I/O INDIRECTAS Y ESPECTATIVAS
        expect(sut.servicio.consultaPrecio(tipo)).andReturn(precioPorDia).times(1);

        errores.add(LocalDate.of(2020,4,18));
        errores.add(LocalDate.of(2020,4,21));
        errores.add(LocalDate.of(2020,4,22));

        //NUNCA USAR TRY-CATCH
        assertDoesNotThrow(()-> { //En estas lineas NO debe saltar una excepcion
            for (long i = 0; inicio.plusDays(i).compareTo(inicio.plusDays(dias)) < 0; i++) {
                if (errores.contains(inicio.plusDays(i))) {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andThrow(new CalendarioException());//.times(1);
                } else {
                    expect(sut.calendario.es_festivo(inicio.plusDays(i))).andReturn(festivos.contains(inicio.plusDays(i)));//.times(1);
                }
            }
        });

        //Grabamos
        imc.replay();
        //Invocamos
        Throwable exception = assertThrows(MensajeException.class, ()->sut.calculaPrecio(tipo,inicio,dias));
        //Comprobamos
        assertEquals(salida, exception.getMessage());
        imc.verify();
    }
}