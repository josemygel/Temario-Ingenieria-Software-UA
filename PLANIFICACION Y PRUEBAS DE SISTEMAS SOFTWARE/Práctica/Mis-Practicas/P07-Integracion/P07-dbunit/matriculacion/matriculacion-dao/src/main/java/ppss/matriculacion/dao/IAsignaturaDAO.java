package ppss.matriculacion.dao;

import java.util.List;

import ppss.matriculacion.to.AsignaturaTO;

public interface IAsignaturaDAO {

	public abstract AsignaturaTO getAsignatura(int cod) throws DAOException;
	public abstract List<AsignaturaTO> getAsignaturas() throws DAOException;
	public abstract void delAsignatura(String cod) throws DAOException;
	public abstract void addAsignatura(AsignaturaTO asignatura) throws DAOException;
	public abstract AsignaturaTO buscaAsignatura(String nombre) throws DAOException;

}
