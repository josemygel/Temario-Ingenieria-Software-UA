package ppss.matriculacion.dao;

import java.util.List;

import ppss.matriculacion.to.AlumnoTO;
import ppss.matriculacion.to.AsignaturaTO;

public class JDBCMatriculaDAO implements IMatriculaDAO {

	public void altaMatricula(AlumnoTO alumno, AsignaturaTO asignatura)
			throws DAOException {
		// TODO Auto-generated method stub

	}

	public void delMatricula(AlumnoTO alumno, AsignaturaTO asignatura)
			throws DAOException {
		// TODO Auto-generated method stub

	}

	public boolean existeMatricula(AlumnoTO alumno, AsignaturaTO asignatura)
			throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<AlumnoTO> getAlumnos(AsignaturaTO alumno) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AsignaturaTO> getAsignaturas(AlumnoTO alumno)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
