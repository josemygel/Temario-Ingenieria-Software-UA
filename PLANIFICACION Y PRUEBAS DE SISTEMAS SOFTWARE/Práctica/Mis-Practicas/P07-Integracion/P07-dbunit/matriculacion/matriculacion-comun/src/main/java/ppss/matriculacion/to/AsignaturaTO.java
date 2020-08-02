package ppss.matriculacion.to;

public class AsignaturaTO {
	int codigo;
	String nombre;
	float creditos;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCreditos() {
		return creditos;
	}

	public void setCreditos(float creditos) {
		this.creditos = creditos;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AsignaturaTO) {
			AsignaturaTO a = (AsignaturaTO)obj;
			return a.getCodigo() == this.getCodigo();
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "" + codigo;
	}
}
