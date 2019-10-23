package pkg1819_p2si;

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
    private static final double porcEntreno = 70/100d; //Porcentaje de imagenes usadas para el entrenamiento 0.70 = 70% (el resto es test)
    
    private static ClasificadorFuerte loadCF(String file){
        try{
            FileInputStream fi = new FileInputStream(new File(file));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read object
            // FALLA AL LEER EL CLASIFICADOR FUERTE
            ClasificadorFuerte cf = (ClasificadorFuerte) oi.readObject();
            oi.close();
            fi.close();

            return cf;
        }catch(Exception e){
            
        }
        
        return null;
    }
    
    private static void saveCF(ClasificadorFuerte cf,ImagenType type) {
        try{
            FileOutputStream f = new FileOutputStream(new File(type+".cf"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            o.writeObject(cf);

            o.close();
            f.close();
        }catch(Exception e){
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { //Adaboost –run <nombre_fichero.cf> <imagen_prueba>
        
        String file = "";
        String imgtxt = "";
        if(args.length > 1){
            file = args[1];
            //Asumimos que sera un valor numerico (todas las imagenes tienen un valor numerico distinto)
            imgtxt = args[2];
            System.out.println("FILE = " + file);
            System.out.println("IMAGE = " + imgtxt);
        }
        
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
        System.out.println("Imagenes usadas para Entrenamiento: " + entreno.size());
        System.out.println("Imagenes usadas para Testeo: " + test.size());
        //TESTER CON BOLSOS MISMAMENTE
        tester(entreno,test,ImagenType.getType(0));
    }
}
        /*
        //FALTA ARREGLAR EL GUARDADO Y LA CARGA DE LOS CLASIFICADORES
        if(!file.isEmpty()){
            int tipo = Integer.parseInt(imgtxt.split("/")[0]);
            String[] ruta = imgtxt.split("/");
            
            File imgFile = new File("db/"+imgtxt);
            Imagen imagen = new Imagen(imgFile);
            
            ClasificadorFuerte cf = loadCF(file);
            if(cf.Clasificar(imagen, ImagenType.getType(tipo)))
                System.out.println("El clasificador dice que la imagen pertenece a " + ImagenType.getType(tipo));
            else System.out.println("El clasificador dice que la imagen no pertenede a " + ImagenType.getType(tipo));
        }
    }
}
        /*
        
       // ClasificadorFuerte cf = new ClasificadorFuerte(entrenamientoBolsos,testBolsos,cantidadClasificadores,cantidadHiperplanos);
        
    
        int valor = 1;
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        
        System.out.print("Introduce el valor para el calculo del clasificador debil (default="+ valor +"): ");
        try{
            valor = Integer.parseInt (br.readLine());
        }catch(Exception e){
            valor = 1000;
        }
        int type = 1;
        System.out.print("Introduce el type de imagen a tratar (1=BOLSO) default="+ type +"): ");
        try{
            type = Integer.parseInt (br.readLine());
        }catch(Exception e){
            type = 1;
        }
        
       // ArrayList cdBuenos = new ArrayList();
        /*
        double mediaAciertos = 0d;
        ImagenType tipo = ImagenType.getType(type);
        for(int c = 0; c<100; c++){
            ClasificadorDebil myClasificador = new ClasificadorDebil(valor,entreno,tipo);
            int aciertos = 0;
           // System.out.println("\nVAMOS A TESTEAR!!\n");
            for(int i = 0; i < test.size(); i++)
            {
                Imagen imgtest = (Imagen)test.get(i);
                
                if(myClasificador.Clasificar(imgtest)>0d)
                    aciertos++;
            }
            mediaAciertos += aciertos/test.size();
        }
        mediaAciertos = mediaAciertos/100;
        
        System.out.println("Pruebas hechas: " + (float)(1-porcEntreno) + "% de las imagenes,  que son " + test.size() + " imágenes.");
        System.out.println("Media de aciertos conseguidos: " + mediaAciertos);
    }
}
        
//        tester(entrenamientoBolsos,testBolsos);
        /*
        //Y cojo el decimo bolso de la bd
        img = (Imagen) bolsos.get(9);
        
        //La invierto para ilustrar como acceder a los pixels e imprimo los pixeles
        //en hexadecimal
        System.out.print("Image pixels: ");
        byte imageData[] = img.getImageData();
        for (int i = 0; i < imageData.length; i++)
        {
            //imageData = 28*28 = 784
            System.out.println(imageData[i]);
            imageData[i] = (byte) (0xff - imageData[i]);
            //System.out.format("%02X ", imageData[i]);
        }
        
        //Muestro la imagen invertida
        imgShow = new MostrarImagen();
        imgShow.setImage(img);
        imgShow.mostrar();
    }
}*/
