package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.internal.MockBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorLlamadasTest {
    IMocksControl imc;
    GestorLlamadas partialSut;
    Calendario mCalendario;

    @BeforeEach
    void createControl(){
        //CREATE STRICT CONTROL (STRICTS MOCKS!)
        imc = EasyMock.createStrictControl();
        mCalendario = imc.createMock(Calendario.class);
        partialSut = partialMockBuilder(GestorLlamadas.class)
                .addMockedMethod("getCalendario").createMock(imc);
    }

    //BASADO EN COMPORTAMIENTO!
    @Test
    @DisplayName("P06_E1_C1")
    void calculaConsumoC1() {
        //PREPARAMOS LAS ENTRADAS (INPUTS)
        int iMinutos = 22;
        int iHora = 10;
        double resultado = 457.6;

        //DECLARAMOS ESPECTATIVAS (Si el orden no se cumple, falla)
            // Primero se llama a getCalendario() que devuelve el mock de Calendario
        expect(partialSut.getCalendario()).andReturn(mCalendario);
            // Seguidamente se llama a getCalendario() del mock devuelto
        expect(mCalendario.getHoraActual()).andReturn(iHora);

        //Grabamos
        imc.replay();

        //Invocamos
        double real = partialSut.calculaConsumo(iMinutos);

        //Comapramos
        assertEquals(resultado, real);

        //Verificamos
        imc.verify();
    }

    @Test
    @DisplayName("P06_E1_C2")
    void calculaConsumoC2() {
        //PREPARAMOS LAS ENTRADAS (INPUTS) Y SALIDA (OUTPUT) ESPERADA
        int iMinutos = 13;
        int iHora = 21;
        double resultado = 136.5;

        //DECLARAMOS ESPECTATIVAS (Si el orden no se cumple, falla)
        // Primero se llama a getCalendario() que devuelve el mock de Calendario
        expect(partialSut.getCalendario()).andReturn(mCalendario);
        // Seguidamente se llama a getCalendario() del mock devuelto
        expect(mCalendario.getHoraActual()).andReturn(iHora);

        //Grabamos
        imc.replay();

        //Invocamos
        double real = partialSut.calculaConsumo(iMinutos);

        //Comapramos
        assertEquals(resultado, real);

        //Verificamos
        imc.verify();
    }
}