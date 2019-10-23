/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public abstract class Jugador implements Runnable{

    //Columna de la próxima jugada
    public int m_columna;

    //Indica si juega como jugador 1 o 2
    public int m_jugador;

    //Indica si ha terminado el análisis
    private boolean m_fin;

    //Tablero para analizar
    public Tablero m_tablero;


    //Constructor
    public Jugador(int jugador)
    {
        m_jugador = jugador;
    }


    public synchronized boolean isDone()
    {
        return m_fin;
    }


    public synchronized void isDone(boolean fin)
    {
        m_fin = fin;
    }


    //Dispone el tablero para el análisis
    public void asignarTablero(Tablero t)
    {
        m_tablero = t;
    }
}
