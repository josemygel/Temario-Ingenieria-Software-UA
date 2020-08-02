package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculaConsumoTest {

    private TestableGestorLlamadas tg;

    @BeforeEach
    public void setUp()  {
        tg = new TestableGestorLlamadas();
    }

    @Test
    public void testC1()  {
        int minutos = 10;
        tg.setHora(15);
        assertEquals(208.0f, tg.calculaConsumo(minutos), 0.01);
    }

    @Test
    public void testC2()  {
        int minutos = 10;
        tg.setHora(22);
        assertEquals(105.0f, tg.calculaConsumo(minutos), 0.01);
    }
}
