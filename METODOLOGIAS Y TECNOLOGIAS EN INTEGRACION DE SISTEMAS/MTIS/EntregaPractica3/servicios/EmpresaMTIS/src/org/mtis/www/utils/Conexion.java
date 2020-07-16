package org.mtis.www.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexion {
	public static Connection conectar() {
		Connection con = null;
		try {
	      // create a mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/empresamtis?useSSL=false";
	      Class.forName(myDriver);
	      con = DriverManager.getConnection(myUrl, "root", "");
	    
			if (con != null) {
//				System.out.println("Conectado");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
		return con;
	}
}