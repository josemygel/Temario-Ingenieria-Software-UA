package org.mtis.www.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogisticaLogicDAO implements LogisticaDAO {
	
	@Override
	public boolean cambiarStock(String referencia, int cantidad) {
		// El valor correcto será 0, y será 404 en caso de no encontrar producto (se asume que la empresa es correcta ya que se comprueba antes
		boolean cambiado = false;

		Connection con = null;
		// the mysql insert statement
		String query = "update productos set cantidad = cantidad + ? where referencia = ?";

		try {
			con = Conexion.conectar();
			// execute the preparedstatement
			
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, cantidad);
			preparedStmt.setString(2, referencia);
			
			if(preparedStmt.executeUpdate() == 1){
				System.out.println("\tDAO [INFO]: PRODUCTO["+referencia+"]: ");
				System.out.println("\tDAO [INFO]: Nueva cantidad "+(cantidad>=0 ? "añadida":"restada")+" del stock: " + cantidad);
				cambiado = true;
			}else{
				System.out.println("\tDAO [ERROR 404]: El producto "+referencia+" no existe");
			}
			
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: Error: LogisticaDAO.cambiarStock()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			e.printStackTrace();
		}
		return cambiado;
	}

	@Override
	public boolean comprobarStock(String referencia, int cantidad) {
		boolean existe = false;

		Connection con = null;
		// the mysql delete statement
		String query = "select * from productos where referencia = '"+referencia+"'";

		try {
			con = Conexion.conectar();
			// execute the preparedstatement

			// create the mysql insert preparedstatement
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);

			rs.first();
			
			if (rs.getString(1).compareToIgnoreCase(referencia) == 0) {
				System.out.println("\tDAO [INFO]: PRODUCTO["+rs.getString(1)+"]: "+rs.getString(2));
				System.out.println("\tDAO [INFO]: ALMACEN: "+rs.getInt(3));
				System.out.println("\tDAO [INFO]: SOLICITANDO: "+cantidad);
				System.out.println("\tDAO [INFO]: STOCK "+(cantidad<=rs.getInt(3) ? "NECESARIO":"INNECESARIO"));
				// existe = ¿Hay suficientes existencias?
				existe = cantidad <= rs.getInt(3);
			}

			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: LogisticaLogicDAO.comprobarStock()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			e.printStackTrace();
		}
		
		return existe;
	}
	

	@Override
	public boolean actualizarPrecio(String referencia, float precio) {
		boolean actualizado = false;

		Connection con = null;
		// the mysql insert statement
		
		String query = "update productos set precio = ? where referencia = ?";

		try {
			con = Conexion.conectar();
			// execute the preparedstatement


			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setFloat(1, precio);
			preparedStmt.setString(2, referencia);
			
			if(preparedStmt.executeUpdate() == 1){
				System.out.println("\tDAO [INFO]: PRODUCTO["+referencia+"]: ");
				System.out.println("\tDAO [INFO]: Nuevo precio insertado: " + precio);
				actualizado = true;
			}else{
				System.out.println("\tDAO [ERROR 404]: El producto "+referencia+" no existe");
			}
			
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: LogisticaDAO.actualizarPrecio()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			e.printStackTrace();
		}
		
		return actualizado;
	}
	
}