package wikipedia.persistence;
 
import g13.OGraph;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class DriverPersistence {
       
        private static String op;
    private static BufferedReader cin;
    private static final String[] OPTION_LIST = new String[] {
        "READ_GRAPH", "WRITE_GRAPH", "READ_WP_GRAPH", "WRITE_WP_GRAPH",
        "LOAD_GRAPH", "SAVE_GRAPH", "READ_AND_SAVE_GRAPH",
        "WRITE_DOT_FORMAT", "SAVE_DOT_FORMAT", "PRINT_OPTION_LIST", "EXIT"
    };
   
    // FUNCIONS AUXILIARS
   
    public static void print(String s) {
        System.out.println(s);
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
        cin = new BufferedReader(new InputStreamReader(System.in));
 
        printOptions();
 
        readOption();
        while (!op.equals("EXIT")) {
                switch(op) {
                        case "READ_GRAPH":
                                print("Introduce un grafo:");
                                G = GraphIO.readGraph();
                                break;
                        case "WRITE_GRAPH":
                                print("Escribe el grafo:");
                                GraphIO.writeGraph(G);
                                break;
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
                                w = GraphIO.readGraphWPformat();
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