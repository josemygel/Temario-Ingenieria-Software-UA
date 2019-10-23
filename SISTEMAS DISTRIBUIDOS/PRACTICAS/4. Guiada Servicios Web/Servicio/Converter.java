package master;

public class Converter
{
  public float celsiusToFarenheit ( float celsius )
  {
    return (celsius * 9 / 5) + 32;
  }

  public float farenheitToCelsius ( float farenheit )
  {
    return (farenheit - 32) * 5 / 9;
  }
}




package station;

//import java.util.Random;
import java.io.*;


public class Station {
	private String lcd;
	private int h,t,l;

	/*Lectura de datos del archivo*/
	public int getTemperature(){ return Integer.parseInt(getData()[0]); }
	public int getHumedity(){ return Integer.parseInt(getData()[1]); }
	public int getLuminity(){ return Integer.parseInt(getData()[2]); }
	public String getLCD(){ return getData()[3]; }

	/*Falta actualizar los datos en el archivo*/
	public void lcd(String lcd){ this.lcd = lcd; }
	private void setT(int t2){ t=t2; }
	private void setH(int h2){ h=h2; }
	private void setL(int l2){ l=l2; }
	
	/*Comprueba que el setteo de datos sea correcto*/
	public void t(int t2){ if(t2 >=-30 && t2 <= 50) setT(t2); }
	public void h(int h2){ if(h2 >=0 && h2 <= 100) setH(h2); }
	public void l(int l2){ if(l2 >=0 && l2 <= 800) setL(l2); }


	// Metodo para obtener de un FileInputStream y una codificacion un String
// (obtener el HTML)
private String getFileContent(FileInputStream fis, String encoding) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }
}

	private String[] getData() {
		String[] arr = new String[4];
		arr[0] = arr[1] = arr[2] = arr[3] = "-404";
		
		try{
			FileInputStream archivo = new FileInputStream("station.txt");
	
			String content = getFileContent(archivo, "UTF8");
	
			String[] text = content.split("\n");
			
		
			for(int i = 0; i<=3 && i<text.length; i++)
				arr[i] = text[i].split(":")[1];
			//Integer.parseInt(text[i].split(":")[1])
	
			//Control temperatura
			arr[0] = Integer.parseInt(arr[0]) < -30 ? "-30" : arr[0];
			arr[0] = Integer.parseInt(arr[0]) >  50 ?  "50" : arr[0];
	
			//Control de humedad
			arr[1] = Integer.parseInt(arr[1]) < 0 ?   "0" : arr[1];
			arr[1] = Integer.parseInt(arr[1]) > 100 ? "100" : arr[1];
	
			//Control de luz
			arr[2] = Integer.parseInt(arr[2]) < 0 ?   "0" : arr[2];
			arr[2] = Integer.parseInt(arr[2]) > 800 ? "800" : arr[2];
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}

		return arr;
	}
}
