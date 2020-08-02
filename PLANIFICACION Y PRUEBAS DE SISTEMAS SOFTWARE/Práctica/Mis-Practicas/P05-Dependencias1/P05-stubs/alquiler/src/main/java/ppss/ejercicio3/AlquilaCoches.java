package ppss.ejercicio3;

import java.time.LocalDate;

public class AlquilaCoches {
    protected Calendario calendario = new Calendario();
   // public void setCalendario(Calendario calendario) { this.calendario = calendario; }

    public IService getServicio() { return new Servicio(); } //Añadimos un SEAM, por el cual SI que podremos inyectar

    public Ticket calculaPrecio(TipoCoche tipo, LocalDate inicio, int ndias)
            throws MensajeException {
        Ticket ticket = new Ticket();
        float precioDia, precioTotal = 0.0f;
        float porcentaje = 0.25f;

        String observaciones = "";

//        IService servicio = new Servicio(); SUT NO TESTABLE
        IService servicio = getServicio(); //SEAM => SUT TESTABLE
        precioDia = servicio.consultaPrecio(tipo);
        for (int i = 0; i < ndias; i++) {
            LocalDate otroDia = inicio.plusDays((long) i);
            try {
                if (calendario.es_festivo(otroDia)) {
                    precioTotal += (1 + porcentaje) * precioDia;
                } else {
                    precioTotal += (1 - porcentaje) * precioDia;
                }
            } catch (CalendarioException ex) {
                observaciones += "Error en dia: " + otroDia + "; ";
            }
        }

        if (observaciones.length() > 0) {
            throw new MensajeException(observaciones);
        }

        ticket.setPrecio_final(precioTotal);
        return ticket;
    }
}