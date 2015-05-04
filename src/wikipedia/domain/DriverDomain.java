package wikipedia.domain;

import wikipedia.utils.Utils;
import g13.*;
import java.io.IOException;

import wikipedia.persistence.GraphIO;

/**
 * Driver Domain Class
 * @author G13.2
 */

public class DriverDomain {
	
	/**
	 * The string's representation of operation
	 */
	private static String op;
    
	/**
	* The Option List
	*/
	private static final String[] OPTION_LIST = new String[] {
		"SET_TITLE", "GET_TITLE", "EQUALS_ELEMENT",
		"TO_CATEGORY", "TO_PAGE", "GET_ELEMENTTYPE",
		"ADD_NODE", "ERASE_NODE", "PRINT_COMMUNITY",
		"BELONGS_COMMUNITY", "ADD_COMMUNITY",
		"GET_COMMUNITY_COUNT", "ERASE_COMMUNITY",
		"RESET_COLLECTION", "BELONGS_COLLECTION",
		"PRINT_COLLECTION", "ADD_NODE_TO_COM",
		"ADD_CATEGORY", "ADD_PAGE", "DEL_CATEGORY", "DEL_PAGE",
		"CAT_EXISTS", "PAGE_EXISTS", "ADD_SUPERLINK",
		"ADD_CPLINK", "PRINT_GRAPH", "APPLY_NG",
		"PRINT_OPTION_LIST", "EXIT"
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
	* Print a Integer
	* @param x Integer to be printed
	*/
	public static void printInteger(int x) {
		print(Integer.toString(x));
	}

	/**
	* Print a Double
	* @param x Double to be printed
	*/
	public static void printDouble(double x) {
		print(Double.toString(x));
	}

	/**
	* Print a Boolean
	* @param x Boolean to be printed
	*/
	public static void printBoolean(boolean x) {
		print(Boolean.toString(x));
	}

	/**
	* Print Options
	*/
	public static void printOptions() {
		print("OPTION LIST:");
		int i = 0;
		for (String option : OPTION_LIST) {
			if (i == 6 || i == 10 || i == 17 || i == 27) print("");
			print("* " + option); ++i;
		}
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
	* Read a Integer
	* @return The read Integer 
	* @throws IOException if you can't write the Integer
	*/
	public static int readInteger() throws IOException {
		return Utils.cin.nextInt();
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
		WP W = new WP();
		Community C = new Community();
		CommunityCollection CC = new CommunityCollection();
		Element E = new Element(){};

		printOptions();

		readOption();
		while (!op.equals("EXIT")) {
		    switch(op) {
			// CLASS ELEMENT ************************************
			case "SET_TITLE":
				print("Cambiar título del elemento implicito:");
				E.setTitle(readString());
				break;
			case "GET_TITLE":
				print("Devuelve el título del elemento implicito:");
				if (E.getTitle() == null) print("El elemento implicito no tiene titulo.");
				else print(E.getTitle());
				break;
			case "EQUALS_ELEMENT":
				print("Compara la igualdad entre dos elementos:");
				print("Introduce los titulos de los elementos a comparar");
				Category C1 = new Category(readString());
				Category C2 = new Category(readString());	
				print("true si son iguales, false alternativamente");
				printBoolean(C1.equals(C2));
				break;
			case "TO_CATEGORY":
				print("Convierte el elemento implicito en una categoria:");
				E = new Category(E.getTitle());
				break;
			case "TO_PAGE":
				print("Convierte el elemento implicito en una pagina:");
				E = new Page(E.getTitle());
				break;
			case "GET_ELEMENTTYPE":
				print("Devuelve el tipo del elemento implicito: Categoria (cat) o Pagina (page)");
				print(Element.toElementTypeString(E.getElementType()));
				break;
				
			// CLASS COMMUNITY ***********************************
			case "ADD_NODE":
				print("Anade un nodo a la comunidad implicita:");
				print("Introduce el título del nodo (Categoria) a anadir:");
				ONode n = new ONode(new Category(readString()));
				C.addNode(n);
				break;
			case "ERASE_NODE":
				print("Elimina el nodo indicado de la comunidad implicita:");
				print("Introduce el titulo del nodo (Categoria) a eliminar:");
				ONode n2 = new ONode(new Category(readString()));
				if (!C.belongs(n2)) print("El nodo indicado no existe.");
				else C.eraseNode(n2);
				break;
			case "PRINT_COMMUNITY":
				print("Imprime por pantalla la comunidad implicita:");
				C.printCommunity();
				break;
			case "BELONGS_COMMUNITY":
				print("Devuelve cierto si el nodo indicado existe en la comunidad y falso alternativamente:");
				print("Introduce el titulo del nodo (Categoria) a buscar:");
				ONode n3 = new ONode(new Category(readString()));
				printBoolean(C.belongs(n3));
				break;

			// CLASS COMMUNITY COLLECTION ******************
			case "ADD_COMMUNITY":
				print("Anade una comunidad a la colleccion implicita:");
				CC.addCommunity(new Community());
				break;
			case "GET_COMMUNITY_COUNT":
				print("Muestra el numero de comunidades a la colleccion implicita:");
				printInteger(CC.getCommunityCount());
				break;
			case "ERASE_COMMUNITY":
				print("Elimina una comunidad de la colleccion:");
				print("Indica el numero de la comunidad que desea eliminar:");
				Community aux = CC.getCommunity(readInteger()-1);
				CC.eraseCommunity(aux);
				break;
			case "RESET_COLLECTION":
				print("Resetea la colección implicita:");
				CC.resetCollection();
				break;
			case "BELONGS_COLLECTION":
				print("Devuelve cierto si el nodo indicado existe en la coleccion y falso alternativamente:");
				print("Introduce el titulo del nodo (Categoria) a buscar:");
				ONode nBelong = new ONode(new Category(readString()));
				printBoolean(CC.belongs(nBelong));
				break;
			case "PRINT_COLLECTION":
				print("Imprime la collecion implicita:");
				CC.printCollection();
				break;
			case "ADD_NODE_TO_COM":
				print("Anade un nodo a la comunidad indicada de la coleccion:");
				print("Indica en que comunidad desea anadir el nodo:");
				int j = readInteger() -1;
				Community CAux = CC.getCommunity(j);
				print("Introduce el título del nodo (Categoria) a anadir:");
				Node m = new ONode(new Category(readString()));
				CAux.addNode(m);
				CC.setCommunity(j,CAux);
				break;
				
				
			// CLASS WP ************************************
			case "ADD_CATEGORY":
				print("Anade una categoria al grafo implicito:");
				print("Introduce el titulo de la categoria a anadir:");
				W.addCategory(new Category(readString()));
				break;
			case "ADD_PAGE":
				print("Anade una pagina al grafo implicito:");
				print("Introduce el titulo de la pagina a anadir:");
				W.addPage(new Page(readString()));
				break;
			case "DEL_CATEGORY":
				print("Elimina una categoria al grafo implicito:");
				print("Introduce el titulo de la categoria a eliminar:");
				W.delCategory(new Category(readString()));
				break;
			case "DEL_PAGE":
				print("Elimina una pagina al grafo implicito:");
				print("Introduce el titulo de la pagina a eliminar:");
				W.delPage(new Page(readString()));
				break;
			case "CAT_EXISTS":
				print("Devuelve cierto si la categoria existe en el grafo implicito, falso alternativamente:");
				print("Introduce el titulo de la categoria a buscar:");
				printBoolean(W.catExists(readString()));
				break;
			case "PAGE_EXISTS":
				print("Devuelve cierto si la pagina existe en el grafo implicito, falso alternativamente:");
				print("Introduce el titulo de la pagina a buscar:");
				printBoolean(W.pagExists(readString()));
				break;
			case "ADD_SUPERLINK":
				print("Anade un enlace de SuperCategoria entre dos nodos:");
				print("Introduce los titulos de las dos categorias:");
				W.addSuperLink(new Category(readString()), new Category(readString()));
				break;
			case "ADD_CPLINK":
				print("Anade un enlace de Categoria a Pagina entre dos nodos:");
				print("Introduce el titulo de la categoria y de la pagina:");
				W.addCPLink(new Category(readString()), new Page(readString()));
				break;
			case "PRINT_GRAPH":
				print("Imprime el grafo implicito:");
				GraphIO.writeGraphWPformat(W.getGraph());
				break;
			case "APPLY_NG":
				print("Aplica el algoritmo de Newman-Girvan al grafo implicito:");
				print("Indica cuantas comunidades desea obtener:");
				W.applyNewmanGirvan(readInteger());
				break;


			// OTHERS **************************************	
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
