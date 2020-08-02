package ppss.ejercicio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//DRIVER
@Tag("Ejercicio1")
@Tag("GestorLlamadas")
class GestorLlamadasTest {


    //@Test
    void templateTest() {
        //Preparar entradas + Crear e inyectar STUB
        //Invocar SUT
        //Verificar resultado
        //Restaurar los datos (si es necesario)
    }

    @Test
    @DisplayName("Ejercicio2_C1")
    void calculaConsumoC1() {
        //Preparar entradas + Crear e inyectar STUB
        int minutos = 10;
        int hora = 15;
        int esperado = 208;

        //Creamos e inyectamos el STUB
        CalendarioStub c = new CalendarioStub();
        c.setResult(hora);

        GestorLlamadasStub gls = new GestorLlamadasStub();
        gls.setCalendario(c); //Son las 15:00

        //Invocar SUT
        double resultado = gls.calculaConsumo(minutos); //obtenemos el resultado de pasar 10 minutos

        //Verificar resultado
        assertEquals(esperado, resultado);
    }

    @Test()
    @DisplayName("Ejercicio2_C2")
    void calculaConsumoC2() {
        //Preparar entradas + Crear e inyectar STUB
        int minutos = 10;
        int hora = 22;
        double esperado = 105;

        //Creamos e inyectamos el STUB
        CalendarioStub c = new CalendarioStub();
        c.setResult(hora);

        GestorLlamadasStub gls = new GestorLlamadasStub();
        gls.setCalendario(c); //Son las 15:00

        //Invocar SUT
        double resultado = gls.calculaConsumo(minutos); //obtenemos el resultado de pasar 10 minutos

        //Verificar resultado
        assertEquals(esperado, resultado, ()->minutos+" minutos a las "+hora+" esperaba "+esperado+" y fue "+resultado);
    }
}