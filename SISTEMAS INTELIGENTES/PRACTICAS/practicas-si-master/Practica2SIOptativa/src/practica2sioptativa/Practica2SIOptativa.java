package practica2sioptativa;

import adaboost.StrongLearner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Parte optativa de la práctica 2 de SI: Aplicación del detector de caras
 * @author pavel
 */
public class Practica2SIOptativa {
    static StrongLearner learner; //El Clasificador

    /**
     * Recibe por parámetro un clasificador (archivo JSON) y una lista de imágenes.
     * Devuelve el tiempo de proceso de cada imagen y guarda cada una de ellas con el sufijo _out
     * @param args Los argumentos
     */
    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                learner = StrongLearner.loadLearner(args[0]); //El clasificador es siempre el primer argumento
                System.out.println("Usando clasificador " + args[0]);
                for (int i = 1; i < args.length; i++) {
                    System.out.print("Analizando " + args[i] + "... ");
                    ImageWithFaces imageWithFaces = new ImageWithFaces(new File(args[i]));
                    long t = System.currentTimeMillis();
                    imageWithFaces.detectFacesAndPaint();
                    System.out.println((System.currentTimeMillis() - t) + " ms"); //Muestro el tiempo
                    //El nombre será sólo el nombre del fichero, quitando la ruta
                    imageWithFaces.save(new File(args[i]).getName());
                }
            } catch (FileNotFoundException e) {
                System.err.println("JSON del clasificador no encontrado (" + new File(args[0]).getAbsolutePath() + ")");
            } catch (IOException e) {
                System.err.println("Error de ficheros de imagen: " + e.getLocalizedMessage());
            }
        } else {
            System.err.println("Parámetros inválidos. Uso:\n\t" 
                    + Practica2SIOptativa.class.getSimpleName().intern() //Nombre de la clase simplificado
                    + " (<Archivo-JSON>) [<Imágenes>...]");
        }
    }
}
