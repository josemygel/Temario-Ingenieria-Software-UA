package ppss;

public class TestableRealizaReserva extends Reserva {

    private final String loginValido = AsuncionesTablaRealizaReserva.loginValido;
    private final String passwordValido = AsuncionesTablaRealizaReserva.passwordValido;

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return loginValido.equals(login) && passwordValido.equals(password);
    }
}
