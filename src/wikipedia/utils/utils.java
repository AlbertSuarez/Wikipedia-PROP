package wikipedia.utils;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Utils class
 * @author G13.2
 */
public class utils
{
	
	/**
	 * Print with a newline
	 * @param obj Object to be printed
	 */
	public static void print(Object obj)
	{
		System.out.println(obj);
	}
	
	/**
	 * Print with a newline by itself
	 */
	public static void print()
	{
		System.out.println();
	}
	
	
	/**
	 * Print with a no line break
	 * @param obj Object to be printed
	 */
	public static void printnb(Object obj)
	{
		System.out.print(obj);
	}
	
	
	/**
	 * The new Java SE5 printf()
	 * @param format The format to print
	 * @param args The arguments to print
	 * @return The result of print
	 */
	public static PrintStream printf(String format, Object... args) {
		return System.out.printf(format, args);
	}
	
	/**
	 * Read with line break
	 * @return The read String
	 * @throws IOException if you can't write the String
	 */
	public static String readln() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String s = read.readLine();
		return s;
	}
	
	
	/**
	 * Read a String
	 * @param s Contains the first line of the file named from file
	 * @param file The file
	 * @pre Exists a file named from file
	 */
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

	/**
	 * Read a String with WP format
	 * @param s Contains as much strings as lines of the WP, each string contains one line
	 * @param file The file
	 * @pre Exists a file named from file
	 */
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
		
	/**
	 * Write a String
	 * @param s Has been added to the data file
	 * @param file The file
	 */
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
	
	/**
	 * Write a String with WP format
	 * @param s The line contained in has been added to the data file
	 * @param file The file
	 * @pre s contains a WP line
	 */
	public static void writeWPline(String[] s, File file) {
		try {
			//creamos o abrimos acceso a fichero txt
			FileWriter escribe=new FileWriter(file,true); 
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
	
	/**
	 * Choose a file
	 * @return A file
	 */
	public static File Choose(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setDialogTitle("Specify which file you wanna load");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    return selectedFile;
		}
		return null;
	}
	
	/**
	 * Save to file
	 * @return A file
	 */
	public static File save(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setDialogTitle("Specify the name of the file to save");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    return selectedFile;
		}
		return null;
	}
		
}
