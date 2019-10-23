package es.sistemasinteligentes.practica1si.desktop;

import AEstrella.Mundo;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.sistemasinteligentes.practica1si.*;

public class DesktopLauncher {
    
    public static String fichero_mundo; //Nombre del fichero que contiene el mundo
    public static Mundo mundo; 
    
	public static void main (String[] arg) {
            
            //Comprueba si se le ha pasado por parámetro un mundo
            if(arg.length!=1)
            {
                //Si no se le pasa un mundo, carga el mundo por defecto
                fichero_mundo = new String("mundo_defecto.txt");
                
            }
            else{
                //Si se pasa un nombre de fichero por parámetro lee ese fichero
                fichero_mundo = new String(arg[0]);
            }
            
                mundo = new Mundo();
                mundo.CargarMundo(fichero_mundo);
                
                //Muestra el mundo leído por pantalla en modo texto
                System.out.println("Mundo a resolver");
                mundo.mostrarMundo();
                
                //Lanza aplicación pasándole el mundo leído de fichero
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();   
                config.width = 1024;
                config.height = 768;
		new LwjglApplication(new GameMain(mundo), config);
	}
}
