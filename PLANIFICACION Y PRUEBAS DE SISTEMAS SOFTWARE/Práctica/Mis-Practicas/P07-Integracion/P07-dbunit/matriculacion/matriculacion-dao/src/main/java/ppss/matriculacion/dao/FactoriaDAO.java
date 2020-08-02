package ppss.matriculacion.dao;

/**
 * La clase <code>GestorDAO</code> se utilizara como factoria de los objetos de acceso
 * a datos de la aplicacion. 
 *
 */
public class FactoriaDAO {
	/**
	 * Devuelve al DAO para acceder a los datos de los alumnos.
	 * @return DAO que da acceso a los alumnos.
	 */
	public IAlumnoDAO getAlumnoDAO() {
		return new JDBCAlumnoDAO();
	}	

	/**
	 * Devuelve al DAO para acceder a los datos de las asignaturas.
	 * @return DAO que da acceso a las asignaturas.
	 */
	public IAsignaturaDAO getAsignaturaDAO() {
		return new JDBCAsignaturaDAO();
	}	

	/**
	 * Devuelve al DAO para acceder a los datos de matriculacion.
	 * @return DAO que da acceso a las matriculaciones.
	 */
	public IMatriculaDAO getMatriculaDAO() {
		return new JDBCMatriculaDAO();
	}	
}
