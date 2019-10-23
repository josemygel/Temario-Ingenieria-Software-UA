/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEstrella;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mirse
 */
public class Mundo {
    
    //Tamaño del mundo
    int tamanyo_x;
    int tamanyo_y;
    
    //Coordenada inicial caballero
    Coordenada caballero;
    
    //Coordenada incial dragon
    Coordenada dragon;
    
    //Mundo
    char mundo[][];
    
    public Mundo(){
        tamanyo_x = 14;
        tamanyo_y = 10;
        caballero = null;
        dragon = null;
        mundo = null;
    }
    
    public Mundo(Mundo m){
        tamanyo_x = m.tamanyo_x;
        tamanyo_y = m.tamanyo_y;
        caballero = m.caballero;
        dragon = m.dragon;
        
        //Hace una copia del mundo
        mundo = new char[tamanyo_y][tamanyo_x];   
        for (int i=0; i<tamanyo_x; i++){
            for(int j=0;j<tamanyo_y; j++)
               mundo[j][i] = m.mundo[j][i];   
        }
    }
    
    public boolean CargarMundo(String archivo){
        FileReader fr = null;
        String sCadena;
        String delimitador = " ";
        int i;

        try
            {
                //Abre el fichero
                fr = new FileReader(archivo);
                BufferedReader bf = new BufferedReader(fr);

                try
                    {

                       //Establece el tamaño del tablero
                        tamanyo_x = 14;
                        tamanyo_y = 10;
                        //Crea el mundo del tamaño especificado
                        mundo = new char[tamanyo_y][tamanyo_x];
                      
                        //Lee el mundo
                        i = 0;
                        while (i<tamanyo_y)
                        {
                            if((sCadena = bf.readLine())!=null){
                                //Separa los diferentes elementos de la cadena que ha leído
                                String[] elementos = sCadena.split(delimitador);

                                for (int j = 0; j < elementos.length; j++) {
                                    mundo[i][j] = elementos[j].charAt(0);
                                    //Almacena la coordenada del caballero
                                    if(mundo[i][j] == 'k'){
                                        caballero = new Coordenada(j, i);
                                    }else if(mundo[i][j] == 'd'){ //Almacena la coordenada del dragón
                                        dragon = new Coordenada(j, i);
                                    }
                                }
                                i++;
                            }else{
                                System.err.println("ERROR. Formato del fichero incorrecto");
                                return false;
                            }
                        }
                        
                        if(caballero == null || dragon== null){
                            System.err.println("ERROR. Formato del fichero incorrecto. Faltan coordenadas de los personajes");
                            return false;
                        }

                    } catch (IOException e1)
                    {
                        System.err.println("Error en la lectura del fichero:"+archivo);
                        return false;
                    }
                //Si falla la apertura del fichero
            } catch (FileNotFoundException e2)
            {
                System.err.println("Error al abrir el fichero: "+archivo);
                return false;
            }finally{
            // Cerramos el fichero en finally porque así nos aseguramos que se cierra tanto si todo ha ido bien, como
            // si ha saltado alguna excepción
            try{
                if( null != fr )
                    {
                        fr.close();
                    }
            }catch (Exception e3){
                System.err.println("Error al cerrar el fichero: "+archivo);

            }
        }
            return true;
    }
    
    public void mostrarMundo(){
        for (int i=0; i<tamanyo_y; i++){
            if(i%2==0)
                System.out.print(" ");
            for(int j=0;j<tamanyo_x; j++){
                System.out.print(mundo[i][j]+" ");
            }
            System.out.println();   
        }
    }
    
    public void setCelda(int x, int y, char valor){
        mundo[y][x] = valor;
    }
    
    public char[][] getMundo(){
        return mundo;
    }
    
    public char getCelda(int x, int y){
      return mundo[y][x];  
    }
    
    public Coordenada getCaballero(){
        return caballero;
    }
    
    public Coordenada getDragon(){
        return dragon;
    }
    
    public void setCaballero(int x, int y){
        caballero.x = x;
        caballero.y = y;
    }
    
    public void setDragon(int x, int y){
        dragon.x = x;
        dragon.y = y;
    }
    
}
