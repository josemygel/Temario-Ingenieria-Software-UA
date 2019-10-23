/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaboost;

import java.io.Serializable;

/**
 *
 * @author josemygel
 */
public enum ImagenType implements Serializable{
    SUDADERA,
    BOLSO,
    CAMISETA,
    PANTALON,
    JERSEY,
    VESTIDO,
    ZAPATILLA,
    ZAPATO,
    NONE;
    
    static public ImagenType getType(int t) {
        switch(t){
            case 0: return SUDADERA;
            case 1: return BOLSO;
            case 2: return CAMISETA;
            case 3: return PANTALON;
            case 4: return JERSEY;
            case 5: return VESTIDO;
            case 6: return ZAPATILLA;
            case 7: return ZAPATO;
            default: return NONE;
        }
    }
}
