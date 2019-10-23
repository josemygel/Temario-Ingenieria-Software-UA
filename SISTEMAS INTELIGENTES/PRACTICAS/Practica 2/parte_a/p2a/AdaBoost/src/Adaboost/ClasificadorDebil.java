package Adaboost;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clasificador debil: Genera una cierta cantidad de hiperplanos y se queda con aquel que más imágenes del conjunto
 * de aprendizaje haya acertado.
 * 
 * Una vez se haya entrenado el clasificador, será capaz de Clasificar una imagen
 * dependiendo del signo obtenido (positivo/negativo) al evaluarla con el mejor 
 * hiperplano generado
 * 
 * La precisión de este clasificador depende de la cantidad de hiperplanos generados,
 * aunque si es posible (auqnue remotamente posible) que un clasificador debil con
 * 1 o 2 hiperplanos sea mejor que otro con una cantidad mayor.
 * 
 * @author josemygel
 */
public class ClasificadorDebil implements Serializable {
    private Hiperplano best; //El hiperplano con menor error obtenido
    public Hiperplano best(){ return best; }

    private final double alfa; //Alfa del clasificador -> Útil sólo para el clasificador fuerte (AdaBoost)
    double GetAlfa() { return alfa; }

    /**
     * Obtiene un clasificador débil a partir de la generación de n hiperplanos,
     * de los cuales se queda con el de menor error
     * @param n cantidad de hiperplanos que se generán
     * @param images el conjunto de aprendizaje
     * @param type
     * @see Hiperplano#Hyperplane()
     */
    
    public ClasificadorDebil(int n, ArrayList<Imagen> images, ImagenType type) {
        //Se generarán n hiperplanos, y se clasificará el mejor de todos
        
        for (int i = 0; i < n; i++)
        {
            Hiperplano h = new Hiperplano(); //Genero un nuevo hiperplano aleatorio
            
            double error = 0d;
            //Si no es del tipo buscado, se añade un error
            
            for (Imagen img : images){
                if(Clasificar(h,img)>0d != img.isType(type)) error += img.getPeso();
            }
            
            h.error = error;
            
            //Si es el primer hiperplano que genero, será el único que tengo (de momento), 
            //Si ya tengo uno, lo actualizo sólo si el error del nuevo es menor
            if(i==0 || h.error < best.error )
                best = h;
        }
        
        //Evitamos que error ==0 y AQUI SE CALCULA LA CONFIANZA
        alfa = Math.log((1d - best.error)/best.error)/2d;
        
    }
    //1 es que es del tipo y -1 que no lo es
    public static int Clasificar(Hiperplano h, Imagen img) { return h.Clasificar(img.getIntImageData()) ? 1 : -1; }
    public double Clasificar(Imagen img) { return best().Evaluar(img.getIntImageData()); }
}