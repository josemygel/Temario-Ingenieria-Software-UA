package ppss;

import java.util.Objects;

public class Tramo {
    int _origen;
    int _longitud;

    int getOrigen(){ return _origen; }
    void setOrigen(int o){ _origen=o; }

    int getLongitud(){ return _longitud; }
    void setLongitud(int l){ _longitud=l; }

    Tramo(){ _origen = _longitud = 0; }
    Tramo(int origen, int longitud){
        _origen = origen;
        _longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Tramo tramo = (Tramo) o;
        return _origen == tramo.getOrigen() &&
                _longitud == tramo.getLongitud();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_origen, _longitud);
    }
}
