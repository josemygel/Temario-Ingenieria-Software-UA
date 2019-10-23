package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public class JugadorMaquina extends Jugador{

    //Profundidad hasta la que se va a desarrollar el árbol de juego
    private final static int NIVEL_DEFECTO = 8;
    
    //Variables del tipo de jugador
    private final static int MIN = 1;
    private final static int MAX = 2;
    
    //Monitorizador de llamadas a V
    private static int LLAMADAS_V;

    //Constructor
    public JugadorMaquina(int jugador)
    {
        super(jugador);
        LLAMADAS_V = 0;
    }

    /**
     * Función que se ejecuta en el thread
     */
    @Override
    public void run()
    {
        //Llama a la función Minimax que implementa el algoritmo para calcular la jugada
        minimax();
        
        //No borrar esta línea!!
        isDone(true);
    }
    
    /**
     * Se debe determinar la mejor jugada mediante Minimax. El tablero de juego se
     * encuentra en la variable m_tablero.
     * Al final de la función de la variable m_columna debe contener la tirada.
     */
    public void minimax() {
        int max = Integer.MIN_VALUE, max_i = -1;
        for (int i = 0; i < m_tablero.numColumnas(); i++) {
            Tablero sucesor = new Tablero(m_tablero); //Genero los sucesores
            if(sucesor.ponerFicha(i, MAX) == 0) { //Si he podido poner una ficha en la columna i
                //Elijo el tipo de función dependiendo de si he elegido con poda o sin ella
                int actual = Interfaz.ALPHABETA_TOGGLE ? alphaBetaV(sucesor, MIN, 1, Integer.MIN_VALUE, Integer.MAX_VALUE) : V(sucesor, MIN, 1);
                if (actual > max) { //Actualizo valores si he encontrado un movimiento mejor
                    max = actual;
                    max_i = i;
                }
            }
        }
        
        int columna = max_i;
        boolean buenaTirada = false;
        while(!buenaTirada) {
            if(m_tablero.comprobarColumna(columna)!=-1) {
                buenaTirada = true;
                m_columna = columna;
            } else {
                columna = (int) (Math.random()*m_tablero.numColumnas()); //Sólo si he perdido no sabe qué columna coger
            }
        }
        System.out.println(/*"Total llamadas a V: " +*/ LLAMADAS_V);
     }

    private int V(Tablero tablero, int jugador, int nivel) {
        LLAMADAS_V++;
        //Casos base:
        if (tablero.cuatroEnRaya() == MAX) return Integer.MAX_VALUE;
        if (tablero.cuatroEnRaya() == MIN) return Integer.MIN_VALUE;
        if (tablero.tableroLleno()) return 0;
        if (nivel == NIVEL_DEFECTO) return f(tablero);
        
        //Caso general:
        //Extremo -> Palabra que engloba MÁX y MIN en una sola
        int extremo = jugador == MAX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < tablero.numColumnas(); i++) {
            Tablero sucesor = new Tablero(tablero);
            if (sucesor.ponerFicha(i, jugador) == 0)
                if (jugador == MAX) extremo = Math.max(extremo, V(sucesor, MIN, nivel + 1));
                else extremo = Math.min(extremo, V(sucesor, MAX, nivel + 1));
        }
        return extremo;
    }
    
    private int alphaBetaV(Tablero tablero, int jugador, int nivel, int alpha, int beta) {
        LLAMADAS_V++;
        //Casos base:
        if (tablero.cuatroEnRaya() == MAX) return Integer.MAX_VALUE;
        if (tablero.cuatroEnRaya() == MIN) return Integer.MIN_VALUE;
        if (tablero.tableroLleno()) return 0;
        if (nivel == NIVEL_DEFECTO) return f(tablero);
        
        //Caso general:
        int extremo = jugador == MAX ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for(int i = 0; i < tablero.numColumnas(); i++) {
            Tablero sucesor = new Tablero(tablero);
            if(sucesor.ponerFicha(i, jugador) == 0) {
                if(jugador == MAX) {
                    alpha = Math.max(alpha, alphaBetaV(sucesor, MIN, nivel + 1, alpha, beta));
                    if(alpha >= beta) return beta; //Si la condición de la poda no se cumple, no voy a encontrar nada mejor
                    extremo = alpha;
                } else { //MIN
                    beta = Math.min(beta, alphaBetaV(sucesor, MAX, nivel + 1, alpha, beta));
                    if(alpha >= beta) return alpha; //Si la condición de la poda no se cumple, no voy a encontrar nada peor
                    extremo = beta;
                }
            }
        }
        return extremo;
    }

    private int f(Tablero t) {
        int jugador, total = 0;
        if (numFichasMayorQue(t, 3)) {
            for (int i = 0; i < t.numFilas(); i++) {
                for (int j = 0; j < t.numColumnas(); j++) {
                    int puntos = 0;
                    jugador = t.obtenerCasilla(i, j);
                    if (jugador == 0) jugador = MAX; //Si la casilla está vacía, simulo ser el jugador MAX
                    //3 en raya potencial
                    puntos += 3 * comprobarFichas(i, j, 0, jugador, jugador, t);
                    puntos += 3 * comprobarFichas(i, j, jugador, 0, jugador, t);
                    puntos += 3 * comprobarFichas(i, j, jugador, jugador, 0, t);
                    //2 en raya potencial
                    puntos += 2 * comprobarFichas(i, j, jugador, 0, 0, t);
                    puntos += 2 * comprobarFichas(i, j, 0, jugador, 0, t);
                    puntos += 2 * comprobarFichas(i, j, 0, 0, jugador, t);
                    //1 en raya potencial -> Sólo si no es casilla vacía
                    if (t.obtenerCasilla(i, j) != 0) puntos += 1 * comprobarFichas(i, j, 0, 0, 0, t);
                    if(t.obtenerCasilla(i, j) == 0) puntos /= 2; //Si la casilla está vacía, la puntuación es la mitad
                    if(jugador == MIN) puntos = -puntos; //Si el jugador es el oponente; la puntuación es negativa
                    total += puntos;
                }
            }
        } else { //JUGADAS DE LIBRO -> Sólo si estoy en las primeras jugadas con NIVEL_DEFECTO = 2
            //System.out.println("Jugadas de libro");
            if(t.obtenerCasilla(0, 3) == MIN) { //Jugador 1 ha empezado poniendo en el medio
                if (t.obtenerCasilla(0, 2) == MAX || t.obtenerCasilla(0, 4) == MAX) total = 2; //BUENO
            } else if (t.obtenerCasilla(0, 3) == MAX) total = 3; //Jugador 1 no empieza por en medio y yo pongo en medio -> MUY BUENO
        }
        //System.out.println(t + "ha obtenido " + total + " puntos\n");
        return total;
    }
    
    private static int comprobarFichas(int i, int j, int uno, int dos, int tres, Tablero t) {
        int posibilidades = 0;
        if (vertical(i, j, uno, dos, tres, t)) posibilidades++;
        if (horizontal(i, j, uno, dos, tres, t)) posibilidades++;
        if (diagonal(i, j, uno, dos, tres, t)) posibilidades++;
        if (diagonalInv(i, j, uno, dos, tres, t)) posibilidades++;
        return posibilidades;
    }
    
    private static boolean vertical(int i, int j, int segunda, int tercera, int cuarta, Tablero t) {
        return i+3 < t.numFilas() ? comprobarVecinos(t.obtenerCasilla(i+1, j), t.obtenerCasilla(i+2, j), t.obtenerCasilla(i+3, j), segunda, tercera, cuarta) : false;
    }
    
    private static boolean horizontal(int i, int j, int segunda, int tercera, int cuarta, Tablero t) {
        return j+3 < t.numColumnas() ? comprobarVecinos(t.obtenerCasilla(i, j+1), t.obtenerCasilla(i, j+2), t.obtenerCasilla(i, j+3), segunda, tercera, cuarta) : false;
    }
    
    private static boolean diagonal(int i, int j, int segunda, int tercera, int cuarta, Tablero t) {
        return i+3 < t.numFilas() && j+3 < t.numColumnas() ? comprobarVecinos(t.obtenerCasilla(i+1, j+1), t.obtenerCasilla(i+2, j+2), t.obtenerCasilla(i+3, j+3), segunda, tercera, cuarta) : false;
    }
    
    private static boolean diagonalInv(int i, int j, int segunda, int tercera, int cuarta, Tablero t) {
        return i-3 >= 0 && j+3 < t.numColumnas() ? comprobarVecinos(t.obtenerCasilla(i-1, j+1), t.obtenerCasilla(i-2, j+2), t.obtenerCasilla(i-3, j+3), segunda, tercera, cuarta) : false;
    }
    
    private static boolean comprobarVecinos(int v1, int v2, int v3, int c1, int c2, int c3) {
        return v1 == c1 && v2 == c2 && v3 == c3;
    }
    
    private boolean numFichasMayorQue(Tablero t, int n) {
        int count = 0;
        for (int i = 0; i < t.numFilas(); i++)
            for (int j = 0; j < t.numColumnas(); j++)
                if (t.obtenerCasilla(i, j) != 0 && ++count > n) return true;
        return false;
    }
}

