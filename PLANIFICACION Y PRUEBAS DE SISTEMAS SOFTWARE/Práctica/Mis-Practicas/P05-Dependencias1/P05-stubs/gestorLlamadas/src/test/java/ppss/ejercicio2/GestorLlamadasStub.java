package ppss.ejercicio2;

public class GestorLlamadasStub extends GestorLlamadas{
    // DOC:     public int getHoraActual() { ... }
    // STUB
    private CalendarioStub calendario;

    public void setCalendario(CalendarioStub cs) { calendario=cs; }

    @Override
    public Calendario getCalendario() { return calendario; }

    // SUT:     public double calculaConsumo(int minutos) { ... }
}
