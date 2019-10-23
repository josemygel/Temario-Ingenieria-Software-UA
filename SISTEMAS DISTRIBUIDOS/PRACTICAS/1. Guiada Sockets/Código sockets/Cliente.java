import java.io.*;
import java.net.*;

public class Cliente {

	/*
	 * Lee datos del socket. Supone que se le pasa un buffer con hueco suficiente
	 * para los datos. Devuelve el numero de bytes leidos o 0 si se cierra fichero o
	 * -1 si hay error.
	 */
	public String leeSocket(Socket p_sk, String p_Datos) {
		try {
			InputStream aux = p_sk.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			p_Datos = flujo.readUTF();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return p_Datos;
	}

	/*
	 * Escribe dato en el socket cliente. Devuelve numero de bytes escritos, o -1 si
	 * hay error.
	 */
	public void escribeSocket(Socket p_sk, String p_Datos) {
		try {
			OutputStream aux = p_sk.getOutputStream();
			DataOutputStream flujo = new DataOutputStream(aux);
			flujo.writeUTF(p_Datos);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return;
	}

	public String pedirRespuesta(String message, Socket p_Socket_Con_Servidor) {
		String cadena = "";
		try {
			escribeSocket(p_Socket_Con_Servidor, message);
			cadena = "";
			cadena = leeSocket(p_Socket_Con_Servidor, cadena);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return cadena;
	}

	public void pedirOperacion(String p_host, String p_puerto) {
		int operacion;
		int salir = 0;
		int resultado = 0;
		char resp = 'x';
		/*
		 * Descriptor del socket y buffer para datos
		 */
		String message = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		/*
		 * Se abre la conexion con el servidor, pasando el nombre del ordenador y el
		 * servicio solicitado.
		 */
		try {
			Socket skCliente = new Socket(p_host, Integer.parseInt(p_puerto));

			System.out.println("MSG: ");
			message = br.readLine();
			message = pedirRespuesta(message, skCliente);

			System.out.println("La respuesta es: " + message);

			resp = 'x';
			while (resp != 's' && resp != 'n') {
				System.out.println("La respuesta es: " + resultado);
				System.out.println("¿Desea enviar otro MSG? [s,n]: ");
				resp = br.readLine().charAt(0);
			}
			if (resp != 's') {
				salir = 1;
				/*
				 * Se cierra el socket con el servidor
				 */

				escribeSocket(skCliente, "fin");

				message = leeSocket(skCliente, message);
				resultado = Integer.parseInt(message);

				if (resultado == -1) {
					skCliente.close();
					System.out.println("Conexion cerrada.");
					System.exit(0);
				}
			}
			message = "";
		} catch (

		Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return;
	}

	public void menu(String p_host, String p_puerto) {
		int opc = 0;
		try {

			while (opc != 1 && opc != 2) {
				System.out.println("[1] Enviar mensaje al servidor\n");
				System.out.println("[2] Salir");
				System.out.println("Indique la opcion a realizar:");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				opc = Integer.parseInt(br.readLine());
			}
			if (opc == 1) {
				pedirOperacion(p_host, p_puerto);
			} else {
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cl = new Cliente();
		int i = 0;
		String host;
		String puerto;
		if (args.length < 2) {
			System.out.println("Debe indicar la direccion del servidor y el puerto");
			System.out.println("$./Cliente nombre_servidor puerto_servidor");
			System.exit(-1);
		}
		host = args[0];
		puerto = args[1];

		while (i == 0) {
			cl.menu(host, puerto);
		}
	}

}
