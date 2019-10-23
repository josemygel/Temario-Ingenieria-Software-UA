import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.standard.Finishings;

import java.lang.System;

/**
 *
 * @author Jose Miguel Gómez Lozano
 * @email josemygel@gmail.com
 */

// Clase servidor encargada de crear hilos de trabajo por peticiones de los
// usuarios
public class ServerWeb {

    static private Integer PUERTO = 8000;
    static private Integer TIMEOUT_CLIENTES = 30 * 1000; // segundos -> milisegundos

    static public String ipController = "127.0.0.1"; // NO DEBE SER ESTATICA (ARREGLAR)
    static public Integer portController = 9000;
    static public ArrayList<Socket> clientes = new ArrayList<>();
    static public Integer maxConnections = 0;

    public static void main(String[] args) {
        try {
            if(args.length == 0) //SI INTRODUCIMOS HELP COMO PARAMETRO NOS DICE COMO USAR EL HTTP SERVER
            {
                System.out.println("Utiliza la estructura indicada:");
                System.out.println("ServerWeb [IpController] [PortController (Default=9000)] [PortHTTP (Default=8000)] [MaxConnections (Default=Infinity)]");
                System.out.println("Se puede hacer uso de este ejemplo para especificar solo determinados parámetros:");
                System.out.println("ServerWeb 127.0.0.1 Default Defecto 50\n");
                return;
            }
            else if(args.length > 0 && args[0] != "help") //INTRODUCIMOS EL CONTROLLER IP
            {
                System.out.println(args[0]);
                ipController = args[0];
                System.out.println("CONTROLLER IP: " + ipController);
            }else{
                System.out.println("(DEFAULT) CONTROLLER IP: " + ipController);

            }

            if(args.length > 1) //INTRODUCIMOS EL CONTROLLER PORT
            {

                try {
                    portController = Integer.parseInt(args[1]);
                    System.out.println("CONTROLLER PORT: " + portController);
                } catch (Exception e) {
                    portController = 9000;
                    System.out.println("(DEFAULT) CONTROLLER PORT: " + portController);
                }
            }else{
                System.out.println("(DEFAULT) CONTROLLER PORT: " + portController);
            }
            
            if(args.length > 2) //INTRODUCIMOS EL HTTP PORT
            {
                try {
                    PUERTO = Integer.parseInt(args[2]);
                    System.out.println("HTTP PORT: " + PUERTO);
                } catch (Exception e) {
                    PUERTO = 8000;
                    System.out.println("(DEFAULT) HTTP PORT: " + PUERTO);
                }
            }else{
                System.out.println("(DEFAULT) HTTP PORT: " + PUERTO);
            }

            if(args.length > 3) //INTRODUCIMOS EL HTTP PORT
            {
                try {
                    maxConnections = Integer.parseInt(args[3]) > 0 ? Integer.parseInt(args[3]) : 0;
                    if(maxConnections > 0)
                        System.out.println("Max Connections: " + maxConnections + "\n");
                    else System.out.println("(DEFAULT) Max Connections: Unlimited\n");
                } catch (Exception e) {
                    maxConnections = 0;
                    System.out.println("(DEFAULT) Max Connections: Unlimited\n");
                }
            }else{
                System.out.println("(DEFAULT) Max Connections: Unlimited\n");
            }

            ServerSocket socketServidor = new ServerSocket(PUERTO);

            while (true) {
                Socket socketCliente = socketServidor.accept();

                int cantidad = 0;
                
                System.out.println("NEW CONEXION!");

                for(int i = 0; i < clientes.size(); i++)
                {
                    try{
                        if(!clientes.get(i).isClosed())
                        {
                            cantidad++;
                        }
                    }catch(Exception ex){
                    }
                }
                System.out.println("Conexiones actuales: " + cantidad);

                if(maxConnections == 0 || cantidad <= maxConnections)
                {
                    System.out.println("CONEXION INIT");
                    clientes.add(socketCliente);
                    HiloTrabajo hilo = new HiloTrabajo(socketCliente, TIMEOUT_CLIENTES, ipController.toString(), portController.toString());
                    hilo.start();
                }else{
                    System.out.println("CONEXION REFUSED!");
                    HiloTrabajo hilo = new HiloTrabajo(socketCliente, "ERROR 400: El servidor esta saturado, por favor, intentalo de nuevo mas tarde.");
                    //hilo.start();
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(ServerWeb.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println();
        }
    }

}

// Hilo encargado de devolver HTTP a la peticion de un usuario
class HiloTrabajo extends Thread {

    private String ipController;
    private String portController;
    private Socket socket;
    private int timeout;
    BufferedReader input;
    DataOutputStream output;

    //Este es usado para indicar al usuario que no se puede acceder de momento por saturacion de conexiones
    public HiloTrabajo(Socket s, String error) {
        socket = s;
        try {
            FileInputStream archivo = new FileInputStream("error.html");
            socket.setSoTimeout(timeout);
            output = new DataOutputStream(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if(!socket.isOutputShutdown())
            {
                int tipoPeticion = 2;
                try {
                    // Recibimos las lineas de la peticion
                    input.readLine(); // Ej: GET /index.html HTTP/1.1
                    input.readLine(); // En blanco
                } catch (Exception ex) {
                    System.err.println("Error: " + ex.getMessage());
                    System.err.println("No se pudo leer la peticion.");
                    System.err.println();
                    return; // No se debe seguir adelante
                }
                String content;
                content = getFileContent(archivo, "UTF8");
                content = remplacer(content,"$error",error);
            
                sendContent("", tipoPeticion, content, output);
            }

            archivo.close();
            output.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public HiloTrabajo(Socket s, int t, String ipController, String portController) {
        socket = s;
        timeout = t;
        this.ipController = ipController;
        this.portController = portController;
    }




    // Metodo que coger un String origen, un string a buscar (remp) y otro por el
    // que va a sustituirlo (newValue)
    public String remplacer(String origen, String remp, String newValue) {
        String string = origen.indexOf(remp) == -1 ? "ERROR REMPLACE NOT FOUND!"
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

/*    public Integer getStationID(String ruta) {
        /**
         * stationID = 0 (NO BSUCADO) stationID = -2 (TODAS LAS STATIONS) stationID >= 1
         * (BUSCAR ESA STATION)
         *//*
        int stationID = 0;
        // Obtenemos la posicion de la segunda /
        int stationRutePos = ruta.indexOf("/controladorSD");

        if (stationRutePos != -1) {
            String aux = ruta.substring(ruta.indexOf("/controladorSD") + "/controladorSD".length());
            try {
                if (aux.length() == 0) {
                    stationID = -2;
                } else {
                    stationID = Integer.parseInt(aux);

                    if (stationID <= 0)
                        stationID = -1;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                stationID = -1;
            }
        }
        /**
         * stationID > 0 (TODO OK) stationID == 0 (No especificada una estacion)
         * stationID == -1 (STATION NOT FOUND)
         *//*
        return stationID;
    }
*/
    public String getRute(String peticion) {

        // Como la peticion no se ha rechazado, continuamos:
        // Linea 2: Etraemos la ruta del archivo solicitado
        int ini = 0, fin = 0;

        for (int pos = 0; pos < peticion.length(); pos++) {
            // Buscamos el ultimo espacio en blanco en la linea
            if (peticion.charAt(pos) == ' ' && ini != 0) {
                fin = pos;
                break;
            }
            // Buscamos el primer espacio en blanco en la linea
            if (peticion.charAt(pos) == ' ' && ini == 0) {
                ini = pos;
            }
        }

        String ruta = peticion.substring(ini+2, fin);

        if(ruta.endsWith(".ico"))
            return ruta;

            System.out.println("RUUTAA: " + ruta);
        if (ruta.isEmpty() || ruta == "index.html" || ruta=="index.html" || (ruta.indexOf("index.html")==0 && ruta.length()=="index.html".length())) {
            ruta = "index.html";
        } else if (ruta.indexOf("controladorSD") != -1) {
            //TIENE QUE TREABAJAR EL CONTROLADOR!!!
        } else {
            ruta = "ERROR 404: PAGE NOT FOUND!";
        }
        return ruta;
    }
	/*
	* Lee datos del socket. Supone que se le pasa un buffer con hueco
	*	suficiente para los datos. Devuelve el numero de bytes leidos o
	* 0 si se cierra fichero o -1 si hay error.
	*/
	public String leeSocket (Socket p_sk)
	{
        String datos = "";
		try
		{
			InputStream aux = p_sk.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			datos = flujo.readUTF();
			System.out.println("Socket getting " + datos);
		}
		catch (Exception e)
		{
            datos = e.toString();
			System.out.println("Error: " + e.toString());
        }

      return datos;
	}

	/*
	* Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
	* o -1 si hay error.
	*/
	public void escribeSocket (Socket p_sk, String p_Datos)
	{
		try
		{
			System.out.println("Socket sending " + p_Datos);
			OutputStream output = p_sk.getOutputStream();
			DataOutputStream flujo= new DataOutputStream( output );
			flujo.writeUTF(p_Datos);      
			//Write/WriteBytes (Flash de buffer en sv y \n \l)
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return;
    }

    public String getController(String ruta)
    {
        System.out.println("GET CONTROLLER INIT!\n");
        String result = "";
        int resultado;

        /*
		* Se abre la conexion con el servidor, pasando el nombre del ordenador
		* y el servicio solicitado.
        */
		try
		{
            //CONECTAR AL SOCKET DEL CONTROLLER
            Socket controller = new Socket(ipController, Integer.parseInt(portController));

            escribeSocket(controller, ruta);

            result = leeSocket(controller);

            try {
                resultado = Integer.parseInt(result);
            } catch (Exception e) {
                resultado = -1;
            }

            controller.close();
        }
		catch(Exception e)
		{
            result = e.toString();
			System.out.println("Error: " + e.toString());
        }
        System.out.println("getController.result = " + result);

		return result;
    }

    public String getContent(String ruta) {
        // Si la ruta es sobre una pagina no encontrada se devolverá dicho error para
        // mostrarlo en el navegador.

        String content = "";
        FileInputStream archivo;
        if (ruta.indexOf("ERROR") == 0) {
            return ruta;
        }

        // Preparamos la escritura del fichero en el output
        try {
            System.out.println("getContent.RUTA -> " + ruta);
            archivo = new FileInputStream(ruta);
            content = getFileContent(archivo, "UTF8");
            archivo.close();
        } catch (Exception ex) {
            // Si el fichero no existe, enviamos un error 404
            Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println("Se ha detectado un error 404");
            System.err.println();
            try {
                output.writeBytes(crearCabecera(404, -1));
            } catch (Exception ex2) {
                // Quizás no ha sido posible enviar el error
                Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex2);
                System.err.println("Error: " + ex2.getMessage());
                System.err.println("No se pudo enviar el mensaje de error 404");
                System.err.println();
            }
            return "EXCEPTION"; // No se debe seguir adelante
        }

        return content;
    }

    public Void sendContent(String ruta, int tipoPeticion, String content, DataOutputStream output) {
        int code = 200;
        int tipoArchivo = 0;
        /**
         * Tipo MIME de archivo solicitado
         *
         * -1 = Error 0 = Desconocido 1 = .htm/.html 2 = .txt 3 = .gif 4 = .jpg/.jpeg 5
         * = .zip
         *
         */

        ruta = ruta.toLowerCase();

        // Este casi se da cuando hay un error
        if (content.indexOf("ERROR 404:") == 0) {
            code = 404;
            ruta = "error.html";
        }
        if (content.indexOf("ERROR 400:") == 0) {
            code = 400;
            ruta = "error.html";
        }
        if (content.indexOf("ERROR 405:") == 0)
        {
            code = 405;
            ruta = "error.html";
        }

        tipoArchivo = ruta.endsWith(".ico") ? 2 : 1;

        // Enviamos la cabecera
        try {
            output.writeBytes(crearCabecera(code, tipoArchivo));
        } catch (Exception ex) {
            // Quizás no ha sido posible enviar la cabecera
            Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println("No se pudo enviar la cabecera de la respuesta correctamente");
            System.err.println();
        }

        // Y si la peticion es GET, el contenido tambien
        if (tipoPeticion == 2) {
            try {
                if (code != 200) {
                    ruta = "error.html";
                    //Si se trata de un error obtenemos el error
                    content = remplacer(getContent(ruta), "$error", content);
                }
                output.writeBytes(content);
                // output.writeUTF(content);
            } catch (Exception ex) {
                // Quizás no ha sido posible enviar el contenido
                Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error: " + ex.getMessage());
                System.err.println("No se pudo enviar el contenido de la respuesta correctamente");
                System.err.println();
            }
        }

        return null;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(timeout);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());

            if(!socket.isOutputShutdown() && !socket.isInputShutdown())
                responder(input, output);

            input.close();
            output.close();
            socket.close();
        } catch (SocketTimeoutException ex) {
            Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println("La conexión ha caducado (30 segundos sin recibir nada)");
            System.err.println();
        } catch (Exception ex) {
            Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println();
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (Exception ex) {
                Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error: " + ex.getMessage());
                System.err.println("No se puede cerrar la conexion.");
                System.err.println();
            }
        }
    }

    private void responder(BufferedReader input, DataOutputStream output) {

        String peticion = "";
        int tipoPeticion = 0;
        /**
         * SOLO SE ACEPTARAN PETICIONES GET! Tipo de peticion
         *
         * 0=No Aceptada 1=HEAD (testing) 2=GET
         *
         */

        try {
            // Recibimos las lineas de la peticion
            peticion = input.readLine(); // Ej: GET /index.html HTTP/1.1
            input.readLine(); // En blanco
        } catch (Exception ex) {
            Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error: " + ex.getMessage());
            System.err.println("No se pudo leer la peticion.");
            System.err.println();
            return; // No se debe seguir adelante
        }

        // Linea 1:
        // Comenzamos a distinguir el tipo de peticion. Si hubiese más tipos, se deben
        // añadir a continuacion.
        if(peticion != null)
        {
            String tmp = peticion.toUpperCase();
            //De momento no contemplaremos el HEAD
           /* if (tmp.startsWith("HEAD")) {
                tipoPeticion = 1;
            }*/
            if (tmp.startsWith("GET")) {
                tipoPeticion = 2;
            }
        }
        // Si hubiese más tipos de peticiones, se añadirían aquí

        // En el caso de no reconocer el tipo de peticion, ya podemos enviar el error
        // 501
        if (tipoPeticion == 0) {
            try {
                //Primero insertamos la cabecera
//                output.writeBytes(crearCabecera(405, -1));
                System.err.println("ERROR 405: NOT USE GET METHOD...\n");
                String content = "ERROR 405: METHOD NOT GET!";
                String ruta = "error.html";
                // Recogemos el contenido a enviar y lo enviamos al output
                sendContent(ruta, tipoPeticion, content, output);
                return;
            } catch (Exception ex) {
                Logger.getLogger(HiloTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error: " + ex.getMessage());
                System.err.println("No se pudo enviar el mensaje de error 501");
                System.err.println();
                return;
            }
        } else {
            System.out.println("Peticion: " + peticion);
            // Obtenemos la ruta
            String ruta = getRute(peticion);

            // Mostramos en consola la ruta
            System.out.println("La ruta es: " + ruta);

            String content;

             if(ruta.indexOf("controladorSD")==0 || ruta.indexOf("controladorSD/") == 0)
            {
                ruta = ruta.length() > "controladorSD/".length() ? ruta.substring("controladorSD".length()+1) : "index.html";
                System.out.println("ruta 2 = " + ruta);
                content = getController(ruta);
            }else{
                content = getContent(ruta);
            }

            // Recogemos el contenido a enviar y lo enviamos al output
            sendContent(ruta, tipoPeticion, content, output);
        }
    }

    private String crearCabecera(int codigoRepuesta, int tipoArchivo) {
        String s = "HTTP1.0 ";

        /**
         * Verificamos el codigo
         *
         * 200:OK 404:No Encontrado 501:No implementado
         *
         */
        switch (codigoRepuesta) {
        case 200:
            s = s + "200 OK";
            break;
        case 404:
            s = s + "404 Not Found";
            break;
        case 400:
            s = s + "400 Server Error";
            break;
        case 405:
            s = s + "405 Method Not Allowed";
            break;
        case 501:
            s = s + "501 Not Implemented";
            break;
        }
        s = s + "\r\n";
        s = s + "Connection: close\r\n";
        s = s + "Server: Servidor creado por Jose Miguel Gomez Lozano\r\n";

        switch (tipoArchivo) {
        // El caso -1 no devuelve nada porque lo reservamos para errores
        // MIME conocidos
        case 1:
            s = s + "Content-Type: text/html\r\n";
            break;
        case 2:
            s = s + "Content-Type: image/ico\r\n";
            break;
        // En casos de formatos desconocidos... (es decir, el caso 0)
        case 0:
        default:
            s = s + "Content-Type: application/octet-stream\r\n";
            break;
        }
        s = s + "\r\n";
        return s;
    }

}