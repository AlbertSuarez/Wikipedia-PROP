package g13;

import java.util.*;

public abstract class Graph {

    private static final String ERR_ADD_EXISTING_NODE =
            "Cannot add a node that already exists in the graph!";
    private static final String ERR_INEX_NODE =
            "The node doesn't belong to the graph";

    private Map<Node, Set<Edge>> G;
    int edgeCount;

    /**
     * Creates an empty Graph
	 */
    public Graph() {
        G = new LinkedHashMap<Node, Set<Edge>>();
        edgeCount = 0;
    }
    
    /**
     * Returns the order i.e number of nodes of the graph
     * @return the order of the graph.
     */
    public int getOrder() {
        return G.size();
    }

    /**
     * Returns the size i.e number of edges of the graph.
     * @return the number of edges of the graph
     */
    public int getEdgeCount() {
        return edgeCount;
    }

    /**
     * Returns a <strong>non-modifiable</strong> version of the set of nodes
     * of the Graph.
     * @return a non-modifiable set of the nodes of the graph
     */
    public Set<Node> getNodeSet() {
        return Collections.unmodifiableSet(G.keySet());
    }

    /**
     * Returns a <strong>non-modifiable</strong> version of the set of edges
     * of the Graph.
     * @return a non-modifiable set with the edges of the graph
     */
    public Set<Edge> getEdgeSet() {
        Set<Edge> result = new LinkedHashSet<Edge>();
        for (Set<Edge> e : G.values()) result.addAll(e);
        return Collections.unmodifiableSet(result);
    }

    /**
     * Returns a <strong>non-modifiable</strong> version of the adjacency
     * list of a given node.
     * @param n The node whose adjacency list is requested
     * @return a non-modifiable set with the adjacent edges of the node n'
     * such that n' belongs to the graph and n.equals(n')
     * @throws IllegalArgumentException if the node n doesn't belong
     * to the Graph
     * @throws NullPointerException if the node n is null
     */
    public Set<Edge> getAdjacencyList(Node n) {
        return Collections.unmodifiableSet(getModifiableAdjacencyList(n));
    }

    /**
     * Returns a modifiable version of the adjacency list of a given node.
     * Note that all the changes made to the returned set will be reflected
     * in the actual Graph object.
     * @param n The node whose adjacency list is requested
     * @return a modifiable set with the adjacent edges of the node n'
     * such that n' belongs to the graph and n.equals(n')
     * @throws IllegalArgumentException if the node n doesn't belong
     * to the Graph
     * @throws NullPointerException if the node n is null
     */
    private Set<Edge> getModifiableAdjacencyList(Node n) {
        if (n == null) throw new NullPointerException();

        Set<Edge> s = G.get(n);

        if (s == null) throw new IllegalArgumentException(ERR_INEX_NODE);

        return s;
    }

	/**
     * Returns whether the node n belongs to the Graph.
	 * @param n The node to test for belonging to the graph
	 * @return true if there exists a node n' such that it belongs to the graph
     * and n.equals(n')
     * @throws NullPointerException if n is null
	 */
    public boolean hasNode(Node n) {
        if (n == null) throw new NullPointerException();
        return G.containsKey(n);
    }

    /**
     * Returns the degree i.e number of adjacent nodes to a given node of the
     * Graph.
     * @param n the node from which to get the degree
     * @return the degree of the node n' such that n' belongs to the graph
     * and n.equals(n')
     * @throws NullPointerException if n is null
     * @throws IllegalArgumentException if the node n doesn't belong to the
     * graph
     */
    public int getNodeDegree(Node n) {
        return getAdjacencyList(n).size();
    }

    /**
     * Returns whether the Graph contains a given Edge <strong>formed by nodes
     * belonging to the graph</strong>.
     * @param e The edge to test for belonging to the graph
     * @return true if there exists an edge e' such that it belongs to the graph
     * and e.equals(e')
     * @throws NullPointerException if e is null
     * @throws IllegalArgumentException if one or both nodes of the edge e
     * don't belong to the graph
     */
    public boolean containsEdge(Edge e) {
        return getAdjacencyList(e.getNode()).contains(e);
    }

