package ppss.ejercicio2;

import ppss.ejercicio2.Calendario;

public class CalendarioStub extends Calendario {
    private int result;
    public void setResult(int r) { result = r; }

    @Override
    public int getHoraActual() { return result; }
}
