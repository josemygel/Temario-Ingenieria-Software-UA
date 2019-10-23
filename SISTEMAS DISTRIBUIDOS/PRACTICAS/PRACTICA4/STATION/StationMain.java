//package com.autentia.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
//import java.util.Random;
import java.lang.*;
import java.io.*;


public class StationMain {

	public static void main(String[] args) throws Exception {
		final String remoteHost = args[0];
		final String StationRmiName = args[1];
		
		final Registry registry = LocateRegistry.getRegistry(remoteHost, Registry.REGISTRY_PORT);
		final Station Station = new Station(StationRmiName);

		System.out.println("Try to register " + Station.getRmiName()
				+ " in remote Registry (only works if Registry was created in the same host).");
		try {
			registry.rebind(Station.getRmiName(), Station);

		} catch (RemoteException e) {
			// No puedes hacer bind, rebind, or unbind de un objeto remoto
			// en un Registry que ha sido creado en otro host.
			e.printStackTrace();
		}

		System.out.println("Register " + Station.getRmiName() + " through the Master.");
		
		final MasterServices master = (MasterServices)registry.lookup(registry.list()[0]);
		master.registerStation(Station);
	}
}

interface MasterServices extends ServerServices {

	public void registerStation(StationServices Station) throws RemoteException;
}

interface ServerServices extends Remote {
	public void sayHelloWorld() throws RemoteException;
	public String[] getData() throws RemoteException;
}

interface StationServices extends ServerServices {

	public String getRmiName() throws RemoteException;
}




class Station extends UnicastRemoteObject implements StationServices {

	private static final long serialVersionUID = 1L;

	private final String rmiName;

	protected Station(String rmiName) throws RemoteException {
		super();
		this.rmiName = rmiName;
	}

	    // Metodo para obtener de un FileInputStream y una codificacion un String
    // (obtener el HTML)
    private String getFileContent(FileInputStream fis, String encoding) throws IOException {
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


	public void sayHelloWorld() throws RemoteException {
		System.out.println("I'm the Station " + getRmiName() + ", and I say: Hellow world !!!");
	}


	public String[] getData() throws RemoteException {
		String[] arr = new String[4];

		try{
			File af = new File(getRmiName()+".txt");
			BufferedWriter bw;
			if(!af.exists())
			{      
				bw = new BufferedWriter(new FileWriter(af));
				Integer temp = 35;
				String temperatura = temp.toString();
				temp = 30;
				String humedad =temp.toString();
				temp = 500;
				String luminosidad = temp.toString();
				String lcd = "Estacion sin descripcion.";
				bw.write("temperature:"+temperatura+"\nhumidity:"+humedad+"\nluminity:"+luminosidad+"\nLCD:"+lcd);
				bw.close();
			}
			
			FileInputStream archivo = new FileInputStream(getRmiName()+".txt");

			String content = getFileContent(archivo, "UTF8");

			String[] text = content.split("\n");
			
		
			for(int i = 0; i<=3 && i<text.length; i++)
				arr[i] = text[i].split(":")[1];
			//Integer.parseInt(text[i].split(":")[1])

			//Control temperatura
			arr[0] = Integer.parseInt(arr[0]) < -30 ? "-30" : arr[0];
			arr[0] = Integer.parseInt(arr[0]) >  50 ?  "50" : arr[0];

			//Control de humedad
			arr[1] = Integer.parseInt(arr[1]) < 0 ?   "0" : arr[1];
			arr[1] = Integer.parseInt(arr[1]) > 100 ? "100" : arr[1];

			//Control de luz
			arr[2] = Integer.parseInt(arr[2]) < 0 ?   "0" : arr[2];
			arr[2] = Integer.parseInt(arr[2]) > 800 ? "800" : arr[2];

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return arr;
	}

	public String getRmiName() throws RemoteException {
		return rmiName;
	}
}
