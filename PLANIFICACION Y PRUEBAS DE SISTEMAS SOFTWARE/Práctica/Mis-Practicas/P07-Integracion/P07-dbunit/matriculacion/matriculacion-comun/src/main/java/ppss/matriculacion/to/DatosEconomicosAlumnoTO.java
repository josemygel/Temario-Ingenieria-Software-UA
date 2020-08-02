package ppss.matriculacion.to;

public class DatosEconomicosAlumnoTO {

	public static enum TiposBeca {
		completa, media, ninguna
	}
	
	TiposBeca tipoBeca;
	boolean familiaNumerosa;
	boolean repetidor;
	
	public TiposBeca getTipoBeca() {
		return tipoBeca;
	}
	public void setTipoBeca(TiposBeca tipoBeca) {
		this.tipoBeca = tipoBeca;
	}
	public boolean isFamiliaNumerosa() {
		return familiaNumerosa;
	}
	public void setFamiliaNumerosa(boolean familiaNumerosa) {
		this.familiaNumerosa = familiaNumerosa;
	}
	public boolean isRepetidor() {
		return repetidor;
	}
	public void setRepetidor(boolean repetidor) {
		this.repetidor = repetidor;
	}
	
	
}
