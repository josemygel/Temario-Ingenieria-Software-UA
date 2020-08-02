package ppss;


import java.util.Arrays;
import java.util.Objects;

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
    public int[] delete(int elem) {
        boolean encontrado = false;

        for (int i=0; i<10; i++) {
            //Comprobamos si se ha encontrado el elemento
            encontrado = encontrado || coleccion[i] == elem;

            //Si se encontro, se procede a realizar el borrado de dicho elemento desplazando los siguientes
            if(encontrado)
                coleccion[i] = i==9 ? 0 : coleccion[i+1];
        }
        if(encontrado) numElem--;

        return coleccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataArray dataArray = (DataArray) o;

        for(int i=0; i<10; i++)
            if(coleccion[i]!=dataArray.coleccion[i])
                return false;

        return numElem == dataArray.numElem;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numElem);
        result = 31 * result + Arrays.hashCode(coleccion);
        return result;
    }
}
