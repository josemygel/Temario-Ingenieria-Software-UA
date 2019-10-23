package adaboost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * <p>Clasificador complejo, basado en n clasificadores simples ponderados.</p>
 * <p>La ponderación se basa en, por cada clasificador débil generado, actualizar los pesos de las imágenes según la confianza
 * del clasificador asignando un peso más alto a aquellas imágenes que ha fallado y uno más bajo a aquellas que ha acertado.</p>
 * <p>El próximo clasificador generado tendrá más en cuenta aquellas imágenes que no ha podido acertar el anterior
 * para así intentar ser capaz de clasificar bien aquellas que el otro no pudo.</p>
 * @author pavel
 */
public class StrongLearner {
    private final ArrayList<WeakLearner> weakLearners; //Lista de clasificadores simples, los cuales estarán ponderados según su confianza

    /**
     * Algoritmo AdaBoost:
     * <ol>
     *     <li>Inicializamos la distribución de pesos D<sub>1</sub>(i) = 1/N sobre el conjunto de entrenamiento.</li>
     *     <li>Repetir desde t = 0 hasta T-1:
     *         <ol>
     *             <li>Entrenar clasificador débil h<sub>t</sub> a partir de D<sub>t</sub>.</li>
     *             <li>Calcular el valor de confianza para h<sub>t</sub> -> α<sub>t</sub> = 1/2 Ln(1-ε<sub>t</sub>/ε<sub>t</sub>).</li>
     *             <li>
     *                 Actualizar la distribución D<sub>t+1</sub>(i) sobre el conjunto de entrenamiento:<br/>
     *                 <img src="http://oi64.tinypic.com/2i9phjn.jpg"/>
     *             </li>
     *         </ol>
     *     </li>
     *     <li>Si el clasificador ha acertado todas las imágenes del entrenamiento, detenerse.</li>
     * </ol>
     * @param faces el conjunto de entrenamiento.
     * @param testFaces el conjunto de test. Sólo lo usamos para ver cuántos acierta por iteración.
     * @param numWeakClassifiers cantidad de clasificadores simples que se generan. Según la fórmula, <strong>T</strong>.
     * @param numHyperplanes cantidad de hiperplanos que genera cada clasificador simple.
     * @see WeakLearner#WeakLearner(int, ArrayList)
     */
    public StrongLearner(ArrayList<Cara> faces, ArrayList<Cara> testFaces, int numWeakClassifiers, int numHyperplanes) {
        //Hacer que imprima con comas y 5 decimales
        NumberFormat format = NumberFormat.getInstance(new Locale("es", "ES"));
        format.setMaximumFractionDigits(5);

        weakLearners = new ArrayList<>(numWeakClassifiers);
        //Inicializamos la distribución de pesos D_1(i) = 1/N sobre el conjunto de entrenamiento:
        for (Cara f : faces) f.setPeso(1d/faces.size());
        if (Practica2SI.VERY_VERBOSE) System.out.println("Iteration\t%Matches(Learn)\tFacesError(Learn)\tNo-FacesError(Learn)\t%Matches(Test)\tFacesError(test)\tNo-FacesError(Test)");
        for (int t = 0; t < numWeakClassifiers; t++) {
            long time = System.currentTimeMillis();
            //Nos creamos un nuevo clasificador h_t. Vendrá ya con el valor de confianza:
            WeakLearner learner = new WeakLearner(numHyperplanes, faces);
            weakLearners.add(learner); //Nos lo guardamos
            //Actualizamos la distribución de pesos D_{t+1}(i) sobre el conjunto de entrenamiento
            //OJO: Estamos actualizando los pesos de CADA imagen para que el PRÓXIMO clasificador débil lo tenga en cuenta!
            double Z_t = 0d; //Constante de normalización calculada como el sumatorio de todos los pesos de las caras
            for (Cara f : faces) { //Aquí viene lo bueno, pero es simplemente seguir la fórmula (mirar Doc del método)
                f.setPeso(f.getPeso() * Math.pow(
                        Math.E, -learner.getConfidence() * f.getTipo() * learner.pointLocation(f)
                ));
                Z_t += f.getPeso();
            }
            //Una vez obtenemos la constante de normalización, pasamos otra vez para ponderar todos los pesos por igual:
            for (Cara f : faces) f.setPeso(f.getPeso() / Z_t);
            if (Practica2SI.VERY_VERBOSE) { //Datos de cada iteración
                int[] stats = getClassifiersMatches(faces); //Aciertos sobre el conjunto de entrenamiento
                int[] testStats = getClassifiersMatches(testFaces); //Aciertos sobre el conjunto de test
                System.out.println((t+1) + "\t" + format.format((double)stats[0]/faces.size()) + "\t" + stats[1] + "\t" + stats[2]
                        + "\t" + format.format((double)testStats[0]/testFaces.size()) + "\t" + testStats[1] + "\t" + testStats[2]);
            }
            if (learner.best().getError() == 0) break; //Si las acierto todas termino
        }
    }

    /**
     * Contabiliza la cantidad de aciertos que tiene el clasificador complejo sobre un conjunto de imágenes cara,no-cara
     * @param faces el conjunto de imágenes
     * @return la cantidad de aciertos
     */
    private int[] getClassifiersMatches(ArrayList<Cara> faces) {
        int matches, facesErrors, noFacesErrors;
        matches = facesErrors = noFacesErrors = 0;
        for (Cara f : faces) {
            if (pointLocation(f) == f.getTipo()) matches++;
            else if (f.getTipo() == Cara.CARA) facesErrors++;
            else noFacesErrors++;
        }
        return new int[] {matches, facesErrors, noFacesErrors};
    }

    /**
     * Devuelve el tipo de imagen (cara, no-cara) del clasificador complejo como ponderación de todas las evaluaciones
     * de cada clasficiador simple que lo componen por la confianza de cada uno.
     * @param f la imagen
     * @return CARA (1) si es cara; NO_CARA (-1) si no
     * @see WeakLearner#pointLocation(Hyperplane, Cara)
     */
    public int pointLocation(Cara f) {
        double sum = 0d;
        for (WeakLearner learner : weakLearners) sum += learner.getConfidence() * learner.best().evaluate(f.getData());
        return sum > 0d ? Cara.CARA : Cara.NO_CARA;
    }

    /**
     * Lee un fichero JSON con los datos del clasificador y genera una instancia sobre éstos.
     * @param fileName el nombre del fichero a leer.
     * @return una instancia de la clase con los datos del JSON leído.
     * @throws FileNotFoundException si no existe el fichero cuyo nombre le pasamos.
     */
    public static StrongLearner loadLearner(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(fileName));
        return gson.fromJson(reader, StrongLearner.class);
    }

    /**
     * Guarda los datos del clasificador en un JSON que se escribe a un fichero.
     * @param fileName el nombre del fichero a escribir.
     * @param pretty si queremos que lo escriba bien formateado o en una sola línea
     * @throws IOException si no puede escribir en ese fichero.
     */
    public void saveLearner(String fileName, boolean pretty) throws IOException {
        Gson gson = pretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(gson.toJson(this));
        Files.write(Paths.get(fileName), gson.toJson(je).getBytes());
    }
}
