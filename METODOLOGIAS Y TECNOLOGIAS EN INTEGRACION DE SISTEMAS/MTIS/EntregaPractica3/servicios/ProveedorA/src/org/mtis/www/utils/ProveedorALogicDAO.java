package org.mtis.www.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProveedorALogicDAO implements ProveedorADAO {
/*
	@Override
	public boolean esCliente(String cif) {
		boolean existe = false;

		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM empresas WHERE CIF = '"+cif+"'";

		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString(1).compareToIgnoreCase(cif) == 0) {
					System.out.println("\tDAO [INFO]: EMPRESA["+cif+"]: "+rs.getString(2));
					existe = true;
					break;
				}
			}
			
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: ProveedorALogicDAO.esCliente()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			//e.printStackTrace();
		}
		if(!existe)
			System.out.println("\tDAO [INFO]: El cif("+cif+") no pertenece a ninguna empresa");
		
		return existe;
	}
*/
	@Override
	public int solicitarStock(String referencia, int cantidad) {

		// El valor correcto será 0, y será 404 en caso de no encontrar producto (se asume que la empresa es correcta ya que se comprueba antes
		int resultado = 0;

		Connection con = null;
		// the mysql insert statement
		String query = "INSERT INTO compras (producto, cantidad) values (?,?)";

		try {
			con = Conexion.conectar();
			// execute the preparedstatement

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, referencia);
			preparedStmt.setInt(2, cantidad);
			
			preparedStmt.execute();
			
			System.out.println("\tDAO [INFO]: COMPRA REALIZADA:");
			System.out.println("\tDAO [INFO]: Producto: " + referencia);
			System.out.println("\tDAO [INFO]: Cantidad: " + cantidad);


			con.close();
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: ProveedorALogicDAO.solicitarStock()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			resultado = e.getErrorCode() == 1452 ? -404 : -400;
			if(resultado == -404){
				System.out.println("\tDAO [ERROR 404]: El producto no existe");
			}
			//e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public float solicitarPresupuesto(String referencia, int cantidad) {

		float presupuesto = -1;

		Connection con = null;
		// the mysql delete statement
		String query = "SELECT * FROM productos WHERE referencia = ?";

		try {
			con = Conexion.conectar();
			// execute the preparedstatement

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, referencia);
			ResultSet rs = preparedStmt.executeQuery();
			

			while (rs.next()) {
				if (rs.getString(1).compareToIgnoreCase(referencia) == 0) {
					presupuesto = cantidad * rs.getFloat(3);
					System.out.println("\tDAO [INFO]: Solicitando presupuesto de producto("+referencia+") por "+cantidad+" unidades");
					break;
				}
			}
			preparedStmt.close();
			rs.close();
			con.close();

			if(presupuesto == -1){
				presupuesto = -404;
			}
		} catch (SQLException e) {
			System.out.println("\tDAO [ERROR]: ProveedorALogicDAO.solicitarPresupuesto()");
			System.out.println("\tDAO [ERROR]: Exception Code: " + e.getErrorCode());
			System.out.println("\tDAO [ERROR]: Exception Message: " + e.getMessage());
			//e.printStackTrace();
		}
		return presupuesto;
	}
	
}