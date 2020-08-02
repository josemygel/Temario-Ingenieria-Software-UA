package ppss.ejercicio3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {

    //@Test
    void templateTest() {
        //Preparar entradas + Crear e inyectar STUB
        //Invocar SUT
        //Verificar resultado
        //Restaurar los datos (si es necesario)
    }

    @Test
    @DisplayName("Calcula Precio C1")
    void calculaPrecio_C1() throws MensajeException {
        //Preparamos datos de entrada
        TipoCoche tipo = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2020, Month.MAY,18);
        int days = 10;
        float esperado = 75;

        //CREAMOS E INYECTAMOS LOS STUB
        ArrayList<LocalDate> festivos = new ArrayList<>();
        ArrayList<LocalDate> errores = new ArrayList<>();
        CalendarioStub calendario = new CalendarioStub(); //INYECTAMOS CALENDARIO
        calendario.setFestivos(festivos); //Incluimos los festivos
        calendario.setErrores(errores); //Incluimos los errores

        //AlquilaCoches ac = new AlquilaCoches(); Bastaria con esta clase si SOLO dependiera de Calendario
        AlquilaCochesStub ac = new AlquilaCochesStub(); // Se usa esta para introducir el DOC de IService
        ac.setServicio(new ServicioStub(10)); //Inyectamos el Servicio (todo 10€)
        ac.calendario = calendario; //Inyectamos el calendario previamente definido


        //Invocamos SUT (y obtenemos resultado)
        Ticket resultado = ac.calculaPrecio(tipo, inicio, days);

        //Verificamos resultados
        assertEquals(esperado, resultado.getPrecio_final());
    }

    @Test
    @DisplayName("Calcula Precio C2")
    void calculaPrecio_C2() throws MensajeException {
        //Preparamos datos de entrada
        TipoCoche tipo = TipoCoche.CARAVANA;
        LocalDate inicio = LocalDate.of(2020, Month.JUNE,19);
        int days = 7;
        float esperado = 62.5f;

        //CREAMOS E INYECTAMOS LOS STUB
        ArrayList<LocalDate> festivos = new ArrayList<>();
        festivos.add(LocalDate.of(2020, Month.JUNE, 20));
        festivos.add(LocalDate.of(2020, Month.JUNE, 24));

        ArrayList<LocalDate> errores = new ArrayList<>();
        CalendarioStub calendario = new CalendarioStub(); //INYECTAMOS CALENDARIO
        calendario.setFestivos(festivos); //Incluimos los festivos
        calendario.setErrores(errores); //Incluimos los errores

        //AlquilaCoches ac = new AlquilaCoches(); Bastaria con esta clase si SOLO dependiera de Calendario
        AlquilaCochesStub ac = new AlquilaCochesStub(); // Se usa esta para introducir el DOC de IService
        ac.setServicio(new ServicioStub(10)); //Inyectamos el Servicio (todo 10€)
        ac.calendario = calendario; //Inyectamos el calendario previamente definido


        //Invocamos SUT (y obtenemos resultado)
        Ticket resultado = ac.calculaPrecio(tipo, inicio, days);

        //Verificamos resultados
        assertEquals(esperado, resultado.getPrecio_final());
    }

    @Test
    @DisplayName("Calcula Precio C3")
    void calculaPrecio_C3() throws MensajeException {
        //Preparamos datos de entrada
        TipoCoche tipo = TipoCoche.TURISMO;
        LocalDate inicio = LocalDate.of(2020, Month.APRIL,17);
        int days = 8;
        String esperado = "Error en dia: 2020-04-18; Error en dia: 2020-04-21; Error en dia: 2020-04-22; ";


        //CREAMOS E INYECTAMOS LOS STUB
        ArrayList<LocalDate> festivos = new ArrayList<>();
        ArrayList<LocalDate> errores = new ArrayList<>();
        errores.add(LocalDate.of(2020, Month.APRIL,18));
        errores.add(LocalDate.of(2020, Month.APRIL,21));
        errores.add(LocalDate.of(2020, Month.APRIL,22));

        CalendarioStub calendario = new CalendarioStub(); //INYECTAMOS CALENDARIO
        calendario.setFestivos(festivos); //Incluimos los festivos
        calendario.setErrores(errores); //Incluimos los errores

        //AlquilaCoches ac = new AlquilaCoches(); Bastaria con esta clase si SOLO dependiera de Calendario
        AlquilaCochesStub ac = new AlquilaCochesStub(); // Se usa esta para introducir el DOC de IService
        ac.setServicio(new ServicioStub(10)); //Inyectamos el Servicio (todo 10€)
        ac.calendario = calendario; //Inyectamos el calendario previamente definido


        //Invocamos SUT (y obtenemos resultado)
        //si sut() lanza la excepción de tipo MensajeException
        //asignamos la excepción a la variable "exception"
        Throwable exception = assertThrows(MensajeException.class,
                () -> ac.calculaPrecio(tipo, inicio, days));

        //Verificamos resultados
        assertEquals(esperado, exception.getMessage());
    }
}