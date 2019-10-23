package adaboost;

import java.util.SplittableRandom;

/**
 * <p>Hiperplano. Clasificador aleatorio basado en una n-tupla que contiene puntos y vectores y una constante.</p>
 * <p>Una recta es del tipo Ax + By - C = 0; un plano, Ax + By + Cz - D = 0;
 * un Hiperplano, A<sub>1</sub>x<sub>1</sub> + A<sub>2</sub>x<sub>2</sub> + A<sub>3</sub>x<sub>3</sub> + ... - C = 0</p>
 * <p>Gracias a esta distribución, podremos posicionar un hiperplano que recibamos
 * (los píxeles de la imagen) en un lado u otro en relación al nuestro.
 * Clasificaremos esa imagen en función de la posición en la que quede.</p>
 * @author pavel
 */
class Hyperplane {
    private static final int DIMM = 24 * 24; //Tamaño del array de píxeles de una imagen (24 ancho * 24 alto)
    private final double[] vectors = new double[DIMM]; //Componentes vector (x_i) del hiperplano
    private double constant = 0d; //Constante final
    private double error; //tasa de error del hiperplano
    double getError() { return error; }
    void setError(double error) { this.error = error; }

    /**
     * <p>Generamos todos los vectores aleatorios y actualizamos la constante como cada vector por su punto correspondiente.</p>
     * <p>No almacenamos los puntos porque luego no los usamos,
     * así que generamos cada punto aleatoriamente y lo multiplicamos directamente por el vector (que sí guardamos).</p>
     */
    Hyperplane() {
        SplittableRandom random = new SplittableRandom(); //Generador de números bastante más eficiente que Random
        for (int i = 0; i < DIMM; i++) {
            vectors[i] = random.nextDouble(Cara.NO_CARA, Cara.CARA);
            constant += vectors[i] * random.nextInt(Cara.MIN_RGB, Cara.MAX_RGB);
        }
    }

    /**
     * Calcula el valor (posición respecto a nosotros) de una imagen.
     * El signo del reusultado nos servirá para saber si es una cara o no.
     * @param pixels los píxeles de la imagen
     * @return el valor que tiene al pasarle el hiperplano
     */
    double evaluate(int[] pixels) {
        double evaluation = 0;
        for (int i = 0; i < DIMM; i++) evaluation += vectors[i] * pixels[i];
        return evaluation - constant;
    }
}
