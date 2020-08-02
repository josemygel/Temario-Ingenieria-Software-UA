package ppss.matriculacion.bo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlumnoBR {

	public boolean validaNif(String nif) {
		if(nif.length()!=9 || nif==null) {
		    return false;
		}
		
		String dni = nif.substring(0, 8);
		char letra = nif.charAt(8); 
		
		Pattern pattern = Pattern.compile("[0-9]{8,8}");
		Matcher matcher = pattern.matcher(dni); 
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

		long ldni = 0;
		try {
			ldni = Long.parseLong(dni);
		} catch(NumberFormatException e) {
			return false;
		}

		int indice = (int)(ldni % 23);
		char letraEsperada = letras.charAt(indice);
		
		return matcher.matches() && letra==letraEsperada;
	}

}
