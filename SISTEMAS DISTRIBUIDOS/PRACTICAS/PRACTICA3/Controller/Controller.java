import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

import javax.print.attribute.standard.Finishings;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import java.lang.System;


/**
 *
 * @author Jose Miguel Gómez Lozano
 * @email josemygel@gmail.com
 */

public class Controller {
	private static int PUERTO = 9000;
	private static String remoteHost;
	private static Registry registry;
	private static ArrayList<Station> stations = new ArrayList<Station>();


	private static class Station{
		public int temperature; 
		public int humidity;  
		public int luminity; 
		public String LCD;

		public Station(String[] arr){
			this.temperature = Integer.parseInt(arr[0]);
			this.humidity = Integer.parseInt(arr[1]);
			this.luminity = Integer.parseInt(arr[2]);
			this.LCD = arr[3];
		};
	 };

    public static void main(String[] args) throws Exception {
		if(args.length < 1)
		{
			System.out.println("You should use \"Controller RegistryIP(NotDefault) ControllerPORT(Default=9000)\"\n");
			return;
		}
		else
		{
			remoteHost = args[0];
			if(args.length > 1)
				PUERTO = Integer.parseInt(args[1]);
		}

		registry = LocateRegistry.getRegistry(remoteHost, Registry.REGISTRY_PORT);
        
        try {
			ServerSocket socketServidor = new ServerSocket(PUERTO);
			
            while (true) {
                Socket socketCliente = socketServidor.accept();
                ThreadController hilo = new ThreadController(socketCliente, registry);
                hilo.start();
            }
        } catch (Exception ex) {
            //Logger.getLogger(ServerWeb.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println();
        }
    }
}

class ThreadController extends Thread {
	//Class Station funcionará como estructura para trabajar sus datos

	
	private class Station{
		public String name;
		public int temperature; 
		public int humidity;  
		public int luminity; 
		public String LCD;
		
		public Station(String[] arr, String name){
			if(arr.length > 0)
				this.temperature = Integer.parseInt(arr[0]);

			if(arr.length > 1)
				this.humidity = Integer.parseInt(arr[1]);

			if(arr.length > 2)
				this.luminity = Integer.parseInt(arr[2]);
				
			if(arr.length > 3)
				this.LCD = arr[3];
			
			this.name = name;
		};
	 };
	
	private Socket socketClient;
	private static Registry registry;

	public ThreadController(Socket p_cliente, Registry reg){        
		try {
			this.registry = reg;
			this.socketClient = p_cliente;
		} catch (Exception ex) {
			//Logger.getLogger(ServerWeb.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error: " + ex.getMessage());
			System.err.println();
		}
	}
	

	public ArrayList<Station> getStations(){
		//Hacemos limpieza de array
		ArrayList<Station> stations = new ArrayList<Station>();

		try{

			final String[] remoteObjNames = registry.list();
						
			//TEMPORAL !!!
			for (String remoteObjName : remoteObjNames) {

				Object obj = this.registry.lookup(remoteObjName);
				
				if (remoteObjName.indexOf("Master") == -1 && obj instanceof ServerServices) {
					
					//Insertamos una estación si no fue eliminada del registro
					System.out.println("\nLlamando al objeto remoto: " + remoteObjName);
					
					try{
						//Master emite una excepcion por lo que no se agrega
						ServerServices station = (ServerServices)obj;

						//Creamos la clase estacion
						Station temp = new Station(station.getData(),remoteObjName);

						stations.add(temp);

						System.out.println("\tTemperatura: " + temp.temperature);
						System.out.println("\tHumedad: " + temp.humidity);
						System.out.println("\tLuminosidad: " + temp.luminity);
						System.out.println("\tLDC: " + temp.LCD);
					}catch(Exception ex){
						System.out.println("\tLa estación está apagada o es la Master.");
					}
				}

			}//HASTA AQUI, PARA VER LOS OBJETOS REMOTOS!!!
		}catch(Exception ex){
			System.out.println("This station was deleted!");
		}

		return stations;
	}

    // Metodo que coger un String origen, un string a buscar (remp) y otro por el
    // que va a sustituirlo (newValue)
    public String remplacer(String origen, String remp, String newValue) {
        String string = origen.indexOf(remp) == -1 ? origen
                : origen.substring(0, origen.indexOf(remp)) + newValue
                        + origen.substring(origen.indexOf(remp) + remp.length());

        return string;
    }

    // Metodo para obtener de un FileInputStream y una codificacion un String
    // (obtener el HTML)
    public String getFileContent(FileInputStream fis, String encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }


