package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {
    int[] coleccion;
    int[] coleccionEsperada;
    DataArray arr;
    DataArray arrEsperado;

    @Test
    public void testAddC1() {
        coleccion = new int[10];

        arr= new DataArray(coleccion,0);
        arr.add(1);

        assertEquals(arr.size(), 1);
        assertEquals(arr.getColeccion()[0], 1);
    }

    @Test
    public void testAddC2() {
        coleccion = new int[10];

        coleccion[0]=1;

        arr= new DataArray(coleccion,1);
        arr.add(2);

        assertEquals(arr.size(), 2);
        assertEquals(arr.getColeccion()[1], 2);
    }

    @Test
    public void testAddC3() {
        coleccion = new int[10];

        for(int i=0;i<10;i++)
            coleccion[i]=i+1;

        arr= new DataArray(coleccion,10);
        arr.add(2);

        assertEquals(arr.size(), 10);
        assertEquals(arr.getColeccion()[0], 1);
        assertEquals(arr.getColeccion()[9], 10);
    }

    @Test
    public void testDeleteC1() {
        coleccion = new int[10];
        coleccionEsperada = new int[10];
        coleccion[0] = coleccion[1] = coleccionEsperada[0] = 1;
        arr= new DataArray(coleccion,2);
        arrEsperado = new DataArray(coleccionEsperada,1);

        coleccion = arr.delete(1);

        assertEquals(arr.size(), arrEsperado.size());
        assertTrue(arr.equals(arrEsperado));
    }

    @Test
    public void testDeleteC2() {
        coleccion = new int[10];
        coleccionEsperada = new int[10];

        for(int i=0; i <= 5; i++)
            coleccion[i] = 4;

        arr= new DataArray(coleccion,6);
        arrEsperado = new DataArray(coleccionEsperada,0);

        for(int i=0; i <=5; i++)
            arr.delete(4);

        assertEquals(arr.size(), arrEsperado.size());
        assertTrue(arr.equals(arrEsperado));
    }

    @Test
    public void testDeleteC3() {
        coleccion = new int[10];
        coleccionEsperada = new int[10];
        coleccion[0] = coleccionEsperada [0] = 2;

        arr= new DataArray(coleccion,1);
        arrEsperado = new DataArray(coleccionEsperada,1);

        arr.delete(4);

        assertEquals(arr.size(), arrEsperado.size());
        assertTrue(arr.equals(arrEsperado));
    }
    @Test
    public void testDeleteC4() {
        coleccion = new int[10];

        for(int i=0; i <= 5; i++)
            coleccion[i] = 4;

        arr= new DataArray(coleccion,6);

        for(int i=0; i <= 4; i++)
            arr.delete(4);

        assertEquals(arr.size(), 1);
    }

}