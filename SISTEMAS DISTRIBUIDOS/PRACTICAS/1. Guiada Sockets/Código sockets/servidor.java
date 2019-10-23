import java.io.*;
import java.net.*;

public class servidor {

	/*
	* Lee datos del socket. Supone que se le pasa un buffer con hueco 
	*	suficiente para los datos. Devuelve el numero de bytes leidos o
	* 0 si se cierra fichero o -1 si hay error.
	*/
	public String leeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			InputStream aux = p_sk.getInputStream();
			DataInputStream flujo = new DataInputStream( aux );
			p_Datos = new String();
			p_Datos = flujo.readUTF();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
      return p_Datos;
	}

	/*
	* Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
	* o -1 si hay error.
	*/
	public void escribeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			OutputStream aux = p_sk.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( aux );
			flujo.writeUTF(p_Datos);      
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
	}
	
	public int sumar(int p_a, int p_b)
	{
		return p_a+p_b;
	}

	public int multiplicar(int p_a, int p_b)
	{
		return p_a*p_b;
	}

	public int realizarOperacion(String p_Cadena)
	{
		String[] operacion = p_Cadena.split(",");
		int res=0;
		
		System.out.println("SRV: La operacion es: " + operacion[0]);
		if(operacion.length != 1)
		{
			System.out.println("SRV: El operando 1 es " + operacion[1] + " y el operando 2 es " + operacion[2]);
			if(operacion[0].compareTo("suma")==0)
			{
				res = sumar(Integer.parseInt(operacion[1]),Integer.parseInt(operacion[2]));
			}
			else
			{
				if(operacion[0].compareTo("mult")==0)
				{
					res = multiplicar(Integer.parseInt(operacion[1]),Integer.parseInt(operacion[2]));	
				}	
				else
				{
					
					res = -1;
				}
			}
			System.out.println("SRV: El resultado es: " + res);
		}else
		{
			res = -1;
		}	
		return (res);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		* Descriptores de socket servidor y de socket con el cliente
		*/
		int resultado=0;
		String Cadena="";
		String puerto="";
		try
		{
			servidor sr = new servidor();
			if (args.length < 1) {
				System.out.println("Debe indicar el puerto de escucha del servidor");
				System.out.println("$./Servidor puerto_servidor");
				System.exit (1);
			}
			puerto = args[0];
			ServerSocket skServidor = new ServerSocket(Integer.parseInt(puerto));
		    System.out.println("Escucho el puerto " + puerto);
	
			/*
			* Mantenemos la comunicacion con el cliente
			*/	
			for(;;)
			{
				/*
				* Se espera un cliente que quiera conectarse
				*/
				Socket skCliente = skServidor.accept(); // Crea objeto
		        System.out.println("Sirviendo cliente...");
				while (resultado != -1)
				{
					Cadena = sr.leeSocket (skCliente, Cadena);
					/*
					* Se escribe en pantalla la informacion que se ha recibido del
					* cliente
					*/
					resultado = sr.realizarOperacion(Cadena);
					Cadena = Integer.toString(resultado);
					sr.escribeSocket (skCliente, Cadena);						
				}
				
				skCliente.close();
				System.exit(0);				
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}

}
