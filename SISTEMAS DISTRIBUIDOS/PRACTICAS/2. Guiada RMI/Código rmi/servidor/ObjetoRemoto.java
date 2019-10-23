import java.io.Serializable; 
import java.rmi.*;
import java.rmi.server.*;

public class ObjetoRemoto extends UnicastRemoteObject
implements InterfazRemoto, Serializable 
{     
	public ObjetoRemoto () throws RemoteException 
	{
		super();
	}
	public int suma(int a, int b)      
    {         
        return a+b;     
    } 
    
    public int multiplicacion(int a,int b)
    {
        return a*b;
    }
}
