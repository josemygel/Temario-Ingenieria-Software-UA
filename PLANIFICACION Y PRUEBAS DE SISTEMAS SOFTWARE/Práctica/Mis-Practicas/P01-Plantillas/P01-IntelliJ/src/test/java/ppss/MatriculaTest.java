package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {
    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float resultadoReal, resultadoEsperado;
    Matricula mat= new Matricula();

    @Test
    public void testCalculaTasaMatricula() {
        edad = 23;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatriculaC1() {
        edad = 24;
        familiaNumerosa = true;
        repetidor = false;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatriculaC2() {
        edad = 23;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 2000.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatriculaC3() {
        edad = 33;
        familiaNumerosa = true;
        repetidor = false;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatriculaC4() {
        edad = 55;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 400.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatriculaC5() {
        edad = 195;
        familiaNumerosa = false;
        repetidor = false;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }
}
