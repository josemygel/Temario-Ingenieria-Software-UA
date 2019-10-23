package ppss.matriculacion.to;

import java.util.Date;
import java.util.List;

/**
 * La clase <code>AlumnoTO</code> se encarga de encapsular los datos de los alumnos.
 *
 */
public class AlumnoTO implements Comparable<AlumnoTO> {
	
	/**
	 * NIF del alumno.
	 */
	String nif;
	
	/**
	 * Nombre del alumno.
	 */
	String nombre;
	
	/** 
	 * Direccion postal del alumno.
	 */
	String direccion;
	
	/**
	 * Direccion de correo electronico del alumno.
	 */
	String email;
	
	/**
	 * Lista de telefonos del alumno.
	 */
	List<String> telefonos;
	
	/**
	 * Fecha de nacimiento del alumno.
	 */
	Date fechaNacimiento;
		
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<String> telefonos) {
		this.telefonos = telefonos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Comprueba si dos alumnos son iguales. Dos alumnos se consideraran iguales
	 * si tienen el mismo NIF.
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof AlumnoTO) {
			AlumnoTO p = (AlumnoTO)o;
			return nif == p.getNif();			
		} else {
			return false;
		}
	}

	/**
	 * Ordena alfabeticamente los alumnos por nombre ascendente
	 */
	public int compareTo(AlumnoTO o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}

	public String toString() {
		return nif;
	}
}
