/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEstrella;

import static java.lang.Math.*;
import java.util.ArrayList;
/*
import java.util.Comparator;
import java.util.PriorityQueue;*/

/**
 *
 * @author mirse
 */
public class AEstrella {
 
    //CLASE NODO PARA TRABAJO DE AESTRELLA
    protected class Nodo extends Coordenada{
    //Parent es el Nodo del que accedimos a este
    public Nodo parent = null;
    //Coste determina el coste para acceder a la casilla
    public int cost = 0;
    //Piedra 'p' y Bosque 'b' no son accesibles
    public boolean accessible = true;
    //Distancia desde la casilla al dragon
    private int distance = 0;
    
    public Nodo(int X, int Y)
    {
        x = X;
        y = Y;
    }

    public Nodo(int X, int Y, char celda)
    {
        x = X;
        y = Y;
        
        switch(celda){
            case 'k':
                accessible = false;
                cost = 0;
            break;
            case 'd':
                cost = 1;
            break;
            case 'c':
                cost = 1;
            break;
            case 'h':
                cost = 2;
            break;
            case 'a':
                cost = 3;
            break;
            default:
                accessible = false;
            break;
        }
    }
    /**
     * 
     * @param dragon
     * @return Usa la heurística h = 0
     */
    public int h0(Coordenada dragon)
    {
        return 0;
    }
    
    /**
     * 
     * @param dragon
     * @return Usa la heurística de Manhattan
     */
    public int hManhattan(Coordenada dragon)
    {
        return abs(dragon.getX()-x)+abs(dragon.getY()-y);
    }
    
    /**
     * 
     * @param dragon
     * @return Usa la heurística Euclidea
     */
    public int hEuclidea(Coordenada dragon)
    {
        return (int)sqrt((abs(dragon.getX()-x)^2)+(abs(dragon.getY()-y)^2));
    }
    
    
    /**
     * 
     * @param dragon
     * @return Usa la heurística de cubo
     */
    public int hCube(Coordenada dragon)
    {
        //x&1 quiere decir que si x es 1110 y 1 es 0001 pasara a ser 0000
        int X1 = y - (x - (x&1)) / 2;
        int Z1 = x;
        int Y1 = -X1-Z1;
        
        int X2 = dragon.getX() - (dragon.getX() - (dragon.getX()&1)) / 2;
        int Z2 = dragon.getX();
        int Y2 = -X2-Z2;
      
        int X = abs(X1-X2);
        int Y = abs(Y1-Y2);
        int Z = abs(Z1-Z2);
        
        return (int)max(X,max(Y,Z));
    }
    
    /**
     * @param dragon
     * @return distancia en "linea recta" hasta llegar a la coordenada dragon
     * Este método es "inventado" por mí, tras analizar el mapa es el que me
     * parece más intuitivo y directo.
     */
    public int hCustom(Coordenada dragon)
    {
          int Y = y;
        int X = x;
        
        //Si ya se calculó la distancia al dragon no se calcula de nuevo
        if(distance != 0)
            return distance;
        
        while(Y != dragon.getY())
        {
            //ODD == IMPAR
            boolean even = (Y%2 != 0);
            
            //BAJAMOS/SUBIMOS
            Y = (Y < dragon.getY()) ? ++Y : --Y;
            
            //Si es IMPAR y vamos a la DERECHA X++
            if(even && X < dragon.getX()) X++;
            
            //Si es PAR y vamos a la IZQUIERDA X--
            else if(!even && X > dragon.getX()) X--;
            
            //Discancia++
            distance++;
        }
        
        //Finalmente, sumamos la distancia de X al objetivo (Y==getY())
        return distance += abs(dragon.getX()-X);
    }
    /**
     * 
     * @param dragon
     * @return heuristica a elegir
     * COMENTARIOS CON X NODOS CON EL MAPA DE EJEMPLO EN LA DOCUMENTACION
     * 
        b b b b b b b b b b b b b b
        b c k h h h h c c c c c c b
        b c c h a a h c c c c c c b
        b c c h h a h c c c c c c b
        b c c c h h h c c c c c c b
        b c c p c h p c p p c c c b
        b c c c c c h h h h h c c b
        b c c p c c h a a a h d c b
        b c c c c c h a a h h h c b
        b b b b b b b b b b b b b b
     * 
     */
    public int h(Coordenada dragon)
    {
        int solucion = 0;
      //H0 GENERA DEMASIADO NODOS
      //MapCustom_2: Genera 89 Nodos con Coste 17
      //MapCustom_3: Genera 82 Nodos con Coste 17
      //EJ: 85 Nodos, 17 Coste
      //solucion = h0(dragon);
      
      //NO ADMISIBLE!
      //MANHATTAN NO DEVUELVE LA SOLUCION OPTIMA!
      //MapCustom_2: Genera 58 Nodos con Coste 18
      //MapCustom_3: Genera 47 Nodos con Coste 17
      //EJ: 52 Nodos, 18 Coste
      //solucion = hManhattan(dragon);
      
      //EUCLIDEA TIENE MUCHOS NODOS
      //MapCustom_2: Genera 90 Nodos con Coste 17
      //MapCustom_3: Genera 83 Nodos con Coste 17
      //EJ: 87 Nodos, 17 Coste
      //solucion = hEuclidea(dragon);
      
      //BUENA PERO FALTA SU TESTEO E IMPLEMENTACION
      //EJ: 69 Nodos, 17 Coste
      //solucion = hCube(dragon);
      
      //BUENA HEURISTICA! POCOS NODOS Y BUEN COSTE!
      //MapCustom_2: Genera 60 Nodos con Coste 17
      //MapCustom_3: Genera 51 Nodos con Coste 17
      //EJ: 69 Nodos, 17 Coste
      solucion =  hCustom(dragon);
      
      return solucion;
    }
    /**
     * 
     * @return Devuelve un Int que significa el coste para llegar a esa casilla
     */
    public int g(){
        //0 significa que es de donde parte el caballero
        return (parent == null) ? cost : cost + parent.g();
    }
    
