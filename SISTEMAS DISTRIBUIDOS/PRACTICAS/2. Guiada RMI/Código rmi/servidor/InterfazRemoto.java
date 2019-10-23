import java.rmi.Remote;

public interface InterfazRemoto extends Remote {
    public int suma (int a, int b) throws java.rmi.RemoteException;
    public int multiplicacion (int a, int b) throws java.rmi.RemoteException;
}
