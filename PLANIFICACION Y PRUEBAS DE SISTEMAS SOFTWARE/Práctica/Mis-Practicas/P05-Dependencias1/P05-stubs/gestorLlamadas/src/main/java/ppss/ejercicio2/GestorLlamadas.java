package ppss.ejercicio2;

public class GestorLlamadas {
 static double TARIFA_NOCTURNA=10.5;
 static double TARIFA_DIURNA=20.8;

 public Calendario getCalendario() {
     Calendario c = new Calendario();
     return c;
 }

 public int getHoraActual() {
         Calendario c = getCalendario();
         int hora = c.getHoraActual();
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