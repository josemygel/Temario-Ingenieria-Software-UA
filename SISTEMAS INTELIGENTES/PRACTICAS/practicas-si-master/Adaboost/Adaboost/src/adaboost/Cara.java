package adaboost;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase cara. Representa un ejemplo para el learn.
 * @author dviejo
 */
public class Cara {
    private int []data;
    private int tipo;

    //EXTREMOS RGB -> Para el hiperplano
    public static final int MIN_RGB = 0;
    public static final int MAX_RGB = 255;
    //TIPOS -> Facilita la comprensión del código
    public static final int CARA = 1;
    public static final int NO_CARA = -1;
    //PESO -> Para Adaboost
    private double peso = 1; //Por defecto 1 para que el clasificador débil contabilice cada fallo como unitario
    double getPeso() { return peso; }
    void setPeso(double value) { peso = value; }

    /**
     * Constructor por defecto sólo con los píxeles. Para la parte optativa
     * @param data los píxeles
     */
    public Cara(int[] data) {
        for (int i = 0; i < data.length; i++) data[i] &= 0x000000FF;
        this.data = data;
    }

    /**
     * Lee la información de una imagen desde un fichero. Le asigna el tipo
     * que se recibe como parametro
     * @param fcara fichero de entrada
     * @param tipo 1 = cara; -1 = nocara
     */
    public Cara(File fcara, int tipo) {
        int mask = 0x000000FF;
        int cont;
        BufferedImage bimage;
        try
        {
            bimage = ImageIO.read(fcara);
            data = bimage.getRGB(0, 0, bimage.getWidth(), bimage.getHeight(), null, 0, bimage.getWidth());
            //Asumiendo que la imagen ya está en escala de grises pero en formato color,
            //convertimos ARGB en un único valor
            for(cont=0;cont<data.length;cont++) data[cont] = data[cont] & mask;
            this.tipo = tipo;
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public int[] getData() {
        return data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int newtipo) {
        tipo = newtipo;
    }
}