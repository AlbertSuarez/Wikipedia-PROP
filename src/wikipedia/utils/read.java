package wikipedia.utils;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class read {
	//Pre: Exists a file named from file
	//Post: s contains the first line of the file named from file
	public static void read(String s,String file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			s = texto.readLine();
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}
	//Pre: Exists a file named from file
	//Post: s contains as much strings as lines of the WP, each string
	//		contains one line 
	public static void readWP(ArrayList<String> s,String file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			String linia;
			while((linia = texto.readLine()) != null){
				s.add(linia);
			}
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}
}

