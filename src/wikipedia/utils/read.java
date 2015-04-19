package wikipedia.utils;
import java.io.FileReader;
import java.io.BufferedReader;

public class read {
	//Pre:
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
	//Pre:
	//Post: s contains as much strings as lines of the WP, each string
	//		contains one line 
	public static void readWP(String[] s,String file){
		try{
			FileReader lector=new FileReader(file);
			BufferedReader texto =new BufferedReader(lector);
			int i = 0;
			while((s[i] = texto.readLine()) != null){
				 String[] nuevo = new String[s.length + 1];
				 System.arraycopy(s, 0, nuevo, 0, s.length);
				 s = nuevo;
				 ++i;
			}
		}
		catch(Exception e){
			System.out.println("Error al leer");
		}
	}
}

