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
        "BELONGS_COMMUNITY", "ADD_COMMUNITY",
        "GET_COMMUNITY_COUNT", "ERASE_COMMUNITY",
        "RESET_COLLECTION", "BELONGS_COLLECTION",
        "PRINT_COLLECTION", "ADD_NODE_TO_COM",
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
        	if (i == 6 || i == 10 || i == 17) print("");
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
   
    public static int readInteger() throws IOException {
        return Integer.parseInt(readString());
    }
    
    private static void readOption() throws IOException {
        print("\nEnter an option: ");
        op = readString();
    }
 
   
    public static void main(String args[]) throws IOException {
        OGraph G = new OGraph();
        Community C = new Community();
        CommunityCollection CC = new CommunityCollection();
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
            	case "ADD_COMMUNITY":
            		print("Anade una comunidad a la colleccion implicita:");
            		CC.addCommunity(new Community());
            		break;
            	case "GET_COMMUNITY_COUNT":
            		print("Muestra el numero de comunidades a la colleccion implicita:");
            		printInteger(CC.getCommunityCount());
            		break;
            	case "ERASE_COMMUNITY":
            		// NO FINALIZADA
            		print("Elimina una comunidad de la colleccion:");
            		print("Indica el numero de la comunidad que desea eliminar:");
            		int i = readInteger();
            		
            		break;
            	case "RESET_COLLECTION":
            		print("Resetea la colección implicita:");
            		CC.resetCollection();
            		break;
            	case "BELONGS_COLLECTION":
            		// NO FINALIZADA
            		print("Indica si una comunidad esta en la coleccion o no:");
            		print("Indica el numero de nodos que tiene la comunidad a buscar:");
            		int size = readInteger();
            		print("Introduce los titulos de los nodos de la comunidad buscada:");
            		Community aux = new Community();
            		for (i = 0; i < size; ++i) C.addNode(new ONode(new Category(readString())));
            		print("True si existe en la coleccion, false alternativamente:");
            		printBoolean(CC.belongs(aux));
            		break;
            	case "PRINT_COLLECTION":
            		print("Imprime la collecion implicita:");
            		CC.printCollection();
            		break;
            	case "ADD_NODE_TO_COM":
            		// NO FINALIZADA
            		print("Anade un nodo a la comunidad indicada de la coleccion:");
            		print("Indica en que comunidad desea anadir el nodo:");
            		int j = readInteger();
            		
            		print("Introduce el título del nodo (Categoria) a anadir:");
            		Node m = new ONode(new Category(readString()));
            		C.addNode(m);
            		
            		break;
            		
            		
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