	/*
	* Lee datos del socket. Supone que se le pasa un buffer con hueco 
	*	suficiente para los datos. Devuelve el numero de bytes leidos o
	* 0 si se cierra fichero o -1 si hay error.
	*/
	public String leeSocket (Socket socketClient)
	{
		String datos = "";
		try
		{
			InputStream input = socketClient.getInputStream();
			DataInputStream flujo = new DataInputStream(input);

			datos = flujo.readUTF();
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		System.out.println("leeSocket.datos = " + datos);
      return datos;
	}

	/*
	* Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
	* o -1 si hay error.
	*/
	public void escribeSocket (Socket socketClient, String p_Datos)
	{
		try
		{
			//System.out.println("Socket sending " + p_Datos);
			OutputStream output = socketClient.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( output );
			flujo.writeUTF(p_Datos);      
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
	}

	public String getInfo(String ruta)
	{
		String error = "";
		
		//Aqui obtendremos la nueva ruta
		if(ruta.equalsIgnoreCase("index.html") || ruta.equalsIgnoreCase("/") || ruta.isEmpty())
		{
			ruta = "index.html";
			return "";
		}
		else
		{//ANALIZAMOS LOS CASOS DE LA RUTA SI NO SE TRATA DE LA RAIZ
			String parametro = ruta.indexOf('?') != -1 ? ruta.substring(0,ruta.indexOf('?')) : "";
			if(!parametro.isEmpty()){
				if(!parametro.equalsIgnoreCase("temperatura") && !parametro.equalsIgnoreCase("humedad") 
				&& !parametro.equalsIgnoreCase("luminosidad") && !parametro.equalsIgnoreCase("lcd"))
				{
					//ERROR DE PARAMETROS!!
					error = "ERROR 404: ERROR DE PARAMETROS, PARAMETRO " + parametro + " NO EXISTE";
				}
			}
		}

		//Si hubiera un error, entonces true
		return error;
	}

	public String getStations(String ruta)
	{
		FileInputStream archivo;
		String content = "";

		ArrayList<Station> stations = new ArrayList<Station>();
		stations = getStations();
		
        // Preparamos la escritura del fichero en el output
        try {
			//Si no se obtuvo la info correcta
			String error = getInfo(ruta);
			if(!error.isEmpty())
			{
				archivo = new FileInputStream("error.html");
				content = getFileContent(archivo, "UTF8");

				content = remplacer(content, "$error", error);
				archivo.close();
			}
			else if (ruta.equalsIgnoreCase("index.html"))// Estacion no seleccionada/ID no especificado (TRABAJAMOS CON EL SERVER WEB)
			{
                archivo = new FileInputStream("index.html");
				content = getFileContent(archivo, "UTF8");
				
				for(int i = 1; i <= 5; i++){
					if(i > stations.size())
						content = remplacer(content,"$station"+i,"No Station");
					else
						content = remplacer(content,"$station"+i,"<div onclick=\"location.href=\'/controladorSD/station="+i+"\';\">Station \"" +stations.get(i-1).name+ "\" in service</div>");
				}

                archivo.close();
			}
			else // SI SE ESPECIFICA STATION, SE OBTIENE EL RESULTADO
            {

				String parametro = ruta.indexOf('?') != -1 ? ruta.substring(0,ruta.indexOf('?')) : "";
				Integer stationID = ruta.indexOf("station=") != -1 ? Integer.parseInt(ruta.substring(ruta.indexOf("station=") + "station=".length())) : -1;

				archivo = new FileInputStream("station.html");
				content = getFileContent(archivo, "UTF8");
				
				//INSETAMOS EL ID
				content = remplacer(content, "$id", stationID.toString());
				stationID--;
				System.out.println("stations: " + stations.size() + " and searching stationID:" + stationID);
				//INSETAMOS LA TEMPERATURA
				content  = parametro.isEmpty() || parametro.equalsIgnoreCase("temperatura") ? 
						remplacer(content,"$temperature", "<li><mark>TEMPERATURA: " + Integer.toString(stations.get(stationID).temperature) + "&deg;C</li></mark>") :
						remplacer(content,"$temperature", " ") ;
				//INSERTAMOS LA HUMEDAD
				content  = parametro.isEmpty() || parametro.equalsIgnoreCase("humedad") ? 
						remplacer(content,"$humidity", "<li><mark>HUMEDAD: " + Integer.toString(stations.get(stationID).humidity) + "</li></mark>") :
						remplacer(content,"$humidity", " ") ;
				//INSERTAMOS LA LUMINOSIDAD
				content  = parametro.isEmpty() || parametro.equalsIgnoreCase("luminosidad") ? 
						remplacer(content,"$luminity", "<li><mark>LUMINOSIDAD: " + Integer.toString(stations.get(stationID).luminity) + "</li></mark>") :
						remplacer(content,"$luminity", " ") ;
				//INSERTAMOS EL LCD
				content  = parametro.isEmpty() || parametro.equalsIgnoreCase("lcd") ? 
						remplacer(content,"$lcd", "<li class=\"lcd\" style=\"\"><mark>PANTALLA LCD:<br>" + stations.get(stationID).LCD + "</li></mark>") :
						remplacer(content,"$lcd", " ") ;
						//YA HEMOS COMPROBADO LOS PARAMETROS ANTES

                archivo.close();
            }
        } catch (Exception ex) {
            // Si el fichero no existe, enviamos un error 404
            //Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println("Se ha detectado un error 404");
			System.err.println();
			content = "ERROR 404: SATION NOT FOUND!";
        }

		return content;
	}
	
	
	/**
	 * Metodo de un thread, cuando el thread hace .start() comienza con este metodo
	 */
    public void run() {
		String ruta = "";
		String content = "";

        try {
			System.out.println("¡Conexión abierta!");
			ruta = leeSocket(this.socketClient);
			System.out.println("Ruta = \"" + ruta + "\"");
			/*
			* Se escribe en pantalla la informacion que se ha recibido del
			* cliente
			*/
			System.out.println("Sirviendo para ruta: " + ruta);
			content = getStations(ruta);
			
			escribeSocket (socketClient, content);						
            
			socketClient.close();
			//System.exit(0); No se debe poner esta sentencia, porque en ese caso el primer cliente que cierra rompe el socket 
			//				  y desconecta a todos				
        }
        catch (Exception e) {
          System.out.println("Error: " + e.toString());
        }
      }
}


/**
 * DEPENDENCIAS DEL RMI
 */
interface StationServices extends ServerServices{
	public String getRmiName() throws RemoteException;
}
interface MasterServices extends ServerServices{
	public void registerStation(StationServices Station) throws RemoteException;
}
interface ServerServices extends Remote{
    public void sayHelloWorld() throws RemoteException;
	public String[] getData() throws RemoteException;
}
