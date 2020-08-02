package ppss.matriculacion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FuenteDatosJDBC {

	private static FuenteDatosJDBC me = null;
	
	private FuenteDatosJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra el driver de la BD");
		}
	}
	
	public static FuenteDatosJDBC getInstance() {
		if(me==null) {
			me = new FuenteDatosJDBC();
		}
		return me;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/matriculacion?useSSL=false", "root", "");
	}
}
