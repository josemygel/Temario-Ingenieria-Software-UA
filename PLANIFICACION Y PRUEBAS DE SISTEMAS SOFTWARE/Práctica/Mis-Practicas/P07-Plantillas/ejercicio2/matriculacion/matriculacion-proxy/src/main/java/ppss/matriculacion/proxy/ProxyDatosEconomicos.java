package ppss.matriculacion.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import ppss.matriculacion.to.DatosEconomicosAlumnoTO;


public class ProxyDatosEconomicos {

	private static final String URL_SERVIDOR_ECONOMICO = "http://registro.alumnos.gov/datosAlumno?nif=";
	
	public DatosEconomicosAlumnoTO getDatosEconomicosAlumno(String nif) throws ProxyException {
		// Crea la URL para conectar al servidor
		URL urlServidorDatos = null;
		try {
			urlServidorDatos = new URL(URL_SERVIDOR_ECONOMICO + nif);
		} catch (MalformedURLException e) {
			throw new ProxyException("Error al generar la URL",e);
		}
		
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			// Abre flujo de lectura de la URL
			is = urlServidorDatos.openStream();
			ois = new ObjectInputStream(is);
			
			DatosEconomicosAlumnoTO datos = (DatosEconomicosAlumnoTO)ois.readObject();
			
			return datos;
		} catch (IOException e) {
			throw new ProxyException("Error al comunicar con el servidor de datos economicos", e);
		} catch (ClassNotFoundException e) {
			throw new ProxyException("Los datos obtenidos del servidor no son correctos", e);
		} finally {
			// Cierra los flujos de entrada
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) { }
			}
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) { }
			}
		}
		
	}
}
