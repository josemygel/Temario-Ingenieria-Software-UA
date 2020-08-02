package ppss;

public class FactoriaOperaciones {
    private static IOperacionBO operacion;

    public static IOperacionBO Create() {
        if (operacion != null)
            return operacion;
        else
            return new Operacion();
    }

    public static void setOperacion(IOperacionBO operacion) {

        FactoriaOperaciones.operacion = operacion;
    }
}
