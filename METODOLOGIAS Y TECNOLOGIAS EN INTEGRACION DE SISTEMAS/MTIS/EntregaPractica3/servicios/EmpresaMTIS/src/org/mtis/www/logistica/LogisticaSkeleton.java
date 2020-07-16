
/**
 * LogisticaSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.mtis.www.logistica;

import org.mtis.www.utils.LogisticaLogicDAO;

/**
 * LogisticaSkeleton java skeleton for the axisService
 */
public class LogisticaSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param actualizarStock
	 * @return actualizarStockResponse
	 */

	public org.mtis.www.logistica.ActualizarStockResponse actualizarStock(org.mtis.www.logistica.ActualizarStock actualizarStock) {
		final String referencia = actualizarStock.getRef();
		final int cantidad = actualizarStock.getCount();
		LogisticaLogicDAO dao = new LogisticaLogicDAO();
		ActualizarStockResponse resp = new ActualizarStockResponse();
		resp.setActualizado(false);

		System.out.println("\n==========================================\n");
		System.out.println("LOGISTICA [INFO]: Actualizando Stock de producto["+referencia+"] "+(cantidad<0 ? "restando ":"añadiendo ")+cantidad+" unidades");
		
		resp.setActualizado(dao.cambiarStock(referencia, cantidad));
		
		return resp;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param actualizarPrecio
	 * @return actualizarPrecioResponse
	 */

	public org.mtis.www.logistica.ActualizarPrecioResponse actualizarPrecio(org.mtis.www.logistica.ActualizarPrecio actualizarPrecio) {
		final String referencia = actualizarPrecio.getRef();
		final float precio = 1.3f*1.21f*actualizarPrecio.getPrecio();
		LogisticaLogicDAO dao = new LogisticaLogicDAO();
		ActualizarPrecioResponse resp = new ActualizarPrecioResponse();
		resp.setActualizado(false);

		System.out.println("\n==========================================\n");
		System.out.println("LOGISTICA [INFO]: Actualizando Precio de producto["+referencia+"] a "+actualizarPrecio.getPrecio()+"€ => "+precio+"€ (30% interes y 21% IVA)");

		resp.setActualizado(dao.actualizarPrecio(referencia, precio));
		
		return resp;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param comprobarStock
	 * @return comprobarStockResponse
	 */

	public org.mtis.www.logistica.ComprobarStockResponse comprobarStock(org.mtis.www.logistica.ComprobarStock comprobarStock) {
		final String referencia = comprobarStock.getRef();
		final int cantidad = comprobarStock.getCount();
		LogisticaLogicDAO dao = new LogisticaLogicDAO();
		ComprobarStockResponse resp = new ComprobarStockResponse();
		resp.setExiste(false);

		System.out.println("\n==========================================\n");
		System.out.println("LOGISTICA [INFO]: Comprobando existencias de Stock de producto["+referencia+"] para "+cantidad+" unidades");
		if(cantidad > 0){
			resp.setExiste(dao.comprobarStock(referencia, cantidad));
		}else{
			System.out.println("LOGISTICA [ERROR]: La cantidad debe ser mayor que 0");
		}
		
		return resp;
	}

}
