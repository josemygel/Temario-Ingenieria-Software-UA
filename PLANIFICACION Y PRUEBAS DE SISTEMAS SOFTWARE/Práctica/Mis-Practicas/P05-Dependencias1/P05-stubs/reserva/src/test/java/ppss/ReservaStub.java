package ppss;

public class ReservaStub extends Reserva {

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return login == "ppss" && password == "ppss" && tipoUsu == Usuario.BIBLIOTECARIO;
    }
}
