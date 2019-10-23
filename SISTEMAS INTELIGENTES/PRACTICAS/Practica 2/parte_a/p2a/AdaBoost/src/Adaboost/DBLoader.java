/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaboost;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 *
 * @author fidel
 */
public class DBLoader {

    private ArrayList[] imageDB;
    
    void loadDBFromPath(String path){
        
        int imagesCount=0;
        
        //Una arrayList por clase almacenará las imágenes
        imageDB = new ArrayList[8];
        
        //Creo un array list de imagenes para cada clase y cargo cada una
        //de las imágenes disponibles por clase
        for (int i=0;i<8; i++){
            imageDB[i] = new ArrayList();
            System.out.println("Loaded class "+i);
            
            File[] files = new File(path,"" + i).listFiles(new FileFilter() {
            @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".png") 
                        || pathname.isDirectory();
                }
            });
            
            //File[] files = new File(path,"" + i).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                   imageDB[i].add(new Imagen(file.getAbsoluteFile()));
                   imagesCount++;
                }
            }
        }
        
        System.out.println("Loaded "+ imagesCount + " images...");
        
    }
    
    int getSizeDatabaseForDigit(){
        return imageDB.length;
    }
    
    ArrayList getImageDatabaseForDigit(int digit){
        return imageDB[digit];
    }
    
}
