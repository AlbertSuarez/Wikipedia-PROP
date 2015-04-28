package wikipedia.domain;

import g13.OGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wikipedia.persistence.GraphIO;

public class DriverDomain {
	private static String op;
    private static BufferedReader cin;
    private static final String[] OPTION_LIST = new String[] {
        "SET_TITLE", "GET_TITLE", "EQUALS_ELEMENT",
        "TO_CATEGORY", "TO_PAGE", "GET_ELEMENTTYPE",
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
        for (String option : OPTION_LIST) print("* " + option);
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
        Element E = new Element(){};
        cin = new BufferedReader(new InputStreamReader(System.in));
 
        printOptions();
 
        readOption();
        while (!op.equals("EXIT")) {
            switch(op) {
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
