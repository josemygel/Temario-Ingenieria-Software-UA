package adaboost;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dviejo
 */
public class Practica2SI {
    private String rutaDir;
    private File []files;
    private int NUM_ITERACIONES;
    private int NUM_CLASIFICADORES;
    private boolean VERBOSE;
    static boolean VERY_VERBOSE = false;

    private double testRate;

    private ArrayList<Cara> listaAprendizaje;
    private ArrayList<Cara> listaTest;

    public Practica2SI() {
        rutaDir = "";
        testRate = 0.5;
        NUM_ITERACIONES = 1;
        NUM_CLASIFICADORES = 1;
        VERBOSE = false;
    }

    public void init() {
        int cont, aciertos, clase;
        System.out.println("Sistemas Inteligentes. Segunda practica");
        getFileNames(rutaDir+"cara/");
        listaAprendizaje = new ArrayList<Cara>();
        for(cont=0;cont<files.length;cont++) {
            if(!files[cont].isDirectory()){
                listaAprendizaje.add(new Cara(files[cont],1));
            }
        }
        getFileNames(rutaDir+"noCara/");
        for(cont=0;cont<files.length;cont++) {
            if(!files[cont].isDirectory()) {
                listaAprendizaje.add(new Cara(files[cont], -1));
            }
        }
        System.out.println(listaAprendizaje.size()+ " imágenes encontradas");
        //inicializamos las listas
        listaTest = new ArrayList<Cara>();
        //Separamos los conjuntos de learn y test
        CrearConjuntoAprendizajeyTest();
        System.out.println(listaAprendizaje.size()+" imagenes para learn, "+listaTest.size()+" imagenes para el test ("+(testRate*100)+"%)");
        //Comenzamos el learn
        long t1 = System.currentTimeMillis();
        //WeakLearner learner = new WeakLearner(NUM_CLASIFICADORES, listaAprendizaje);
        StrongLearner learner = new StrongLearner(listaAprendizaje, listaTest, NUM_ITERACIONES, NUM_CLASIFICADORES);
        //Prueba escritura-lectura del clasificador
        try {
            System.out.println("Guardando en disco...");
            learner.saveLearner("learner.json", true);
            System.out.println("Leyendo de disco...");
            learner = StrongLearner.loadLearner("learner.json");
        } catch (IOException ex) {
            Logger.getLogger(Practica2SI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long t2 = System.currentTimeMillis();
        long time;
        time = t2 - t1;
        System.out.println("Tiempo empleado en el learn: "+((float)time/1000f)+" segundos");
        System.out.println("Número de clasificadores encontrados: " + (NUM_ITERACIONES*NUM_CLASIFICADORES) + " (" + NUM_ITERACIONES+ "*" + NUM_CLASIFICADORES+ ")");

        //Test final
        if(VERBOSE) {
            aciertos = 0;
            for(Cara c: listaAprendizaje) {
                clase = learner.pointLocation(c);
                if(clase == c.getTipo()) aciertos++;
            }
            System.out.println("APRENDIZAJE. Tasa de aciertos: "+((float)aciertos/(float)(listaAprendizaje.size())*100.0f)+"%");
        }

        //Comprobamos el conjunto de test
        aciertos = 0;
        for(Cara c: listaTest) {
            clase = learner.pointLocation(c);
            if(clase == c.getTipo()) aciertos++;
        }
        System.out.println("TEST. Tasa de aciertos: "+((float)aciertos/(float)(listaTest.size())*100.0f)+"%");
    }

    /**
     * Selecciona al azar un subconjunto para Test. El resto compondrá el conjunto de learn
     */
    private void CrearConjuntoAprendizajeyTest() {
    	int totalTam = listaAprendizaje.size();
        int tamTest = (int)(totalTam * testRate);
        int cont;
        Random rnd = new Random(System.currentTimeMillis());
        for(cont=0;cont<tamTest;cont++) {
            listaTest.add(listaAprendizaje.remove(rnd.nextInt(totalTam)));
            totalTam--;
        }
    }

    public void getFileNames(String ruta) {
    	File directorio = new File(ruta);
        if (!directorio.isDirectory())
            throw new RuntimeException("La ruta debe ser un directorio: " + directorio.getAbsolutePath());
        ImageFilter filtro = new ImageFilter();
        files = directorio.listFiles(filtro);
    }

    public void setRuta(String r) {
        rutaDir = r;
    }

    public void setRate(double t) {
        testRate = t;
    }

    public void setNumIteraciones(int t) {
        NUM_ITERACIONES = t;
    }

    public void setNumClasificadores(int c) {
        NUM_CLASIFICADORES = c;
    }

    public void setVerbose(boolean v) {
        VERBOSE = v;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cont;
        Practica2SI programa;
        String option;
        boolean maluso = true;
        int paso = 2;

        programa = new Practica2SI();

        for(cont = 0; cont < args.length; cont+=paso) {
            option = args[cont];
            if(option.charAt(0) == '-') {
                switch(option.charAt(1)) {
                    case 'd':
                            programa.setRuta(args[cont+1]);
                            paso = 2;
                            maluso = false;
                            break;
                    case 't':
                            programa.setRate(Double.parseDouble(args[cont+1]));
                            paso = 2;
                            break;
                    case 'T':
                            programa.setNumIteraciones(Integer.parseInt(args[cont + 1]));
                            paso = 2;
                            break;
                    case 'c':
                            programa.setNumClasificadores(Integer.parseInt(args[cont + 1]));
                            paso = 2;
                            break;
                    case 'v':
                            programa.setVerbose(true);
                            paso = 1;
                            break;
                    case 'V':
                            VERY_VERBOSE = true;
                            paso = 1;
                            break;
                    default:
                            maluso = true;
                            break;
                }
            } else maluso = true;
        }

        if(!maluso) programa.init();
        else {
            System.out.println("Lista de parametros incorrecta");
            System.out.println("Uso: java Practica2SI -d ruta [-t testrate] [-T maxT] [-c numClasificadores] [-v]");
        }
    }
}
