package ppss.matriculacion.to;

import java.util.ArrayList;
import java.util.List;

public class ReciboMatriculaTO {

	AlumnoTO alumno;
	List<LineaReciboMatriculaTO> asignaturasMatricula;
	float tasaMatricula;
	float total;
	
	public static class LineaReciboMatriculaTO {
		AsignaturaTO asignatura;
		float precioAsignatura;
				
		public LineaReciboMatriculaTO() {
		}

		public LineaReciboMatriculaTO(AsignaturaTO asignatura,
				float precioAsignatura) {
			this.asignatura = asignatura;
			this.precioAsignatura = precioAsignatura;
		}
		
		public AsignaturaTO getAsignatura() {
			return asignatura;
		}
		
		public void setAsignatura(AsignaturaTO asignatura) {
			this.asignatura = asignatura;
		}
		
		public float getPrecioAsignatura() {
			return precioAsignatura;
		}
		
		public void setPrecioAsignatura(float precioAsignatura) {
			this.precioAsignatura = precioAsignatura;
		}
	}

	public ReciboMatriculaTO() {
		asignaturasMatricula = new ArrayList<LineaReciboMatriculaTO>();
	}
	
	public AlumnoTO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoTO alumno) {
		this.alumno = alumno;
	}

	public void addAsignaturaMatricula(AsignaturaTO asignatura, float precio) {
		asignaturasMatricula.add(new LineaReciboMatriculaTO(asignatura, precio));
	}
	
	public List<LineaReciboMatriculaTO> getAsignaturasMatricula() {
		return asignaturasMatricula;
	}

	public float getTasaMatricula() {
		return tasaMatricula;
	}

	public void setTasaMatricula(float tasaMatricula) {
		this.tasaMatricula = tasaMatricula;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	
}
