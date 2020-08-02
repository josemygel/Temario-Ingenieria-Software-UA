package ppss;

public class ServicioStub implements IService {
    private float precio = 10.0f;

    @Override
    public float consultaPrecio(TipoCoche t) {
        return precio;
    }
}
