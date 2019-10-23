/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819_p2si;

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
 para así intentar ser capaz de Clasificar bien aquellas que el otro no pudo.</p>
 * @author pavel
 */
public class ClasificadorFuerte {
    private ArrayList<ClasificadorDebil> clasificadoresDebiles; //Lista de clasificadores simples, los cuales estarán ponderados según su confianza

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
     * @param imagenes el conjunto de entrenamiento.
     * @param testFaces el conjunto de test. Sólo lo usamos para ver cuántos acierta por iteración.
     * @param numWeakClassifiers cantidad de clasificadores simples que se generan. Según la fórmula, <strong>T</strong>.
     * @param numHyperplanes cantidad de hiperplanos que genera cada clasificador simple.
     * @param type tipo de imagenes con las que crear el constructor fuerte (Ej.: 1=BOLSO)
     * @see ClasificadorDebil#ClasificadorDebil(int, ArrayList)
     */
    public ClasificadorFuerte(ArrayList<Imagen> imagenes, ArrayList<Imagen> test, int numWeakClassifiers, int numHyperplanes, ImagenType type) {
    //Hacer que imprima con comas y 5 decimales
    //      NumberFormat format = NumberFormat.getInstance(new Locale("es", "ES")); format.setMaximumFractionDigits(5); 
    // System.out.println("Iteration\t%Aciertos(Entr.)\tErrores(Entr.)\t%Aciertos(Test)\tErrores(test)\t");

        clasificadoresDebiles = new ArrayList<>(numWeakClassifiers);
        
        //Di = 1/N  |>  Dificultad de Clasificar
        for (Imagen f : imagenes) f.setPeso(1d/imagenes.size());
        
        //for t = 1 -> T do  |>  T es el numero de clasificadores dbiles a usar
        for (int t = 0; t < numWeakClassifiers; t++) {
            //Entrenar ht teniendo en cuenta Dt
            //DENTRO DEL CONSTRUCTOR DEL CLASIFICADOR DEBIL, SE CREA EL PLANO ALEATORIO Y SE ESCOGE EL MEJOR
            //Del mejor, ya se obtiene su valor de confianza en el constructor [ALFA como numero R (double)]
            ClasificadorDebil learner = new ClasificadorDebil(numHyperplanes, imagenes, type);
            //Actualizamos la distribución de pesos D_{t+1}(i) sobre el conjunto de entrenamiento
            //OJO: Estamos actualizando los pesos de CADA imagen para que el PRÓXIMO clasificador débil lo tenga en cuenta!

            double Z_t = 0d; //Constante de normalización calculada como el sumatorio de todos los pesos de las caras
            for (Imagen f : imagenes) { //Aquí viene lo bueno, pero es simplemente seguir la fórmula (mirar Doc del método)
                
                double tipo = f.isType(type) ? 1d : -1d;
                //Actualizamos dificultad(peso) de las imagenes
                
                f.setPeso((f.getPeso() * Math.pow(Math.E, -learner.GetAlfa() * tipo * learner.Clasificar(f))));
                
                //Sumamos la dificultad a Z_t
                Z_t += f.getPeso();
            }
            
            //Una vez obtenemos la constante de normalización, pasamos otra vez para ponderar todos los pesos por igual:
            for (Imagen f : imagenes){ f.setPeso(f.getPeso() / Z_t); }
            
            int aciertos = getClassifiersMatches(imagenes,type); //Aciertos sobre el conjunto de entrenamiento
            int testAciertos = getClassifiersMatches(test,type); //Aciertos sobre el conjunto de test
             //   System.out.println((t+1) + "\t\t" + aciertos + "\t\t\t" + (imagenes.size()-aciertos) + "\t\t" + testAciertos + "\t\t" + (testFaces.size()-testAciertos) + "\t");
            iftestAciertos > test.size()/2d) //Si acierta mas de la mitad
                clasificadoresDebiles.add(learner); //Nos lo guardamos
//            if (learner.best().error == 0) break; //Si las acierto todas termino
        }
        //RESETEO DE IMAGENES
        for (Imagen f : imagenes) f.setPeso(1);
    }

    /**
     * Contabiliza la cantidad de aciertos que tiene el clasificador complejo sobre un conjunto de imágenes cara,no-cara
     * @param imagenes el conjunto de imágenes
     * @return la cantidad de aciertos
     */
    private int getClassifiersMatches(ArrayList<Imagen> imagenes, ImagenType type) {
        int aciertos = 0;
        
        for (Imagen img : imagenes){
            if (Clasificar(img,type)) {
                aciertos++;
            }
        }
        
        return aciertos;
    }

    /**
     * Devuelve el tipo de imagen (cara, no-cara) del clasificador complejo como ponderación de todas las evaluaciones
     * de cada clasficiador simple que lo componen por la confianza de cada uno.
     * @param f la imagen
     * @return CARA (1) si es cara; NO_CARA (-1) si no
     * @see ClasificadorDebil#pointLocation(Hyperplane, Imagen)
     */
    int Evaluar(Imagen f) {
        double sum = 0d;
        //Testeamos la posicion de una imagen en base a todos los clasificadores debiles
        for (ClasificadorDebil learner : clasificadoresDebiles){
            sum += learner.GetAlfa() * learner.best().Evaluar(f.getIntImageData());
        }
        
        return sum > 0d ? 1 : -1;
    }
    
    public boolean Clasificar(Imagen img, ImagenType type){
        //true si coincide que es del tipo y su posicion es positiva o viceversa, falso si no coinciden los datos
        return Evaluar(img)==1 == img.isType(type);
    }
/*
    /**
     * Lee un fichero JSON con los datos del clasificador y genera una instancia sobre éstos.
     * @param fileName el nombre del fichero a leer.
     * @return una instancia de la clase con los datos del JSON leído.
     * @throws FileNotFoundException si no existe el fichero cuyo nombre le pasamos.
     *//*
    public static ClasificadorFuerte loadLearner(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(fileName));
        return gson.fromJson(reader, ClasificadorFuerte.class);
    }

    /**
     * Guarda los datos del clasificador en un JSON que se escribe a un fichero.
     * @param fileName el nombre del fichero a escribir.
     * @param pretty si queremos que lo escriba bien formateado o en una sola línea
     * @throws IOException si no puede escribir en ese fichero.
     *//*
    public void saveLearner(String fileName, boolean pretty) throws IOException {
        Gson gson = pretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(gson.toJson(this));
        Files.write(Paths.get(fileName), gson.toJson(je).getBytes());
    }*/
}