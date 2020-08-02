package ppss.ejercicio3;

public class ServicioStub implements IService{
    float price;
    ServicioStub(float p){ price = p; }

    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return price; //Asumimos que todos los coches (CARAVANA/TURISMO) vale 10 segun el enunciado
    }
}
