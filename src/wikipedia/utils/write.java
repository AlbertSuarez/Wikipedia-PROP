package wikipedia.utils;
import java.io.File;
import java.io.FileWriter;

public class write {

	//Pre:
	//Post: s has been added to the data file
	public static void write(String s, String file) {
		try {
			//creamos o abrimos acceso a fichero txt
			File data=new File(file);
			//el segundo apartado nos dice si sobreescribimos o escribimos al final
			//como está en true, no sobreescribimos.
			FileWriter escribe=new FileWriter(data,true); 
			escribe.write(s);
			escribe.close();
			}
		catch(Exception e) {
			System.out.println("Error al escribir");
		}
	}
	
	//Pre: s contains a WP line
	//Post: the line contained in has been added to the data file
	public static void writeWPline(String[] s, String file) {
		try {
			//creamos o abrimos acceso a fichero txt
			File data=new File(file);
			FileWriter escribe=new FileWriter(data,true); 
			for(int i = 0; i < s.length;++i){
				if( i != 0)escribe.write(" ");
				escribe.write(s[i]);
			}
			escribe.write(System.getProperty( "line.separator" ));
			escribe.close();
			}
		catch(Exception e) {
			System.out.println("Error al escribir");
		}
	}
}

