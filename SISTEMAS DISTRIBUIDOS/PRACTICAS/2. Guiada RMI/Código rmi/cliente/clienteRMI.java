import java.io.*;
import java.rmi.*;

public class clienteRMI {
    

    /** Creates a new instance of cliente_rmi */
    public clienteRMI() {
    }

    private int pedirNumeros(int operacion, int resultado, InterfazRemoto objetoRemoto)
    {
            int op1 = 10;
            int op2 = 10;
            InputStreamReader ent = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(ent);

            while (op1 < 0 || op1 > 9)
            {
                System.out.println("\nIntroduzca el primer operando [0-9]:");
                try
                {
                    op1 = new Integer(buf.readLine()).intValue();
                }
                catch(Exception e)
                {
                    op1 = 10;
                }
            }
            while (op2 < 0 || op2 > 9)
            {
                System.out.println("\nIntroduzca el segundo operando [0-9]:");
                try
                {
                    op2 = new Integer(buf.readLine()).intValue();
                }
                catch(Exception e)
                {
                    op2 = 10;
                }
            }
            try
            {
                if (operacion == 1)
                {
                        resultado = objetoRemoto.suma(op1,op2);
                }
                else
                {
                        if (operacion == 2)
                        {
                                resultado = objetoRemoto.multiplicacion(op1,op2);
                        }
                }
            }
            catch(Exception exc)
            {
                System.out.println("Error al realizar la operacion "+exc);
            }
            return resultado;
    }

    private void pedirOperacion(String host, String port, InterfazRemoto objetoRemoto)
    {
            int operacion;
            int salir = 0;
            int resultado = 0;
            char resp = 'x';
            InputStreamReader ent = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(ent); 
            
            
            try
            {
                if(objetoRemoto == null){
                    System.setSecurityManager(new RMISecurityManager());
                    String servidor = "rmi://"+host+":"+port+"/ObjetoRemoto";
                    objetoRemoto = (InterfazRemoto) Naming.lookup(servidor);
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error al instanciar el objeto remoto "+ex);
                System.exit(0);
            }
            while (salir == 0)
            {
                    operacion = 0;
                    while (operacion !=1 && operacion !=2)
                    {
                        System.out.println("[1] Sumar");
                        System.out.println("[2] Multiplicar");
                        System.out.println("Indica la operacion a realizar: ");
                        try
                        {
                            operacion = new Integer(buf.readLine()).intValue();
                        }
                        catch(Exception e)
                        {
                            operacion = 0;
                        }
                    }
                    resultado = pedirNumeros(operacion, resultado, objetoRemoto);
                    resp='x';
                    while(resp != 's' && resp != 'n')
                    {
                        System.out.println("El resultado es: "+resultado);
                        System.out.println("Desea realizar otra operacion? [s,n]: ");
                        try
                        {
                            resp = buf.readLine().charAt(0);
                        }
                        catch(Exception e)
                        {
                            resp = 'x';
                        }
                    }
                    if (resp != 's')
                    {
                            salir = 1;
                    }
            }
            //objetoRemoto = null;
            return;
    }

    
    private void menu(String host, String port, InterfazRemoto objetoRemoto)
    {
            int opc = 0;
            
            InputStreamReader ent = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(ent);
            while (true)
            {
                System.out.println("[1] Realizar operacion");
                System.out.println("[2] Salir");
                System.out.println("Indique la opcion a realizar:");

                try
                {
                    opc = new Integer(buf.readLine()).intValue();
                }
                catch(Exception e)
                {
                    opc=2;
                }
                
                if (opc == 1)
                    pedirOperacion(host,port,objetoRemoto);
                else System.exit(opc);
            }
    }    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        InterfazRemoto objetoRemoto = null;
        String host;
            String port;
            clienteRMI cr = new clienteRMI();
        int i = 0;
        if (args.length < 2) {
            try
            {
                System.out.println("Debe indicar la direccion del servidor");
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e);
            }
            return;
        }
        host = args[0];
        port = args[1];
    
        cr.menu(host,port,objetoRemoto);
    }
}
