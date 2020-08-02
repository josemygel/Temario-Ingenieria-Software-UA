package ppss.matriculacion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FuenteDatosJDBC_backup {

	private static FuenteDatosJDBC_backup me = null;

	private FuenteDatosJDBC_backup() {
		try {
			Class.forName("org.hsqldb.jdbcDriver" );
			
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra el driver de la BD");
		}
	}
	
	public static FuenteDatosJDBC_backup getInstance() {
		if(me==null) {
			me = new FuenteDatosJDBC_backup();
		}
		return me;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:file:matriculacionbd;ifexists=true", "SA", "");
		
	}
}
