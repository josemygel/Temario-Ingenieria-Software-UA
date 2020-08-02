package ppss.ejercicio2;

public class TestableGestorLlamadas extends GestorLlamadas {
    Calendario stubCalendario;
    
    public void setCalendario (Calendario calendario) {
        this.stubCalendario = calendario;
    }
    
    @Override
    public Calendario getCalendario() {
        return stubCalendario;
    }
}
