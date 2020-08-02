package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {

    private static Stream<Arguments> casosDePrueba(){
        return Stream.of(
                //Entrada(coleccion([]int),numElems(int),aBorrar(int))
                //Resultado(coleccion y resultado([]int),numElems(int))
                Arguments.of(new int[]{1,3,5,7,0,0,0,0,0,0}, 4, 5, new int[]{1,3,7,0,0,0,0,0,0,0}, 3),
                Arguments.of(new int[]{1,3,3,5,7,0,0,0,0,0}, 5, 3, new int[]{1,3,5,7,0,0,0,0,0,0}, 4),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 10, 4, new int[]{1,2,3,5,6,7,8,9,10,0}, 9)
        );
    }

    private static Stream<Arguments> casosDePruebaExcepcionales(){
        return Stream.of(
                //Ponemos el mensaje de la excepcion a la derecha
                Arguments.of(new int[]{0,0,0,0,0,0,0,0,0,0}, 0, 8, "No hay elementos en la colección"),
                Arguments.of(new int[]{1,3,5,7,0,0,0,0,0,0}, 4, -5, "El valor a borrar debe ser > cero"),
                Arguments.of(new int[]{0,0,0,0,0,0,0,0,0,0}, 0, 0, "Colección vacía. Y el valor a borrar debe ser > cero"),
                Arguments.of(new int[]{1,3,5,7,0,0,0,0,0,0}, 4, 8, "Elemento no encontrado")
        );
    }

    @ParameterizedTest(name = "Eliminar {1} a la coleccion {0}")
    @MethodSource("casosDePrueba")
    void deleteTest(int[] coleccion, int cantidad, int elemento, int[] coleccionEsperada, int cantidadEsperada) throws DataArray.DataException {
        DataArray data = new DataArray(coleccion,cantidad);

        //OBtenemos salida real del método
        int[] salida = data.delete(elemento);

        assertAll(
                ()->assertArrayEquals(coleccion,coleccionEsperada),
                ()->assertArrayEquals(data.getColeccion(),coleccionEsperada),
                ()->assertArrayEquals(salida, coleccionEsperada),
                ()->assertEquals(data.size(),cantidadEsperada)
        );
    }

    @Tag("excepcional")
    @ParameterizedTest(name = "Eliminar {1} a la coleccion {0} lanza excepción ({3})")
    @MethodSource("casosDePruebaExcepcionales")
    void exceptionDeleteTest(int[] coleccion, int cantidad, int elemento, String excepcion) throws DataArray.DataException {
        DataArray data = new DataArray(coleccion, cantidad);

        //asignamos la excepción a la variable "exception"
        Throwable exception = assertThrows(DataArray.DataException.class, () -> data.delete(elemento));

        //mostramos el mensaje asociado a la excepción
        assertEquals(excepcion, exception.getMessage());
        assertArrayEquals(coleccion,data.getColeccion());
    }
}