package ppss;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FactoriaBOs {
    public static IOperacionBO op;
    public IOperacionBO getOperacionBO() {
        if(op!=null)
            return op;
        throw new UnsupportedOperationException("getOperacionBO Not yet implemented");
    }
}
