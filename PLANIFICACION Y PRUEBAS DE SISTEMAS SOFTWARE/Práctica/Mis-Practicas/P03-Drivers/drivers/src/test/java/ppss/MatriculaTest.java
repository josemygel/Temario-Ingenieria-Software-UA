package ppss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Tag("matricula")
class MatriculaTest {
    Matricula m = new Matricula();
    int edad;
    boolean famNum;
    boolean repite;
    float tasa;
    float resultado;

    @Test
    void C1_calculaTasaMatriculaTest() {
        edad = 19;
        famNum = false;
        repite = true;
        tasa = 2000;

        resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }

    @Test
    void C2_calculaTasaMatriculaTest() {
        edad = 68;
        famNum = false;
        repite = true;
        tasa = 250;

        resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }

    @Test
    void C3_calculaTasaMatriculaTest() {
        edad = 19;
        famNum = true;
        repite = true;
        tasa = 250;

        resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }

    @Test
    void C4_calculaTasaMatriculaTest() {
        edad = 19;
        famNum = false;
        repite = false;
        tasa = 500;

        resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }

    @Test
    void C5_calculaTasaMatriculaTest() {
        edad = 61;
        famNum = false;
        repite = false;
        tasa = 400;

        resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }



    //los valores devueltos por este método son los argumentos
    //del método anotado con @ParemeterizedTest
    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(19, false, true, 2000),
                Arguments.of(68, false, true, 250),
                Arguments.of(19, true, true, 250),
                Arguments.of(19, false, false, 500),
                Arguments.of(61, false, false, 400),
                Arguments.of(60, true, true, 400)
        );
    }

    //método que construye y devuelve un mensaje de error en caso
    //de que el resultado esperado no coincida con el real
    private String genFailMsg(int edad, boolean famNum, boolean repite, float esperado, float tasa) {
        String message = "Persona con " + edad + " años, ";
        message += famNum ? "con" : "sin";
        message += " familia numerosa y ";
        message += repite ? "repitiendo" : "sin repetir";
        message += " paga " + tasa + " en lugar de " + esperado;
        return message;
    }

    @Tag("parametrizable")
    @ParameterizedTest(name="Persona de {0} años, {1} familia numerosa y {2} repite curso, pagaría {3}")
    @MethodSource("casosDePrueba")
    void calculaTasaMatriculaParameterizedTest(int edad, boolean famNum, boolean repite, float tasa) {
        Matricula m = new Matricula();

        float resultado = m.calculaTasaMatricula(edad, famNum, repite);

        assertEquals(tasa, resultado, ()->genFailMsg(edad,famNum,repite,tasa,resultado));
    }
}