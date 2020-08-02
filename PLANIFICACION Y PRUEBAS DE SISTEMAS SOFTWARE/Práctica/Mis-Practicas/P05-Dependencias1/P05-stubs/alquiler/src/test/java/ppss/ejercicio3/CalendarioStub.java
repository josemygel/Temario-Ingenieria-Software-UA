package ppss.ejercicio3;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioStub extends Calendario {

    private ArrayList<LocalDate> festivos = new ArrayList<>();
    private ArrayList<LocalDate> errores = new ArrayList<>();

    public void setFestivos(ArrayList<LocalDate> festivos) { this.festivos = festivos; }
    public void setErrores(ArrayList<LocalDate> errores) { this.errores = errores; }
    public ArrayList<LocalDate> getErrores() { return errores; }
    public ArrayList<LocalDate> getFestivos() { return festivos; }

    @Override
    public boolean es_festivo(LocalDate dia) throws CalendarioException {
        if(errores.contains(dia))
            throw new CalendarioException();
        return festivos.contains(dia);
    }
}
