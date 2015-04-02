package es.upc.fib.prop.shared13;

import java.util.*;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Graph
{
    /**
     * As you can see the graph is created using the interfaces Map and Set
       (if you don't know what a java interface is google it), this way if you
       need to keep the edges or the nodes in a certain order you can use
       the type of Map/Set (See java.utils documentation) according to your needs,
       In this implementation I will use LinkedHashedMap because I don't care much about the order
       so it uses the entry one. If you are interested in a certain ordering consider using TreeMaps
       (all valid for sets too)
     */
    private Map<Node,Set<Edge>> graph;

    /**
     * Graph getter for subclasses
     */
    protected Map<Node, Set<Edge>> getGraph()
    {
        return graph;
    }

    /**
     * @pre True
     * @post An empty graph is created
	 * Creates an empty graph
	 */
    public Graph()
    {
        graph = new LinkedHashMap<Node,Set<Edge>>();
    }

	/**
     * @pre True
     * @post Returns true if the node is in the graph, false otherwise
	 * @param n
	 * @return
     * Returns true if the node is in the graph, false otherwise
	 */
    public boolean nodeExists(Node n) {
        return graph.containsKey(n);
    }

	/**
     * @pre The Node n is not part of the graph
     * @post The Node n is part of the graph
     * @param n
	 * The argument is added to the graph, without adjacencies
	 IF IT ALREADY EXISTS ALL OF ITS EDGES WILL BE DELETED, WITHOUT DELETING ITS NEIGHBORS EDGES
	 AS A RESULT THE GRAPH WILL BE IN AN ERRONEOUS STATE. CONSIDER YOURSELF WARNED
	 */
    public void addNode(Node n) {
        graph.put(n,new LinkedHashSet<Edge>());
    }

	/**
     * @pre The Node n is part of the graph
     * @post The Node n is not a part of the graph
     * @param n
	 * The node n is deleted, so are all its edges, including its neighbors edges
	 */
    public void deleteNode(Node n) {
        //First we gotta delete all the neighbors edges
        Set<Edge> es = graph.get(n);
        //We look at each Edge and delete it from its neighbor's set
        for(Edge e : es){
            graph.get(e.getNeighbor(n)).remove(e);
        }
        //Finally, we remove the node from the graph (its edges implicitly)
        graph.remove(n);

    }

	/**
     * @pre Both Nodes from the Edge e are part of the graph
     * @post The Edge e is added to the graph
     * @param e
	 */
    public void addEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);
        graph.get(n1).add(e);
        graph.get(n2).add(e);
    }

	/**
     * @param n1
     * @param n2
     * @pre THe Nodes n1 and n2 are part of the graph and there is an Edge e between them
     * @post The Edge e is removed from the Graph
	 */
    //TODO: change name to remove
    public void removeEdge(Node n1,Node n2) {
        //We need to get the list of edges of both nodes and delete the edge

        Set<Edge> se1 = graph.get(n1);
        Set<Edge> se2 = graph.get(n2);

        for(Edge e:se1){
            if(e.getNeighbor(n1)==n2) {
                se1.remove(e);
                break;
            }
        }
        for(Edge e:se2){
            if(e.getNeighbor(n2)==n1){
                se2.remove(e);
                break;
            }
        }

    }


	/**
     * @pre The Edge e is part of the graph
     * @post The Edge e is deleted from the graph
     * @param e
	 * deletes the edge from the graph
	 */
    public void deleteEdge(Edge e)
    {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);
        graph.get(n1).remove(e);
        graph.get(n2).remove(e);
    }

	/**
     * @pre True
     * @post Returns the set of edges of a node
     * @param n
     * @return
	 * returns the set of edges of that node DO NOT MODIFY IT DIRECTLY
	 */
    public Set<Edge> nodeEdges(Node n)
    {
        return graph.get(n);
    }

}
