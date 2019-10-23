package Adaboost;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.*;

/**
 * @author josemygel
 */
public class Main {
    public static void println(String txt){System.out.println(txt);}
    public static void print(String txt){System.out.print(txt);}
    private static void tester(ArrayList<Imagen> entreno, ArrayList<Imagen> tests, ImagenType type){
        println("\nEn este tester vamos a mostrar cuan eficaz es un clasificador fuerte según el número de hiperplanos y clasificadores débiles...");

        for(int i = 1; i <= 8; i++){
            ClasificadorDebil myCD = new ClasificadorDebil(i,entreno,ImagenType.getType(i-1));
            ClasificadorFuerte myCF = new ClasificadorFuerte(entreno, tests, i, i, ImagenType.getType(i-1));

            float aciertos = 0;
            float aciertosCD = 0;
            
            for(int j = 0; j < tests.size(); j++)
            {
                Imagen test = tests.get(j);
                if(myCF.Clasificar(test, type)){
                    //NO ENTRA A CLASIFICAR NI UNA SOLA SUDADERA (Type = 0)
                    aciertos++;
                }
                if(myCD.Clasificar(test) > 0d == test.isType(type))
                {
                    aciertosCD++;
                }
            }
            
            System.out.println("TYPE= " + ImagenType.getType(i-1) + " => Con " + i*10 + " hiperplanos y clasificadores débiles, el porcentaje medio de aciertos es: " + (double)100*aciertos/tests.size() + "% con " + aciertos + " aciertos.");
            System.out.println("TYPE= " + ImagenType.getType(i-1) + " => Con " + i*10 + " hiperplanos medio de aciertos en un clasificador DEBIL es: " + (double)100*aciertosCD/tests.size() + "% con " + aciertosCD + " aciertos.\n");
        }
    }
    
    
    private static final int DIM = 28 * 28;
    private static final double porcEntreno = 80/100d; //Porcentaje de imagenes usadas para el entrenamiento 0.70 = 70% (el resto es test)
    
    private static ArrayList<ClasificadorFuerte> loadCF(String file){
        if(file.indexOf(".cf")==-1) file+=".cf";
        try{
            FileInputStream fi = new FileInputStream(new File(file));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read object
            // FALLA AL LEER EL CLASIFICADOR FUERTE
            ArrayList<ClasificadorFuerte> cfs = (ArrayList) oi.readObject();
            
            oi.close();
            fi.close();

            return cfs;
        }catch(Exception e){
            System.err.println("Excepción encontrada: " + e.getMessage());
            
        }
        
        return null;
    }
    
    private static void saveCFS(ArrayList<ClasificadorFuerte> cf,String name) {
        try{
            if(name.indexOf(".cf")==-1)
                name += ".cf";
            
            FileOutputStream f = new FileOutputStream(new File(name));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            o.writeObject(cf);

            o.close();
            f.close();
        }catch(Exception e){
         System.out.println("Excepcion encontrada: " + e.getMessage());   
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { //Adaboost –run <nombre_fichero.cf> <imagen_prueba>
        //Cargador de la BD de SI
        DBLoader ml = new DBLoader();
        ml.loadDBFromPath("./db");
        
        //Almacenamos todas las imagenes en ArraysList
        ArrayList<ArrayList> imgs = new ArrayList();
        
        for(int i = 0; i < ml.getSizeDatabaseForDigit(); i++)
           imgs.add(ml.getImageDatabaseForDigit(i));
        
        ArrayList<Imagen> entreno = new ArrayList<Imagen>();
        ArrayList<Imagen> test = new ArrayList<Imagen>();
        
        //VAMOS A ALMACENAR TODAS LAS IMAGENES CON SU TYPE CORRESPONDIENTE EN ENTRENO Y TEST
        for(int i=0; i<8; i++){
            for(double j=0; j<imgs.get(i).size(); j++){
                Imagen img = (Imagen)imgs.get(i).get((int)j);
                img.setType(i);
                
                if(j <= (double)imgs.get(i).size()*porcEntreno){
                    entreno.add(img);
                }else{
                    test.add(img);
                }
            }
        }
        
                
        String argumento = "";
        String ruta = "";
        if(args.length > 1){
            if(args[0] == "-run")
            ruta = args[2];
                
            argumento = args[1];
        }
        
        System.out.println("Imagenes usadas para Entrenamiento: " + entreno.size());
        System.out.println("Imagenes usadas para Testeo: " + test.size());
        //TESTER CON BOLSOS MISMAMENTE
//        tester(entreno,test,ImagenType.getType(0));


        if(null == args[0])
            System.err.println("Error de parámetros");
        else switch (args[0]) {
            case "-run":
                run(args[1],args[2],entreno,test);
                break;
            case "-train":
                train(args[1],entreno,test);
                break;
            default:
                System.err.println("Error de parámetros");
                break;
        }
    }
    
    private static void run(String cfile, String ruta,ArrayList<Imagen> entreno, ArrayList<Imagen> test){
        int tipo = Integer.parseInt(ruta.split("/")[0]);
        
        File imgFile = new File("db/"+ruta);
        Imagen imagen = new Imagen(imgFile);
        imagen.setType(tipo);
        
        ClasificadorFuerte cf = null;
        
        for(ClasificadorFuerte clasif : loadCF(cfile)){
            if(imagen.isType(clasif.type)){
                cf = clasif;
                break;
            }
        }
        
        
        if(cf == null)
            System.err.println("El clasificador fuerte no ha sido cargado...");
        else if(cf.Clasificar(imagen, ImagenType.getType(tipo)))
            System.out.println("\nEl clasificador dice que la imagen ES un/a " + ImagenType.getType(tipo));
        else System.out.println("\nEl clasificador dice que la imagen NO ES un/a " + ImagenType.getType(tipo));
    }
    
    private static void train(String name, ArrayList<Imagen> entreno, ArrayList<Imagen> test){
        ArrayList<ClasificadorFuerte> cfs = new ArrayList<ClasificadorFuerte>();
        
        int hiperplanos = 30;
        int clasificadoresDebiles = 30;
        //Generaremos un clasificador para cada tipo
        for(int i=0;i<8;i++){
            System.out.println("Generando clasificador fuerte para " + ImagenType.getType(i));
            ClasificadorFuerte cf = new ClasificadorFuerte(entreno,test,clasificadoresDebiles,hiperplanos,ImagenType.getType(i));
            cfs.add(cf);
        }
        //-train misclasificadores
        //-run misclasificadores  1/4469.png
        //Una vez generados los 8, se procede a guardarlos en una imagen
        saveCFS(cfs,name);
    }
}