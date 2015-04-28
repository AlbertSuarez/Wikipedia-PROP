package wikipedia.domain;

import g13.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DriverDomain {
	private static String op;
    private static BufferedReader cin;
    private static final String[] OPTION_LIST = new String[] {
        "SET_TITLE", "GET_TITLE", "EQUALS_ELEMENT",
        "TO_CATEGORY", "TO_PAGE", "GET_ELEMENTTYPE",
        "ADD_NODE", "ERASE_NODE", "PRINT_COMMUNITY",
        "BELONGS_COMMUNITY",
        "PRINT_OPTION_LIST", "EXIT"
    };
   
    // FUNCIONS AUXILIARS
   
    public static void print(String s) {
        System.out.println(s);
    }

    public static void printInteger(int x) {
        print(Integer.toString(x));
    }

    public static void printDouble(double x) {
        print(Double.toString(x));
    }

    public static void printBoolean(boolean x) {
        print(Boolean.toString(x));
    }
    
    public static void printOptions() {
        print("OPTION LIST:");
        int i = 0;
        for (String option : OPTION_LIST) {
        	if (i == 6 || i == 10) print("");
        	print("* " + option); ++i;
        }
        print("END OF OPTION LIST\n");
    }
 
    private static void printErr(String s) {
        print("!!! ERR: " + s);
    }
 
    public static String readString() throws IOException {
        return cin.readLine();
    }
   
    private static void readOption() throws IOException {
        print("\nEnter an option: ");
        op = readString();
    }
 
   
    public static void main(String args[]) throws IOException {
        OGraph G = new OGraph();
        Community C = new Community();
        Element E = new Element(){};
        cin = new BufferedReader(new InputStreamReader(System.in));
 
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
            		// NO FUNCA NI PA TRAS
            		print("Compara la igualdad entre dos elementos:");
            		print("Introduce los titulos de los elementos a comparar");
            		Element E1 = new Element(){};
            		E1.setTitle(readString());
            		
            		Element E2 = new Element(){};
            		E2.setTitle(readString());
            		
            		print("true si son iguales, false alternativamente");
            		printBoolean(E1.equals(E2));
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
            		Node n = new ONode(new Category(readString()));
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
            		
            		
            		
            		
            		
            		
            	// CLASS WP ************************************
            		
            		
            		
            		
            	// CLASS NEWMANN-GIRVAN ************************
            		
            		
            		
            		
            		
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
