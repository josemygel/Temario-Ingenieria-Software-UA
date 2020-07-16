package org.mtis.www.utils;

import java.util.ArrayList;
import java.util.List;
 
public interface ProveedorADAO {
	public int solicitarStock(String referencia, int cantidad);
	public float solicitarPresupuesto(String referencia, int cantidad);
}