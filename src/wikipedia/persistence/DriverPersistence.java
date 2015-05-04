package wikipedia.persistence;

import wikipedia.utils.Utils;
import g13.OGraph;
import java.io.IOException;

/**
 * Driver Persistence Class
 * @author G13.2
 */
public class DriverPersistence {
    
	/**
	 * The string's representation of operation
	 */
	private static String op;

	/**
	* The Option List
	*/
	private static final String[] OPTION_LIST = new String[] {
	"READ_WP_GRAPH", "WRITE_WP_GRAPH",
	"LOAD_GRAPH", "SAVE_GRAPH", "READ_AND_SAVE_GRAPH",
	"WRITE_DOT_FORMAT", "SAVE_DOT_FORMAT", "PRINT_OPTION_LIST", "EXIT"
	};

	// FUNCIONS AUXILIARS

	/**
	* Print a String
	* @param s String to be printed
	*/
	public static void print(String s) {
		System.out.println(s);
	}

	/**
	* Print Options
	*/
	public static void printOptions() {
		print("OPTION LIST:");
		for (String option : OPTION_LIST) print("* " + option);
		print("END OF OPTION LIST\n");
	}

	/**
	* Print a Error
	* @param s Error to be printed
	*/
	private static void printErr(String s) {
		print("!!! ERR: " + s);
	}

	/**
	* Read a String
	* @return The read String 
	* @throws IOException if you can't write the String
	*/
	public static String readString() throws IOException {
		return Utils.cin.next();
	}

	/**
	* Read a Option
	* @throws IOException if you can't write the Option
	*/
	private static void readOption() throws IOException {
		print("\nEnter an option: ");
		op = readString();
	}

    /**
     * The main method
     * @param args Arguments of main
     * @throws IOException if you can't write for the program
     */
    public static void main(String args[]) throws IOException {
	OGraph G = new OGraph();
 
	printOptions();
 
	readOption();
	while (!op.equals("EXIT")) {
		switch(op) {
			case "READ_WP_GRAPH":
				print("Introduce un grafo con el formato de Wikipedia:");
				G = GraphIO.readGraphWPformat();
				break;
			case "WRITE_WP_GRAPH":
				print("Escribe el grafo con el formato de Wikipedia:");
				GraphIO.writeGraphWPformat(G);
				break;
			case "LOAD_GRAPH":
				print("Selecciona el Archivo txt que contiene el grafo:");
				G = GraphIO.loadWP();
				break;
			case "SAVE_GRAPH":
				print("Salva el grafo implicito en un fichero.");
				GraphIO.saveWP(G);
				break;
			case "READ_AND_SAVE_GRAPH":
				print("Introduce un grafo con el formato de Wikipedia:");
				OGraph w = new OGraph();
				//w = GraphIO.readGraphWPformat();
				print("Salva el grafo escrito en un fichero");
				GraphIO.saveWP(w);
				break;
			case "WRITE_DOT_FORMAT":
					print("Escribe el grafo en formato DOT");
					print("DOT es un programa que representa el grafico en un dibujo");
					GraphIO.writeDOTformat(G);
					break;
			case "SAVE_DOT_FORMAT":
					print("Convierte el grafo implicito en formato DOT y lo salva en un fichero");
					GraphIO.saveWP(G);
					break;
			case "PRINT_OPTION_LIST":
					printOptions();
					break;
			default:
				printErr("Invalid operation name\n*** HINT: Enter " +
						"PRINT_OPTION_LIST to list available options");
				break;
		}
	    readOption();
	}
    }
}
