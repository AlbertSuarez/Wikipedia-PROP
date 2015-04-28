package wikipedia.utils;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class utils
{
	
	
	// Pre:  True
	// Post: Print 'obj' with a newline
	public static void print(Object obj)
	{
		System.out.println(obj);
	}
	
	
	// Pre:  True
	// Post: Print a newline by itself
	public static void print()
	{
		System.out.println();
	}
	
	
	// Pre:  True
	// Post: Print with no line break
	public static void printnb(Object obj)
	{
		System.out.print(obj);
	}
	
	
	// Pre:  True
	// Post: The new Java SE5 printf() (from C)
	public static PrintStream printf(String format, Object... args) {
		return System.out.printf(format, args);
	}
	
	
	//Pre: Exists a file named from file
	//Post: s contains the first line of the file named from file
	public static void read(String s,String file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			s = texto.readLine();
			texto.close();
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}

	
	//Pre: Exists a file named from file
	//Post: s contains as much strings as lines of the WP, each string contains one line
	public static void readWP(ArrayList<String> s,File file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			String linia;
			while((linia = texto.readLine()) != null){
				s.add(linia);
			}
			texto.close();
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}
	
	
	//Pre: Exists a file named from file
	//Post: s contains as much strings as lines of the WP, each string contains one line
	public static void readWP(ArrayList<String> s,String file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			String linia;
			while((linia = texto.readLine()) != null){
				s.add(linia);
			}
			texto.close();
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}
		

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
	
	
	//anadir filter para filtrar que el archivo debe ser formato txt;
	public static File Choose(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    return selectedFile;
		}
		return null;
	}
		
}
