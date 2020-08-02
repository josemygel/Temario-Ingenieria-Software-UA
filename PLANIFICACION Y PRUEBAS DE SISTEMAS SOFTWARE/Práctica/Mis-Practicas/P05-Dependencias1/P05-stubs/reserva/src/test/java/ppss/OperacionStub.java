package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionStub implements IOperacionBO {
    private String excepcion = "";
    public void setExcepcion(String excepcion) { this.excepcion = excepcion; }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(socio != "Luis")
            throw new SocioInvalidoException();

        switch (excepcion) {
            case "IsbnInvalido": throw new IsbnInvalidoException();
            case "JDBC": throw new JDBCException();
        }
    }
}
