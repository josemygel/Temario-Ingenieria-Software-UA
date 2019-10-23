package adaboost;

/**
 * IMPORTANTE:  No modificar esta clase a menos de que se quieran considerar
 *              imagenes en otro formato
 * Extensiones soportadas: GIF, JPEG, TIFF, PNG, BMP
 * @author dviejo
 */
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ImageFilter implements FilenameFilter {

	ArrayList<String> validExtensions;
	public ImageFilter() {
		super();
		
		validExtensions = new ArrayList<String>();
		validExtensions.add("gif");
		validExtensions.add("jpg");
		validExtensions.add("tiff");
		validExtensions.add("png");
		validExtensions.add("bmp");
	}

	@Override
	public boolean accept(File dir, String name) {
		boolean returnValue=false;
		int cont, tam;

		String extension = ImageFilter.getExtension(name);
		tam = validExtensions.size();
		for(cont=0;cont<tam&&!returnValue;cont++)
		{
			returnValue=validExtensions.get(cont).equals(extension);
		}
		return returnValue;
	}
	
    public static String getExtension(String fileName) {
    	String extension = "";
        int i = fileName.lastIndexOf('.');

        if (i > 0 && i < fileName.length() - 1)
        	extension = fileName.substring(i + 1).toLowerCase();
        return extension;
    }


}
