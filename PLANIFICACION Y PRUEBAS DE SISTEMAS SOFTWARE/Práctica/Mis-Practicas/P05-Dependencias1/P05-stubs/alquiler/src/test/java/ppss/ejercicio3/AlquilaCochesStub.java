package ppss.ejercicio3;

public class AlquilaCochesStub extends AlquilaCoches{
    IService servicio;
    public void setServicio(IService servicio) { this.servicio = servicio; }

    @Override
    public IService getServicio() { return servicio; }
}
