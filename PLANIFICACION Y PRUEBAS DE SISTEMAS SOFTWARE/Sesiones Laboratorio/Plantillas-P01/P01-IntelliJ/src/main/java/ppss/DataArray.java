package ppss;


public class DataArray {
    private int[] coleccion;
    private int numElem;
    
    //Constructor
    public DataArray() {
        coleccion = new int[10];
        numElem=0;        
    }
    
    //Constructor
    public DataArray(int[] datos, int contador) {
        coleccion = datos;
        numElem=contador;        
    }
    
    public int size() {
        return numElem;
    }
    
    //getter
    public int[] getColeccion() {
        return coleccion;
    }
    
    //método para añadir un entero a la colección
    public void add(int elem) {
    if (numElem < (coleccion.length)) {
            coleccion[numElem]= elem;
            numElem++;
            System.out.println("added "+elem +" ahora hay "+numElem+ " elementos");
        } else {
            System.out.println(elem +" ya no cabe. Ya has añadido "+numElem+" elementos");
        } 
    }
    
    //método para borrar un entero a la colección
    public int[] delete(int elem) { return null;}
    
}
