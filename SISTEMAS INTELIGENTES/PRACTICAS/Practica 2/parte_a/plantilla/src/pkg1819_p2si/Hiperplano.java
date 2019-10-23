package pkg1819_p2si;

import java.util.Random;

/**
 * @author josemygel
 */

public class Hiperplano {
    public static final int DIM = 28 * 28; //Tamaño del array de píxeles de una imagen (28 ancho * 28 alto)
    public double[] vector = new double[DIM]; //Componentes vector (x_i) del hiperplano
    public double C = 0d; //Constante final
    public double error = 0d; //tasa de error del hiperplano

    Hiperplano() {
        Random random = new Random(System.currentTimeMillis()); //OK
        
        for (int i = 0; i < DIM; i++) {
            //GENERAMOS EL VECTOR ALEATORIO
            vector[i] = random.nextDouble()-random.nextDouble();
            //GENERAMOS LA CONSTANTE ALEATORIA (CON "IMAGEN" ALEATORIA)
            //valores entre 0 y 255 (imagen aleatoria en escala de grises)
            C += vector[i] * random.nextInt()%256; 
        }
    }
    //Evaluar se encarga de devolver el valor de la evaluación de una imagen con sus datos
    public double Evaluar(int[] pixels) {
        double evaluation = 0d;
        
        for (int i = 0; i < DIM; i++)
        {
            evaluation += vector[i] * (double)pixels[i];
        }
        
        return evaluation - C;
    }
    public double Evaluar(Imagen img) { return Evaluar(img.getIntImageData()); }
    
    //Clasificar se encargará de decir si Evaluar esta imagen devolvió true/false
    public boolean Clasificar(Imagen img) { return Evaluar(img) > 0d; }
    public boolean Clasificar(int[] pixels){ return Evaluar(pixels) > 0d; }
}
