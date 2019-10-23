//package com.autentia.rmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.Remote.*;
import java.rmi.RemoteException;
import java.rmi.server.*;

public class MasterMain {

	public static void main(String[] args) throws Exception {
		final Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		Master master = new Master(registry);
		registry.rebind(Master.RMI_NAME, master);

		System.out.println("Waiting for someone wake me up.");
		MasterMain masterMain = new MasterMain();
		synchronized (masterMain) {
			try {
				masterMain.wait();
			} catch (InterruptedException e) {
				// Me han despertado, tengo que terminar la ejecución.
			}
		}
	}
}

/**
 * La clase Master se encarga de registrar las estaciones que aun están por registrar
 */
class Master extends UnicastRemoteObject implements MasterServices {

	private static final long serialVersionUID = 1L;

	static final Number RMI_ID = 0;
	static final String RMI_NAME = Master.class.getSimpleName();

	private final Registry registry;

	protected Master(Registry registry) throws RemoteException {
		super();
		this.registry = registry;
	}

	public void registerStation(StationServices Station) throws RemoteException {
		String rmiName;
		try {
			rmiName = Station.getRmiName();
			registry.rebind(rmiName, Station);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("Registered: " + rmiName);
	}

	public void sayHelloWorld() throws RemoteException {
		System.out.println("I'm de Master, and I'm unique !!!");
	}

	public String[] getData() throws RemoteException {
		String[] arr = new String[4];

		arr[0] = arr[1] = arr[2] = arr[3] = "NULL";

		return arr;
	}
}


/**
 * DEPENDENCIAS DEL RMI
 */


interface ServerServices extends Remote {
	public void sayHelloWorld() throws RemoteException;
	public String[] getData() throws RemoteException;
}

interface MasterServices extends ServerServices {
	public void registerStation(StationServices Station) throws RemoteException;
}
interface StationServices extends ServerServices {
	public String getRmiName() throws RemoteException;
}