    public String toString(){
        return "POS: " + x + ":" + y;
    }
    
    public int f(){
        return this.g()+this.h(mundo.dragon);
    }
    
    public boolean equals(Nodo n){return x==n.getX() && y==n.getY();}
}

    //Mundo sobre el que se debe calcular A*
    Mundo mundo;
    
    //Camino
    public char camino[][];
    
    //Casillas expandidas
    int camino_expandido[][];
    
    //Número de nodos expandidos
    int expandidos;
    
    //Coste del camino
    float coste_total;
    
    public AEstrella(){
        expandidos = 0;
        mundo = new Mundo();
    }
    
    public AEstrella(Mundo m){
        //Copia el mundo que le llega por parámetro
        mundo = new Mundo(m);
        camino = new char[m.tamanyo_y][m.tamanyo_x];
        camino_expandido = new int[m.tamanyo_y][m.tamanyo_x];
        expandidos = 0;
        
        //Inicializa las variables camino y camino_expandidos donde el A* debe incluir el resultado
            for(int i=0;i<m.tamanyo_x;i++)
                for(int j=0;j<m.tamanyo_y;j++){
                    camino[j][i] = '.';
                    camino_expandido[j][i] = -1;
                }
    }
    
    //Calcula el A*
    public int CalcularAEstrella(){

        boolean encontrado = false;
        int result = -1;
       
        //AQUÍ ES DONDE SE DEBE IMPLEMENTAR A*
        Nodo n = new Nodo(mundo.caballero.getX(),mundo.caballero.getY(),'k');
        Nodo draco = new Nodo(mundo.dragon.getX(),mundo.dragon.getY(),'d');
        
        camino_expandido[n.getY()][n.getX()] = 0;
        
        /**
         *  listaInterior = vacioB
         *  listaFrontera = inicioB
         */
        ArrayList<Nodo> interList = new ArrayList<Nodo>();
        ArrayList<Nodo> frontList = new ArrayList<Nodo>();
        
        frontList.add(n);

        //mientras listaFrontera no esté vacíaB
        while(!frontList.isEmpty()){
            Nodo select=null;
            int selected = 0;
            
            //n = select
            //n = obtener nodo de listaFrontera con menor f(n) = g(n) + h(n)B
            //Seleccionamos el nodo de lista interior a trabajar
            for (int i=0; i<frontList.size(); i++)
            {
                if(select == null || frontList.get(i).f() < select.f())
                {
                    selected = i;
                    select = frontList.get(i);
                }
            }
            
            //listaFrontera.del(n) y listaInterior.add(n)
            interList.add(frontList.remove(selected));
            
            //si n es meta devolver
            if(select.getX() == mundo.dragon.getX() && select.getY() == mundo.dragon.getY())
            {
               encontrado = true;
               break;
            }
            
            //En las posiciones PARES no X+1
            //En las posiciones IMPRAES no X-1
            
            for(int i = 0; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    //Cogemos la altura en relacion al nodo seleccionado
                    int Y= select.getY()+j;
                    
                    //Si fuera x e y las mismas, cambiamos el valor X al anterior
                    int X = (j==0 && i==0) ? -1 : 0;
                    
                    //Lo que hace esto es, en caso par, cambiar
                    if(i!=0 && j!=0) X = select.getY()%2 == 0 ? 0 : -2; 
                    
                    X += select.getX()+i;
                    
                    Nodo temp = new Nodo(X,Y,mundo.getCelda(X, Y));
                    
                    temp.parent = select;
                    boolean ins = false;

                    if(temp.accessible){
                        //System.out.println(temp.toString());

                        for(Nodo aux : interList){
                            if(aux.equals(temp))
                            {
                                if(aux.parent.g() > select.g())
                                    aux.parent = select;
                                ins = true;
                            }
                        }
                        
                        //Si no esta insertado ya en la lista inerior...
                        if(!ins){
                            //Si esta en la lista frontera no hay que insertarlo
                            for(Nodo aux : frontList)
                            if(aux.equals(temp))
                            {
                                if(aux.parent.g() > select.g())
                                    aux.parent = select;
                                ins = true;
                            }
                        }
                        
                        //Si no esta en ninguna de las listas...
                        if(!ins){
                            frontList.add(temp);
                        }
                    }
                    
                }
            }
        }
        
