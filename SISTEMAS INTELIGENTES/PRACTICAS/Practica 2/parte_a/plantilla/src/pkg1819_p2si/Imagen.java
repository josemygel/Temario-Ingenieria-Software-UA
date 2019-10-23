/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819_p2si;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fidel
 */
public class Imagen {
    
    private BufferedImage biImage;
    private byte [] imageData;
    private int [] imageIntData;
    private static int DIM = 784;
    private double peso = 1;
    private ImagenType type = ImagenType.NONE;
    
    //Set&Get del peso
    public void setPeso(double p) { peso = p; }
    public double getPeso() { return peso; }
    
    //Se podrá setear el tipo con un entero o con un ImagenType
    public void setType(int t) { type = ImagenType.getType(t); }
    public void setType(ImagenType t) { type = t; }
    
    //Se podra obtener el Enum de ImagenType que contiene
    public ImagenType getType() { return type; }
  
    //Posibles funciones auxiliares para el type de las imagenes
    public boolean isType(Imagen img) { return isType(img.getType()); }
    public boolean isType(int t) { return isType(ImagenType.getType(t)); }
    public boolean isType(ImagenType t) { return t == type; }
    
    
    Imagen(){
        biImage = null;
    }
    
    
    Imagen(File file){
        try {
            biImage = ImageIO.read(file);
            imageData = ((DataBufferByte)biImage.getRaster().getDataBuffer()).getData();
            
            imageIntData = new int[DIM];
            
            for(int i = 0; i < DIM; i++) imageIntData[i] = (int)imageData[i];
            
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadFromPath(String filename){
        try
        {
            File file = new File(filename);
            biImage = ImageIO.read(file);
            imageData = ((DataBufferByte)biImage.getRaster().getDataBuffer()).getData();

            
            //Conversion rápida de color a grises, por si es necesario...
            //BufferedImage biColor = ImageIO.read(file);
            //biImage = new BufferedImage(biColor.getWidth(), biColor.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            //biImage.getGraphics().drawImage(biColor, 0, 0, null);
                           
        } catch (IOException e)
        {
                System.out.println(e.getMessage());
        }
    }
    
    byte [] getImageData(){
        return imageData;
    }
    
    int [] getIntImageData(){
        return imageIntData;
    }
    
    BufferedImage getBufferedImage(){
        return biImage;
    }
    
    
}
