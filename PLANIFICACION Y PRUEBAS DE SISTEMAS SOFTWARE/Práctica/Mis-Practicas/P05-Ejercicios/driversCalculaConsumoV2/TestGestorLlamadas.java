package ppss.ejercicio2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestGestorLlamadas {
    TestableGestorLlamadas testableGestorLlamadas;
    CalendarioStub stubCalendario;
    
    @BeforeEach
    public void setUp() {
        testableGestorLlamadas = new TestableGestorLlamadas();
        stubCalendario = new CalendarioStub();
    }

    @Test
    public void testC1CalculaConsumo() {
        stubCalendario.setHora(15);
        testableGestorLlamadas.setCalendario(stubCalendario);
        assertEquals(208.0, testableGestorLlamadas.calculaConsumo(10),0.01);
    }
    
    @Test
    public void testC2CalculaConsumo() {
        stubCalendario.setHora(22);
        testableGestorLlamadas.setCalendario(stubCalendario);
        assertEquals(105, testableGestorLlamadas.calculaConsumo(10),0.01);
    }
}
