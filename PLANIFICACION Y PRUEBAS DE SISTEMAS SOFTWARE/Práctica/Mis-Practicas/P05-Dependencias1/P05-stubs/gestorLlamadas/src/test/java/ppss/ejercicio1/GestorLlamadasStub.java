package ppss.ejercicio1;

import java.util.Calendar;

public class GestorLlamadasStub extends GestorLlamadas{
    // DOC:     public int getHoraActual() { ... }
    // STUB
    private int result;

    public void setResult(int r) { result = r; }

    @Override
    public int getHoraActual() {
        return result;
    }

    // SUT:     public double calculaConsumo(int minutos) { ... }
}