        draco = new Nodo(mundo.dragon.getX(),mundo.dragon.getY(),'d');
        
        for(Nodo aux : interList)
            if(aux.equals(draco))
                draco = aux;
        //SI DRAGON ESTA EN LISTA INTERIOR EL INSERTAMOS COSTE_TOTAL Y RESULTADO
        if(encontrado) coste_total = result = draco.g();
        
        //INSERTAMOS LA CANTIDAD DE NODOS EXPANDIDOS
        expandidos = interList.size();

        //MARCAMOS EL CAMINO HASTA EL DRAGON SI ES QUE LLEGAMOS AL DRAGON
        while(draco.parent != null){
            camino[draco.getY()][draco.getX()] = 'X';
            draco = draco.parent;
        }
        
        //MARCAMOS EN EL MAPA EL CAMINO EXPANDIDO CON LOS COSTES DE CADA CASILLA EXPANDIDA
        for(Nodo aux : interList){
            camino_expandido[aux.getY()][aux.getX()] = aux.g();
        }
        
        //Si ha encontrado la solución, es decir, el camino, muestra las matrices camino y camino_expandidos y el número de nodos expandidos
        if(encontrado){
            //Mostrar las soluciones
            System.out.println("Camino");
            mostrarCamino();

            System.out.println("Camino explorado");
            mostrarCaminoExpandido();
            
            System.out.println("Nodos expandidos: "+expandidos);
        }

        return result;
    }
    

    
    
   
    
    //Muestra la matriz que contendrá el camino después de calcular A*
    public void mostrarCamino(){
        for (int i=0; i<mundo.tamanyo_y; i++){
            if(i%2==0)
                System.out.print(" ");
            for(int j=0;j<mundo.tamanyo_x; j++){
                System.out.print(camino[i][j]+" ");
            }
            System.out.println();   
        }
    }
    
    //Muestra la matriz que contendrá el orden de los nodos expandidos después de calcular A*
    public void mostrarCaminoExpandido(){
        for (int i=0; i<mundo.tamanyo_y; i++){
            if(i%2==0)
                    System.out.print(" ");
            for(int j=0;j<mundo.tamanyo_x; j++){
                if(camino_expandido[i][j]>-1 && camino_expandido[i][j]<10)
                    System.out.print(" ");
                System.out.print(camino_expandido[i][j]+" ");
            }
            System.out.println();   
        }
    }
    
    public void reiniciarAEstrella(Mundo m){
        //Copia el mundo que le llega por parámetro
        mundo = new Mundo(m);
        camino = new char[m.tamanyo_y][m.tamanyo_x];
        camino_expandido = new int[m.tamanyo_y][m.tamanyo_x];
        expandidos = 0;
        
        //Inicializa las variables camino y camino_expandidos donde el A* debe incluir el resultado
            for(int i=0;i<m.tamanyo_x;i++)
                for(int j=0;j<m.tamanyo_y;j++){
                    camino[j][i] = '.';
                    camino_expandido[j][i] = -1;
                }
    }
    
    public float getCosteTotal(){
        return coste_total;
    }
}