    /**
     * Returns a string representation of the graph. First the Graph
     * order and number of edges are printed in the same line,
     * followed by the list of nodes of the graph, followed by the list of
     * edges of the graph. The string ends with a new line.
     * @return a string representation of the graph.
     */
    @Override public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();

        s.append("Order: ").append(getOrder())
                .append(" Number of edges: ").append(getEdgeCount())
                .append(NEW_LINE).append(NEW_LINE)
                .append("Nodes:").append(NEW_LINE);

        for (Node n : G.keySet()) s.append("    ").append(n).append(NEW_LINE);

        s.append(NEW_LINE).append("Edges:").append(NEW_LINE);

        for (Set<Edge> set : G.values())
            for (Edge e : set) s.append("    ").append(e).append(NEW_LINE);

        return s.toString();
    }

    /**
     * Adds a node to the graph. Note that the node <strong>must</strong>
     * not belong to the Graph before the call i.e there must not be any node
     * n' belonging to the Graph such that n.equals(n')
     * @param n The node to add to the graph
     * @throws IllegalArgumentException if the node n already belongs to the
     * graph
     */
    public void addNode(Node n) {
        if (this.hasNode(n))
            throw new IllegalArgumentException(ERR_ADD_EXISTING_NODE);
        else G.put(n, new LinkedHashSet<Edge>());
    }

    /**
     * Removes a given node n' from the graph if it belonged to the Graph
     * and returns whether it belonged (i.e whether the graph has been modified
     * by the call). The node n' is such that n.equals(n')
     * @param n the node to remove from the graph
     * @return true if the node belonged to the graph before the removal try
     */
    public boolean removeNode(Node n) {
        if (n == null) throw new NullPointerException();

        if (!this.hasNode(n)) return false;
        else {
            edgeCount -= getNodeDegree(n);
            for (Edge e : G.get(n)) G.get(e.getNeighbor(n)).remove(e);
            G.remove(n);
            return true;
        }
    }

    /**
     * Adds a given edge to the Graph and returns whether it didn't already
     * belong to the graph before the call. Note that the graph will not be
     * modified if the edge already belongs to the Graph, i.e there is an edge
     * e' such that e' belongs to the Graph and e.equals(e')
     * @param e The edge to add to the graph
     * @return true if the edge didn't belong to the graph before the call
     * @throws NullPointerException if e is null
     * @throws IllegalArgumentException if one or both nodes of the edge
     * don't belong to the graph
     */
    public boolean addEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        boolean added = getModifiableAdjacencyList(n1).add(e) &&
                        getModifiableAdjacencyList(n2).add(e);
        if (added) ++edgeCount;
        return added;
    }

    /**
     * Removes an edge e' from the graph and returns whether the edge was in
     * the graph before the call. e' is such that e.equals(e').
     * @param e The edge to remove
     * @return true if the edge belonged to the graph before the removal try
     * @throws NullPointerException if e is null
     * @throws IllegalArgumentException if one or both nodes of the edge
     * don't belong to the graph
     */
    public boolean removeEdge(Edge e) {
        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);

        boolean removed = getModifiableAdjacencyList(n1).remove(e) &&
                          getModifiableAdjacencyList(n2).remove(e);
        if (removed) --edgeCount;
        return removed;
    }

    /**
     * Removes an edge identified by two nodes n1, n2 from the graph and returns
     * whether the edge was in the graph before the call. The edge to be removed
     * is such that it belongs to the graph and it contains two nodes n1', n2'
     * such that n1.equals(n1') and n2.equals(n2') or n1.equals(n2') and
     * n2.equals(n1')
     * @param n1 One of the nodes of the edge to remove
     * @param n2 The other node of the edge to remove
     * @return true if the edge belonged to the graph before the removal try
     * @throws NullPointerException if n1 is null or n2 is null
     * @throws IllegalArgumentException if one or both nodes of the edge
     * don't belong to the graph
     */
    public abstract boolean removeEdge(Node n1, Node n2);

}
