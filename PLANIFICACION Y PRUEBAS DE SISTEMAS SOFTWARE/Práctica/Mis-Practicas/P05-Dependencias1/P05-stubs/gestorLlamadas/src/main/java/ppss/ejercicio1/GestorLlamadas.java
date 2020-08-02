package ppss.ejercicio1;

import java.util.Calendar;

public class GestorLlamadas {
 static double TARIFA_NOCTURNA=10.5;
 static double TARIFA_DIURNA=20.8;

 public int getHoraActual() {
         Calendar c = Calendar.getInstance();
         int hora = c.get(Calendar.HOUR);
         return hora;
         }

         public double calculaConsumo(int minutos) {
         int hora = getHoraActual();
         if(hora < 8 || hora > 20) {
             return minutos * TARIFA_NOCTURNA;
             } else {
             return minutos * TARIFA_DIURNA;
             }
         }
}