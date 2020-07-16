
/**
 * ProvisionASkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.mtis.www.proveedora;

import org.mtis.www.utils.*;

/**
 * ProvisionASkeleton java skeleton for the axisService
 */
public class ProveedorASkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param presupuestar
	 * @return presupuestarResponse
	 */

	public org.mtis.www.proveedora.PresupuestarResponse presupuestar(org.mtis.www.proveedora.Presupuestar presupuestar) {
		final String ref = presupuestar.getReferencia();
		final int cantidad = presupuestar.getUnidades();

		ProveedorALogicDAO dao = new ProveedorALogicDAO();
		PresupuestarResponse resp = new PresupuestarResponse();
		resp.setResultado(false);
		resp.setPrecio(-1);

		System.out.println("\n==========================================\n");
		System.out.println("ProveedorA [INFO]: La empresa solicita presupuesto para el producto(" + ref
				+ ") por " + cantidad + " unidades");

		float presupuesto = dao.solicitarPresupuesto(ref, cantidad);
		
		if (presupuesto >= 0) {
			resp.setMensaje("Presupuesto obtenido con exito: " + presupuesto + "€");
			resp.setPrecio(presupuesto);
		} else {
			resp.setPrecio(presupuesto);
			resp.setMensaje(presupuesto == -404 ? "El producto no existe"
					: "Ha ocurrido un problema solicitando el presupuesto");
		}

		resp.setResultado(resp.getPrecio() < 0);
		System.out.println("ProveedorA [" + (resp.getResultado() ? "INFO" : "ERROR") + "]: " + resp.getMensaje());

		return resp;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param solicitar
	 * @return solicitarResponse
	 */

	public org.mtis.www.proveedora.SolicitarResponse solicitar(org.mtis.www.proveedora.Solicitar solicitar) {
		final String ref = solicitar.getReferencia();
		final int cantidad = solicitar.getUnidades();
		String message = "";
		SolicitarResponse resp = new SolicitarResponse();
		ProveedorALogicDAO dao = new ProveedorALogicDAO();
		System.out.println("\n==========================================\n");
		System.out.println("ProveedorA [INFO]: La empresa solicita stock de producto(" + ref + ") por "
				+ cantidad + " unidades");

		int solicitud = dao.solicitarStock(ref, cantidad);
		if (solicitud == 0) {
			resp.setResultado(true);
			resp.setMensaje("Stock " + (cantidad < 0 ? "DEVUELTO" : "ENVIADO") + " correctamente");
		} else {
			resp.setResultado(false);
			resp.setMensaje(solicitud == 404 ? "El producto solicitado no existe"
					: "ERROR[" + solicitud + "]: Ha ocurrido un problema con la solicitud de Stock");
		}
		
		System.out.println("ProveedorA [" + (resp.getResultado() ? "INFO" : "ERROR") + "]: " + resp.getMensaje());
		return resp;
	}

}
