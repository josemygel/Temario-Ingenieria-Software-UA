/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public class Tablero {

    //Tamaño del tablero
    private int m_numColumnas;
    private int m_numFilas;

    //Datos del tablero
    private int m_tablero[][];
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m_numFilas; i++) {
            for (int j = 0; j < m_numColumnas; j++) {
                sb.append(m_tablero[i][j]).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Constructor del tablero
     * @param anchura Anchura del tablero
     * @param altura Altura del tablero
     */
    public Tablero(int anchura, int altura)
    {
        //Tamaño del tablero
        m_numColumnas = anchura;
        m_numFilas = altura;

        //Crea el tablero
        m_tablero = new int[m_numFilas][m_numColumnas];

        //Inicializa el tablero
        for (int i=0; i<m_numFilas; i++)
        {
            for(int j=0; j<m_numColumnas; j++)
                m_tablero[i][j] = 0;
        }

    }

    /**
     * Constsructor de Copia del tablero
     * @param original Tablero del cual realizar la copia
     */
    public Tablero (Tablero original)
    {
        //Tamaño del tablero
        m_numColumnas = original.m_numColumnas;
        m_numFilas = original.m_numFilas;

        //Crea el tablero
        m_tablero = new int[m_numFilas][m_numColumnas];

        //Inicializa el tablero
        for (int i=0; i<m_numFilas; i++)
        {
            for(int j=0; j<m_numColumnas; j++)
                m_tablero[i][j] = original.m_tablero[i][j];
        }
    }

    /**
     * Devuelve el número de columnas
     */
    public int numColumnas()
    {
        return m_numColumnas;

    }

    /**
     * Devuelve el número de filas
     */
    public int numFilas()
    {
        return m_numFilas;

    }

    /**
     * Indica si existe una ficha en la posición indicada.
     */
    public boolean existeFicha(int i, int j)
    {
        if(m_tablero[i][j] != 0)
            return true;
        else
            return false;
    }

    /**
     * Devuelve a quién pertenece la casilla especificada.
     * 0 está vacía
     * 1 pertenece al jugador 1 
     * 2 pertenece al jugador 2 
     */
    public int obtenerCasilla(int i, int j)
    {
        return m_tablero[i][j];
    }

    /**
     * Cambia el valor de una casilla del tablero
     */
    public void cambiarCasilla(int i, int j, int valor)
    {
        if(i>=0 && i<m_numFilas && j>=0 && j<m_numColumnas)
            m_tablero[i][j] = valor;
    }

    //Limpia el tablero. Deja todas las casillas vacías.
    public void limpiarTablero()
    {
        for(int i=0; i<m_numFilas; i++)
            for(int j=0; j<m_numColumnas;j++)
                m_tablero[i][j] = 0;
    }


    /**
     * Indica si el tablero está lleno.
     */
    public boolean tableroLleno()
    {
        boolean lleno = true;

        for(int i=0;((i<m_numFilas) && lleno);i++)
            for(int j=0;((j<m_numColumnas) && lleno);j++)
                if(m_tablero[i][j] == 0)
                    lleno = false;
        return lleno;
    }

    /**
     * Comprueba si se ha hecho 4 en raya.
     * Realiza la búsqueda en horizontal, en vertical y en diagonal.
     * 0 no hay 4 en raya.
     * 1 ha hecho 4 en raya el jugador 1 
     * 2 ha hecho 4 en raya el jugador 2 
     */
    public int cuatroEnRaya()
    {
        //Casilla por la que busca
        int casilla;
        //Jugador que a hecho el 4 en raya
        int ganador = 0;

        //Indica que ha encontrado 4 en raya
        boolean fin = false;

        int i = 0;
        int j = 0;

        while ((!fin) && (i < m_numFilas))
        {
            j = 0;
            while ((!fin) && (j < m_numColumnas))
            {
                //Para cada casilla del tablero
                casilla = m_tablero[i][j];
                //Si la casilla no está vacía comprueba si pertenece a un 4 en raya
                if(casilla != 0)
                {
                    //Realiza una búsqueda en horizontal
                    if(j+3 < m_numColumnas)
                        if ((m_tablero[i][j+1]==casilla) && (m_tablero[i][j+2]==casilla) && (m_tablero[i][j+3]==casilla))
                        {
                            ganador = casilla;
                            fin = true;
                        }

                    //Realiza una búsqueda en vertical
                    if(i+3 < m_numFilas)
                        if((m_tablero[i+1][j] == casilla) && (m_tablero[i+2][j] == casilla) && (m_tablero[i+3][j]== casilla))
                        {
                            ganador = casilla;
                            fin = true;
                        }

                    //Realiza una búsqueda diagonal. Debe buscar en las 2 diagonales a partir de una casilla
                    if(i+3 < m_numFilas)
                    {
                        //Realiza una búsqueda en la primera diagonal
                        if(j-3 >= 0)
                        {
                            if((m_tablero[i+1][j-1] == casilla) && (m_tablero[i+2][j-2] == casilla) && (m_tablero[i+3][j-3]== casilla))
                            {
                                ganador = casilla;
                                fin = true;
                            }
                        }

                        //Realiza una búsqueda en la segunda diagonal
                        if(j+3 < m_numColumnas)
                        {
                            if ((m_tablero[i+1][j+1]==casilla) && (m_tablero[i+2][j+2]==casilla) && (m_tablero[i+3][j+3]==casilla))
                            {
                                ganador = casilla;
                                fin = true;
                            }
                        }

                    }
               }
               j++;
           }
           i++;
        }
        return ganador;
    }


    /**
     * Inserta una ficha en el tablero, en la columna especificada. Devuelve:
     * 0 si la ficha se inserta correctamente.
     * 1 si la columna en la que se intenta colocar está llena.
     * -1 en caso de error.
     * @param columna Indica la columna en la que se coloca la ficha
     * @param jugador Indica el jugador que está jugando, 1 para jugador 1, 2 para jugador 2
     */
    public int ponerFicha (int columna, int jugador)
    {
        boolean encontrado = false;

        //Comprueba que la columna pasada como parámetro sea correcta
        if ((columna < 0) || (columna >= m_numColumnas))
           return -1;

        //Comprueba la primera casilla libre de esa columna (si tiene)
        for (int i=0;(i<m_numFilas && !encontrado);i++)
        {
            if(m_tablero[i][columna] == 0)
            {
                encontrado = true;
                //Si encuentra una casilla libre, pone la ficha
                m_tablero[i][columna] = jugador;
            }
        }

        //Si cuando sale del for no ha encontrado ninguna casilla libre es que la columna está llena
        if(!encontrado)
            return 1;
        else
            return 0;
    }

    /**
     * Comprueba si la columna que se le pasa como parámetro es correcta para una jugada.
     * Devuelve la fila en la que le se colocaría la ficha y -1 en caso de error.
     * @param columna
     * @return
     */
    public int comprobarColumna(int columna)
    {
        int fila = -1;
        boolean encontrado = false;

        //Comprueba que la columna pasada como parámetro sea correcta
        if ((columna < 0) || (columna >= m_numColumnas))
        {
           encontrado = true;
        }

        //Comprueba la primera casilla libre de esa columna (si tiene)
        for (int i=0;(i<m_numFilas && !encontrado);i++)
        {
            if(m_tablero[i][columna] == 0)
            {
                fila = i;
                encontrado = true;
            }
        }
        return fila;
    }

}
