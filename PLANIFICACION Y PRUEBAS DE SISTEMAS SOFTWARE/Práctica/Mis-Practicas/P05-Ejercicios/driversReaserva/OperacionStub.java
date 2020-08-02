package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.ArrayList;

import static ppss.AsuncionesTablaRealizaReserva.*;

public class OperacionStub implements IOperacionBO {

    private Boolean accesoBd = true;
    private ArrayList<String> sociosValidos = new ArrayList<>();
    private ArrayList<String> isbnsValidos = new ArrayList<>();

    public OperacionStub() {

        this.sociosValidos.add(socioValido);
        this.isbnsValidos.add(isbnValido1);
        this.isbnsValidos.add(isbnValido2);
    }

    public void setAccesoBd(Boolean accesoBd) {
        this.accesoBd = accesoBd;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if (!this.accesoBd) {
            throw new JDBCException();
        } else if (!sociosValidos.contains(socio)) {
            throw new SocioInvalidoException();
        } else if (!isbnsValidos.contains(isbn)) {
            throw new IsbnInvalidoException();
        }
    }

}

