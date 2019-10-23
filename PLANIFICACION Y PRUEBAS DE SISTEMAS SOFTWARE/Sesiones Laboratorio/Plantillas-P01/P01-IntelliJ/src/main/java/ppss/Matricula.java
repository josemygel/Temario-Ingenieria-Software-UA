package ppss;

public class Matricula {
    public float calculaTasaMatricula(int edad, boolean familiaNumerosa,
                                      boolean repetidor) {
        float tasa = 500.00f;

        if ((edad < 25) && (!familiaNumerosa) && (repetidor)) {
            tasa = tasa + 1500.00f;
        } else {
            if ((familiaNumerosa) || (edad >= 65)) {
                tasa = tasa / 2;
            }
            if ((edad > 50) && (edad < 65)) {
                tasa = tasa - 100.00f;
            }
        }
        return tasa;
    }
}
