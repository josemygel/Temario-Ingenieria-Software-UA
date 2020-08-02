package ppss;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioStub extends Calendario {
    ArrayList<String> festivos = new ArrayList<>();
    ArrayList<String> exceptions = new ArrayList<>();
   
    public void setFestivos (ArrayList<String> festivos) {
        this.festivos = festivos;
    }
   
    public void setExceptiones(ArrayList<String> exceptions) {
        this.exceptions = exceptions;
    }
    
    @Override
    public boolean es_festivo(LocalDate fecha) throws CalendarioException {
      if (exceptions.contains(fecha.toString())) {
          throw new CalendarioException();
      } else if (festivos.contains(fecha.toString())) {
               return true;
      } else return false;    
    }  
}
