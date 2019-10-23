package ppss.matriculacion.dao;

import java.util.List;

import ppss.matriculacion.to.AlumnoTO;


/**
 * Interfaz comun de los objetos que dan acceso a los datos de los alumnos.
 *
 */
public interface IAlumnoDAO {

	/**
	 * De de alta una alumno.
	 * @param a Alumno que se agragara. Se producira un error si el alumno
	 * ya existe, o si el parametro o su campo <code>nif</code> es <code>null</code>.
	 * @throws DAOException Si ocurre un error al agregar al alumno
	 */
	public abstract void addAlumno(AlumnoTO a) throws DAOException;

	/**
	 * De de baja un alumno.
	 * @param nif Nif del alumno a eliminar. Se producira un error si el alumno no
	 * existe, o si el parametro es <code>null</code>.
	 * @throws DAOException Si ocurre un error al eliminar al alumno
	 */
	public abstract void delAlumno(String nif) throws DAOException;

	/**
	 * Obtiene los datos de un alumno.
	 * @param nif Nif del alumno del cual queremos obtener los datos
	 * @return Datos del alumno, o <code>null</code> si el alumno no existe.
	 * @throws DAOException Si ocurre un error al recuperar los datos
	 */
	public abstract AlumnoTO getAlumno(String nif) throws DAOException;

	/**
	 * Devuelve la lista de todos alumnos.
	 * @return Lista de todos los alumnos.
	 * @throws DAOException Si ocurre un error al recuperar los datos
	 */
	public abstract List<AlumnoTO> getAlumnos() throws DAOException;

}