package adaboost;

import java.util.ArrayList;

/**
 * <p>Clasificador simple. Genera una cierta cantidad de hiperplanos y se queda con aquel que más imágenes del conjunto
 * de aprendizaje haya acertado.</p>
 * <p>Una vez se haya entrenado el clasificador, será capaz de clasificar una imagen en cara o no
 * dependiendo del signo obtenido al evaluar la misma con el mejor hiperplano generado</p>
 * <p>La precisión de este clasificador depende de la cantidad de hiperplanos generados, aunque tampoco hace magia</p>
 * @author pavel
 */
class WeakLearner {
    private Hyperplane best; //El hiperplano con menor error obtenido
    Hyperplane best(){ return best; }

    private double confidence; //Confianza del clasificador -> Útil sólo para el clasificador fuerte
    double getConfidence() { return confidence; }

    /**
     * Obtiene un clasificador débil a partir de la generación de n hiperplanos, de los cuales se queda con el de menor error
     * @param n cantidad de hiperplanos que se generán
     * @param faces el conjunto de aprendizaje
     * @see Hyperplane#Hyperplane()
     */
    WeakLearner(int n, ArrayList<Cara> faces) {
        for (int i = 0; i < n; i++) {
            Hyperplane h = new Hyperplane(); //Genero un nuevo hiperplano aleatorio
            double error = 0d; //Le sumo a error el peso de cada cara que falle del conjunto de aprendizaje
            for (Cara f : faces) if (pointLocation(h, f) != f.getTipo()) error+= f.getPeso();
            h.setError(error);
            if (i == 0) best = h; //Si es el primer hiperplano que genero, será el único que tengo (de momento)
            else if (h.getError() < best.getError()) best = h; //Si ya tengo uno, lo actualizo sólo si el error del nuevo es menor
        }
        confidence = 0.5d * Math.log((1 - best.getError())/best.getError()); //Formulita Adaboost
    }

    /**
     * Devuelve el tipo de imagen (cara, no-cara) para un hiperplano dado según el signo obtenido al evaluar los píxeles
     * @param h el hiperplano
     * @param c la imagen
     * @return CARA (1) si es cara; NO_CARA (-1) si no
     * @see Hyperplane#evaluate(int[])
     */
    int pointLocation(Hyperplane h, Cara c) { return h.evaluate(c.getData()) > 0d ? Cara.CARA : Cara.NO_CARA; }

    /**
     * Sobrecarga del método que tiene como hiperplano el mejor
     * @see WeakLearner#pointLocation(Hyperplane, Cara)
     */
    @SuppressWarnings("unused")
    int pointLocation(Cara c) { return pointLocation(best, c); }
}
