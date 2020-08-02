package ppss.ejercicio1;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MultipathExampleTest {
    int salida, esperada, a, b, c;
    MultipathExample sut;


    private static Stream<Arguments> casosPruebaD2(){
        /**
         *
         - Caminos:
         D1 (T&T + T): 1.1 > 1.2 > 2 > 3 > 4 > 5
         - Ej.: a=7; b=4; c=6; r=17;
         D2 (T&F + F): 1.1 > 1.2 > 3 > 5
         - Ej.: a=7; b=7; c=5; r=5;
         D3 (F&F + F): 1.1 > 3 > 5
         - Ej.: a=5; b=5; c=5; r=5;
         * */
        return Stream.of(
                //Arguments.of(a,b,c,esperado)
                Arguments.of(7,4,6,17),
                Arguments.of(7,7,5,5),
                Arguments.of(5,5,5,5)
        );
    }

    @BeforeEach
    void init(){
        salida = esperada = a = b = c = 0;
        sut = new MultipathExample();
    }

    @Test
    @DisplayName("Apartado A1")
    void multiPath1_A1() {
        a=7;
        b=7;
        c=7;
        esperada = 21;

        salida = sut.multiPath1(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }
    /*
    @Test
    @DisplayName("Apartado A2")
    void multiPath1_A2() {
        a=5;
        b=5;
        c=5;
        esperada = 5;

        salida = sut.multiPath1(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }*/

    //@Test
    @DisplayName("Apartado C")
    void multiPath1_C() {
        a=7;
        b=7;
        c=7;
        esperada = 7;

        salida = sut.multiPath1(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }
/*
    @Test
    @DisplayName("Apartado D")
    void multiPath1_D1() {
        a=3;
        b=6;
        c=2;
        esperada = 8;

        salida = sut.multiPath1(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }

 */
    @DisplayName("Apartado D, funcion multiPath2 parametrizado")
    @ParameterizedTest(name = "a={0}, b={1}, c={2} => {3}")
    @MethodSource("casosPruebaD2")
    void multiPath2_D2(int a, int b, int c, int esperada) {

        salida = sut.multiPath2(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }

    private static Stream<Arguments> casosPruebaE(){
        /**
         *
         - Caminos:
         D1 (T&T + T): 1 > 2 > 3 > 4 > 5
         - Ej.: a=7; b=4; c=6; r=17;
         D2 (F&F + F): 1 > 3 > 5
         - Ej.: a=5; b=5; c=5; r=5;
         * */
        return Stream.of(
                //Arguments.of(a,b,c,esperado)
                Arguments.of(7,4,6,17),
                Arguments.of(5,5,5,5)
        );
    }

    @DisplayName("Apartado E, funcion multiPath2 parametrizado")
    @ParameterizedTest(name = "a={0}, b={1}, c={2} => {3}")
    @MethodSource("casosPruebaE")
    void multiPath3_E(int a, int b, int c, int esperada) {

        salida = sut.multiPath3(a,b,c);

        assertEquals(esperada,salida,"Comprobando salida esperada y real");
    }



}