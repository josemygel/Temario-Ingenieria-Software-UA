/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEstrella;

/**
 *
 * @author mirse
 */
public class Coordenada {
    int x;
    int y;
    
    public Coordenada(){
        x = 0;
        y = 0;
    }
    
    public Coordenada(int coordx, int coordy){
        x = coordx;
        y = coordy;
    }
    
    public Coordenada(Coordenada c){
        x = c.x;
        y = c.y;
    }
    
    public void set(Coordenada c){
        x = c.x;
        y = c.y;
    }
    
    public void set(int coordx, int coordy){
        x = coordx;
        y = coordy;
    }
   
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
}
