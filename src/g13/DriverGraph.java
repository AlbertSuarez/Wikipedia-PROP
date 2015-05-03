package g13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wikipedia.domain.*;

/**
 * Driver Graph Class
 * @author G13.2 G13.1
 */
public class DriverGraph {

	/**
	 * NodePair Private Class
	 * @author G13.1
	 */
    private static class NodePair {
    	
    	/**
    	 * The first node of pair.
    	 */
        public Node first;
        
        /**
         * The second node of pair.
         */
        public Node second;

        /**
         * Create a Node Pair
         * @param first The first node
         * @param second The second node
         */
        public NodePair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }

	/**
	 * The string's representation of operation
	 */
    private static String op;
    
	/**
	 * The used Buffered Reader
	 */
    private static BufferedReader cin;
    
    /**
     * The Option List
     */
    private static final String[] OPTION_LIST = new String[] {
            "ADD_NODE", "REMOVE_NODE", "ADD_EDGE", "REMOVE_EDGE",
            "HAS_EDGE", "HAS_VALID_EDGE", "HAS_NODE", "GET_ORDER",
            "GET_EDGE_COUNT", "GET_VALID_EDGE_COUNT", "GET_EDGES",
            "GET_VALID_EDGES", "GET_NODES", "GET_ADJACENCY_LIST",
            "GET_VALID_ADJACENCY_LIST", "GET_NODE_DEGREE",
            "GET_VALID_NODE_DEGREE", "GET_EDGE", "REMOVE_ALL_NODE_EDGES",
            "INVALIDATE_ALL_EDGES", "SET_EDGE_WEIGHT",
            "SET_EDGE_VALIDITY", "PRINT_GRAPH", "PRINT_OPTION_LIST", "EXIT"

    };

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
     * Read a Boolean
     * @return The read Boolean 
     * @throws IOException if you can't write the Boolean
     */
    public static boolean readBoolean() throws IOException {
        return Boolean.parseBoolean(readString());
    }

    /**
     * Read a Integer
     * @return The read Integer 
     * @throws IOException if you can't write the Integer
     */
    public static int readInteger() throws IOException {
        return Integer.parseInt(readString());
    }
    
    /**
     * Read a String
     * @return The read String 
     * @throws IOException if you can't write the String
     */
    public static String readString() throws IOException {
        return cin.readLine();
    }
    
    /**
     * Read a Double
     * @return The read Double 
     * @throws IOException if you can't write the Double
     */
    public static double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }
    
    /**
     * The main method
     * @param args Arguments of main
     * @throws IOException if you can't write for the program
     */
    public static void main(String args[]) throws IOException {
        OGraph G = new OGraph();
        cin = new BufferedReader(new InputStreamReader(System.in));

        printOptions();

        readOption();
        while (!op.equals("EXIT")) {
            switch(op) {
                case "ADD_NODE":
                    G.addNode(readNode());
                    break;
                case "REMOVE_NODE":
                    G.removeNode(readNode());
                    break;
                case "ADD_EDGE":
                    G.addEdge(readEdge());
                    break;
                case "REMOVE_EDGE":
                    NodePair np = readNodePair();
                    G.removeEdge(np.first, np.second);
                    break;
                case "HAS_EDGE":
                    NodePair np2 = readNodePair();
                    printBoolean(G.hasEdge(np2.first, np2.second));
                    break;
                case "HAS_NODE":
                    printBoolean(G.hasNode(readNode()));
                    break;
                case "GET_ORDER":
                    printInteger(G.getOrder());
                    break;
                case "GET_EDGE_COUNT":
                    printInteger(G.getEdgeCount());
                    break;
                case "GET_VALID_EDGE_COUNT":
                    printInteger(G.getValidEdgeCount());
                    break;
                case "GET_EDGES":
                    print(G.getEdges().toString());
                    break;
                case "GET_NODES":
                    print(G.getNodes().toString());
                    break;
                case "GET_VALID_EDGES":
                    print(G.getValidEdges().toString());
                    break;
                case "GET_ADJACENCY_LIST":
                    print(G.getAdjacencyList(readNode()).toString());
                    break;
                case "GET_VALID_ADJACENCY_LIST":
                    print(G.getValidAdjacencyList(readNode()).toString());
                    break;
                case "GET_NODE_DEGREE":
                    printInteger(G.getNodeDegree(readNode()));
                    break;
                case "GET_VALID_NODE_DEGREE":
                    printInteger(G.getValidNodeDegree(readNode()));
                    break;
                case "GET_EDGE":
                    NodePair np3 = readNodePair();
                    print(G.getEdge(np3.first, np3.second).toString());
                    break;
                case "HAS_VALID_EDGE":
                    NodePair np4 = readNodePair();
                    printBoolean(G.hasValidEdge(np4.first, np4.second));
                    break;
                case "REMOVE_ALL_NODE_EDGES":
                    G.removeAllNodeEdges(readNode());
                    break;
                case "INVALIDATE_ALL_EDGES":
                    G.invalidateAllEdges();
                    break;
                case "SET_EDGE_WEIGHT":
                    Edge e = readEdge();
                    G.getEdge(e.getNode(), e.getNeighbor(e.getNode()))
                            .setWeight(e.getWeight());
                    break;
                case "SET_EDGE_VALIDITY":
                    NodePair np5 = readNodePair();
                    print("Enter the desired validity: ");
                    G.getEdge(np5.first, np5.second)
                            .setValidity(readBoolean());
                    break;
                case "PRINT_EDGE_WEIGHT":
                    NodePair np6 = readNodePair();
                    printDouble(G.getEdge(np6.first, np6.second).getWeight());
                    break;
                case "PRINT_EDGE_VALIDITY":
                    NodePair np7 = readNodePair();
                    printBoolean(G.getEdge(np7.first, np7.second).isValid());
                    break;
                case "PRINT_GRAPH":
                    print(G.toString());
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

    /**
     * Read a NodePair
     * @return The read NodePair 
     * @throws IOException if you can't write the NodePair
     */
    private static NodePair readNodePair() throws IOException {
        print("Enter the first node:\n");
        ONode n1 = readNode();
        print("Enter the second node:\n");
        ONode n2 = readNode();
        return new NodePair(n1, n2);
    }

    /**
     * Read a OEdge
     * @return The read OEdge 
     * @throws IOException if you can't write the OEdge
     */
    private static OEdge readEdge() throws IOException {
        NodePair e = readNodePair();
        print("Enter the edge weight (double): ");
        double w = readDouble();
        print("Enter the type of edge (CsupC or CP):");
        return new OEdge(e.first, e.second, w, true, OEdge.toEdgeType(readString()));
    }

    /**
     * Read a ONode
     * @return The read ONode 
     * @throws IOException if you can't write the ONode
     */
    private static ONode readNode() throws IOException {
    	print("Enter the type of Node: Category or Page:");
    	Element e = new Element(){};
    	String type = readString();
    	if (type.equals("Category")) e = new Category();
    	else e = new Page();
        print("Enter the title of node's element: ");
        e.setTitle(readString());
        return new ONode(e);
    }

    /**
     * Read a Option
     * @throws IOException if you can't write the Option
     */
    private static void readOption() throws IOException {
        print("\nEnter an option: ");
        op = readString();
    }
}
