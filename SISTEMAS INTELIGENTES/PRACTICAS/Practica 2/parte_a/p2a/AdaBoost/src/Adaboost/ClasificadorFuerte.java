package Adaboost;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author josemygel
 */
public class ClasificadorFuerte implements Serializable {
    private ArrayList<ClasificadorDebil> clasificadoresDebiles; //Lista de clasificadores simples, los cuales estarán ponderados según su alfa
    public ImagenType type = ImagenType.NONE;
    /**
     * @param entreno el conjunto de entrenamiento.
     * @param testFaces el conjunto de test. Sólo lo usamos para ver cuántos acierta por iteración.
     * @param n cantidad de clasificadores simples que se generan. Según la fórmula, T
     * @param h cantidad de hiperplanos que genera cada clasificador debil.
     * @param type tipo de imagenes con las que crear el constructor fuerte (Ej.: 1=BOLSO)
     * @see ClasificadorDebil#ClasificadorDebil(int, ArrayList, ImagenType)
     */
    public ClasificadorFuerte(ArrayList<Imagen> entreno, ArrayList<Imagen> test, int n, int h, ImagenType type) {
        this.type = type;
    //Hacer que imprima con comas y 5 decimales
    //      NumberFormat format = NumberFormat.getInstance(new Locale("es", "ES")); format.setMaximumFractionDigits(5); 
    // System.out.println("Iteration\t%Aciertos(Entr.)\tErrores(Entr.)\t%Aciertos(Test)\tErrores(test)\t");

        clasificadoresDebiles = new ArrayList<>(n);
        
        //Di = 1/N  |>  Dificultad de Clasificar
        for (Imagen f : entreno) f.setPeso(1d/entreno.size());
        
        //for t = 1 -> T do  |>  T es el numero de clasificadores dbiles a usar
        for (int t = 0; t < n; t++) {
            //Entrenar ht teniendo en cuenta Dt
            //DENTRO DEL CONSTRUCTOR DEL CLASIFICADOR DEBIL, SE CREA EL PLANO ALEATORIO Y SE ESCOGE EL MEJOR
            //Del mejor, ya se obtiene su valor de confianza en el constructor [ALFA como numero R (double)]
            ClasificadorDebil learner = new ClasificadorDebil(h, entreno, type);
            
            //Actualizamos la distribución de pesos D_{t+1}(i) sobre el conjunto de entrenamiento
            double Z_t = 0d; //Constante de normalización calculada como el sumatorio de todos los pesos de las caras
            for (Imagen img : entreno) {
                //1 si es del mismo tipo que el que buscamos y -1 si no
                double tipo = img.isType(type) ? 1d : -1d;
                //Actualizamos dificultad(peso) de las imagenes
                img.setPeso((img.getPeso() * Math.pow(Math.E, -learner.GetAlfa() * tipo * learner.Clasificar(img))));
                
                //Sumamos la dificultad a Z_t
                Z_t += img.getPeso();
            }
            
            //Una vez obtenemos la constante de normalización, pasamos otra vez para ponderar todos los pesos por igual:
            for (Imagen f : entreno){ f.setPeso(f.getPeso() / Z_t); }
            
            int aciertos = GetAciertos(entreno,type); //Aciertos sobre el conjunto de entrenamiento
            int testAciertos = GetAciertos(test,type); //Aciertos sobre el conjunto de test
      //          System.out.println((t+1) + "\t\t" + aciertos + "\t\t\t" + (entreno.size()-aciertos) + "\t\t" + testAciertos + "\t\t" + (test.size()-testAciertos) + "\t");
            if(testAciertos > test.size()/2d) //Si acierta mas de la mitad
                clasificadoresDebiles.add(learner); //Nos lo guardamos
//            if (learner.best().error == 0) break; //Si las acierto todas termino
        }
        
        //RESETEO DE IMAGENES
        for (Imagen f : entreno) f.setPeso(1);
    }

    /**
     * Contabiliza la cantidad de aciertos que tiene el clasificador fuerte sobre un conjunto de imágenes
     * @param imagenes el conjunto de imágenes
     * @return la cantidad de aciertos
     */
    private int GetAciertos(ArrayList<Imagen> imagenes, ImagenType type) {
        int aciertos = 0;
        
        for (Imagen img : imagenes){
            if (Clasificar(img,type)) {
                aciertos++;
            }
        }
        
        return aciertos;
    }

    /**
     * Devuelve el tipo de imagen del clasificador fuerte como ponderación de todas las evaluaciones
     * de cada clasficicador débil que lo componen por la confianza de cada uno.
     * @param img la imagen
     * @return Evaluacion positiva (1) si es del tipo del clasificador; Evaluacion negativa (-1) si no
     * @see ClasificadorFuerte#Clasificar(Imagen, ImagenType)
     */
    int Evaluar(Imagen img) {
        double sum = 0d;
        //Testeamos la posicion de una imagen en base a todos los clasificadores debiles
        for (ClasificadorDebil learner : clasificadoresDebiles){ sum += learner.GetAlfa() * learner.Clasificar(img); }
        
        return sum > 0d ? 1 : -1;
    }
    
    //true si coincide que es del tipo y su posicion es positiva o viceversa, falso si no coinciden los datos
    public boolean Clasificar(Imagen img, ImagenType type){ return Evaluar(img)==1 == img.isType(type); }
}