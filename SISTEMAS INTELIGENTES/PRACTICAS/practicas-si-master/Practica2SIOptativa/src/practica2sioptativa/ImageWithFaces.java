package practica2sioptativa;

import adaboost.Cara;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static practica2sioptativa.Practica2SIOptativa.learner;

/**
 * Clase auxiliar que lee una imagen y va pasando píxel a píxel en busca de caras.
 * Si encuentra una pinta un borde rojo alrededor. También puede guardar la imagen pintada
 * @author pavel
 */
class ImageWithFaces {
    private BufferedImage img, copy; //Una imagen y su copia que usamos para pintar las caras detectadas

    /**
     * Lee una imagen
     * @param fimg la imagen a leer
     * @throws IOException si no se ha podido leer
     */
    ImageWithFaces(File fimg) throws IOException {
        try {
            img = ImageIO.read(fimg);
            copy = ImageIO.read(fimg);
        } catch (IOException e) {
            throw new IOException("No se puede abrir " + fimg.getAbsolutePath());
        }
    }

    /**
     * Guarda la imagen pintada en la ruta media/salida_imagenes/. Si no existe la crea
     * @param fileName el nombre de la imagen pintada
     * @throws IOException si no puede escribir la imagen
     */
    void save(String fileName) throws IOException {
        try {
            File dir = new File("media/salida_imagenes/");
            if (dir.mkdirs()) System.out.println("Ruta " + dir + " inexistente creada");
            fileName = dir + "/" + fileName;
            //Como formato cojo el nombre de la extensión
            ImageIO.write(copy, fileName.substring(fileName.lastIndexOf('.') + 1), new File(fileName));
        } catch (IOException e) {
            throw new IOException("No se puede guardar " + new File(fileName).getAbsolutePath());
        }
    }

    /**
     * <p>Recorre la imagen en busca de caras.</p>
     * <p>
     *     Por cada píxel, recorre sus 24x24 adyacentes generando una instancia de Cara y la pasa por el clasificador.
     *     Si es una cara, pinta un recuadro rojo a su alrededor.
     * </p>
     */
    void detectFacesAndPaint() {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                //Si tengo hueco para buscar la cara y ésta efectivamente lo es
                if (x <= img.getWidth() - 24 && y <= img.getHeight() - 24 &&
                        learner.pointLocation(new Cara(getNext24x24MaskedPixels(x, y))) == Cara.CARA) {
                    paint24x24Edge(x, y, Color.RED);
                }
            }
        }
    }

    /**
     * Dada una posición de imagen pinta un marco en sus 24x24 píxeles adyacentes
     * @param x coordenada X de la imagen
     * @param y coordenada Y de la imagen
     * @param color el color a pintar
     */
    private void paint24x24Edge(int x, int y, Color color) {
        //No sé por qué pero hay que acceder de forma traspuesta
        for (int i = y; i < y + 24; i++) {
            for (int j = x; j < x + 24; j++) {
                if ((i == y || i == y + 23) || (j == x || j == x + 23)) { //Sólo pintar bordes
                    copy.setRGB(j, i, color.getRGB());
                }
            }
        }
    }

    /**
     * Dada una posición de imagen, obtiene todos los 24x24 píxeles adyacentes y los guarda en un array al que se le ha
     * aplicado la misma máscara que en el constructor de Cara.
     * @param x coordenada X de la imagen
     * @param y coordenada Y de la imagen
     * @return los píxeles de la imagen con la máscara aplicada
     */
    private int[] getNext24x24MaskedPixels(int x, int y) {
        int[] subdata = new int[24 * 24];
        int count = 0;
        //No sé por qué pero hay que acceder de forma traspuesta
        for (int i = y; i < y + 24; i++) {
            for (int j = x; j < x + 24; j++) {
                subdata[count++] = img.getRGB(j, i);
            }
        }
        /*
        //TEST. Con Intellij IDEA puedes ver la imagen que contiene BufferedImage
        BufferedImage image = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, 24, 24, subdata, 0, 24);
        image.getSource();
        */
        return subdata;
    }
}
