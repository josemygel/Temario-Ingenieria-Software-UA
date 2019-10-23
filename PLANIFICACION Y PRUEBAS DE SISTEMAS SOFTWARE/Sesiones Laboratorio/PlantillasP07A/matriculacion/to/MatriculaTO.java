package ppss.matriculacion.to;

import java.util.List;

public class MatriculaTO {

	AlumnoTO alumno;
	List<AsignaturaTO> asignaturas;
	List<String> errores;
	
	public AlumnoTO getAlumno() {
		return alumno;
	}
	public void setAlumno(AlumnoTO alumno) {
		this.alumno = alumno;
	}
	
	public List<AsignaturaTO> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<AsignaturaTO> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public List<String> getErrores() {
		return errores;
	}
	public void setErrores(List<String> errores) {
		this.errores = errores;
	}	
	
}
