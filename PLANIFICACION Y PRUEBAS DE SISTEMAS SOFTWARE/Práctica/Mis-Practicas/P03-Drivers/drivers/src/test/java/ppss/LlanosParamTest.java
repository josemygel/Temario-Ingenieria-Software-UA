package ppss;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

@Tag("llanos")
class LlanosParamTest {
    Llanos llanos = new Llanos();

    //casosDePrueba del ejercicio 3
    private static Stream<Arguments> casosDePrueba(){
        //Funcionará con int[]?
        return Stream.of(
                Arguments.of(new int[]{3}, 0, 0),
                Arguments.of(new int[]{100, 100, 100, 100}, 0, 3),
                Arguments.of(new int[]{120, 140, 180, 180, 180}, 2, 2),
                Arguments.of(new int[]{-1}, 0, 0),
                Arguments.of(new int[]{-1,-1,-1,-1}, 0, 3),
                Arguments.of(new int[]{120, 140, -10, -10, -10}, 2, 2)
        );
    }

    @Tag("parametrizable")
    @ParameterizedTest(name = "Testeando tramo {0} para origen en {1} y longitud de {2}")
    @MethodSource("casosDePrueba")
    void buscarTramoLlanoMasLargo(int[] tramo, int origen_esperado, int longitud_esperada) {
        //Preparamos datos de entrada
        ArrayList<Integer> arr = new ArrayList<>();
        for (int item:tramo)
            arr.add(item);

        //Obtenemos resultado real
        Tramo esperado = llanos.buscarTramoLlanoMasLargo(arr);


        assertAll("Grupo test PARAMETRIZADO",
                ()->assertEquals(esperado.getLongitud(),longitud_esperada,"Comparando logintud"),
                ()->assertEquals(esperado.getOrigen(),origen_esperado, "Comparando origen"));
    }
}