package org.mtis.www.utils;

import java.util.ArrayList;
import java.util.List;
 
public interface LogisticaDAO {	
	public boolean comprobarStock(String referencia, int cantidad);
	public boolean cambiarStock(String referencia, int cantidad);
	public boolean actualizarPrecio(String referencia, float precio);
}