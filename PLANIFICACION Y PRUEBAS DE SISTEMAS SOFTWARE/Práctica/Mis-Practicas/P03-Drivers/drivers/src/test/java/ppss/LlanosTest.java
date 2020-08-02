package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Tag("llanos")
class LlanosTest {
    Llanos llanos = new Llanos();
    ArrayList<Integer> lecturas;
    Tramo esperado;
    Tramo real;

    @BeforeEach
    void resetearDatos(){ lecturas = new ArrayList<>(); }

    @Tag("A")
    @Test
    void C1A_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(3);

        //Creamos resultado espreado
        esperado = new Tramo(0,0);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertEquals(esperado,real);
    }

    @Tag("A")
    @Test
    void C2A_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(100);
        lecturas.add(100);
        lecturas.add(100);
        lecturas.add(100);

        //Creamos resultado espreado
        esperado = new Tramo(0,3);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertAll("Grupo Test C2A",
                ()->assertEquals(esperado.getLongitud(),real.getLongitud(),"Comparando logintud"),
                ()->assertEquals(esperado.getOrigen(),real.getOrigen(), "Comparando origen"),
                ()->assertEquals(esperado,real));
    }

    @Tag("A")
    @Test
    void C3A_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(120);
        lecturas.add(140);
        lecturas.add(180);
        lecturas.add(180);
        lecturas.add(180);

        //Creamos resultado espreado
        esperado = new Tramo(2,2);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertAll("Grupo Test C3A",
                ()->assertEquals(esperado.getLongitud(),real.getLongitud(),"Comparando logintud"),
                ()->assertEquals(esperado.getOrigen(),real.getOrigen(), "Comparando origen"),
                ()->assertEquals(esperado,real));
    }

    @Tag("B")
    @Test
    void C1B_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(-1);

        //Creamos resultado espreado
        esperado = new Tramo(0,0);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertAll("Grupo Test C1B",
                ()->assertEquals(esperado.getLongitud(),real.getLongitud(),"Comparando logintud"),
                ()->assertEquals(esperado.getOrigen(),real.getOrigen(), "Comparando origen"),
                ()->assertEquals(esperado,real));
    }

    @Tag("B")
    @Test
    void C2B_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);

        //Creamos resultado espreado
        esperado = new Tramo(0,3);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertAll("Grupo Test C2B",
                ()->assertEquals(esperado.getLongitud(),real.getLongitud(),"Comparando logintud"),
                ()->assertEquals(esperado.getOrigen(),real.getOrigen(), "Comparando origen"),
                ()->assertEquals(esperado,real));
    }

    @Tag("B")
    @Test
    void C3B_buscarTramoLlanoMasLargo() {
        //Preparamos datos de entrada
        lecturas.add(120);
        lecturas.add(140);
        lecturas.add(-10);
        lecturas.add(-10);
        lecturas.add(-10);

        //Creamos resultado espreado
        esperado = new Tramo(2,2);

        //Obtenemos el resultado real
        real = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertEquals(esperado,real);
    }
}