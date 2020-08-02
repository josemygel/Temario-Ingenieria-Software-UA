package ppss;

import ppss.excepciones.*;
import java.util.ArrayList;

//paquete ppss
 public class Reserva {
     private IOperacionBO operacion;// = new Operacion(); así no asumimos la existencia de dicha clase
     public IOperacionBO getOperacion() { return operacion; }
     public void setOperacion(IOperacionBO operacion) { this.operacion = operacion; }

    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void realizaReserva(String login, String password,
                               String socio, String[] isbns) throws Exception {

        ArrayList<String> errores = new ArrayList<>();
        if (!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) {
            errores.add("ERROR de permisos");
        } else {
//            IOperacionBO io = new Operacion();
            IOperacionBO io = getOperacion();
            try {
                for (String isbn: isbns) {
                    try {
                        io.operacionReserva(socio, isbn);
                    } catch (IsbnInvalidoException iie) {
                        errores.add("ISBN invalido" + ":" + isbn);
                    }
                }
            } catch (SocioInvalidoException sie) {
                errores.add("SOCIO invalido");
            } catch (JDBCException je) {
                errores.add("CONEXION invalida");
            }
        }
        if (errores.size() > 0) {
            String mensajeError = "";
            for (String error : errores) {
                mensajeError += error + "; ";
            }
            throw new ReservaException(mensajeError);
        }
    }
}