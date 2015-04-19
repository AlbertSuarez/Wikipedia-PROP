package g13;

import java.util.*;

import com.google.common.base.Predicate;
import com.google.common.collect.*;

/**
 * Abstract graph class representing undirected weighted graphs.
 * Created mainly by Albert Segarra with some contributions by
 * Miquel Jubert.
 *
 * Notes:
 *  1: A node n is said to belong/be/etc. to a graph/edge if there
 *  exists an edge n' such that n.equals(n') and n' belongs to the graph/edge
 *
 *  2: An edge instance e is said to be valid if a call to e.isValid() returns
 *  true
 *
 *  3: The <code>Collection</code>s returned by some of the class operations
 *  are meant to be used for iterating through its elements or getting to know
 *  their size. Using operations like <code>contains</code> is not recommended,
 *  as its cost is linear.
 *
 *
 */
public abstract class Graph {

    private static final String ERR_INEX_NODE =
            "The node doesn't belong to the graph";

    private final Set<Edge> E;
    private final Set<Node> V;
    private final Map<Node, Map<Node, Edge>> G;
    private final Predicate<Edge> validFilter;

    /**
     * Creates an empty Graph
	 */
    public Graph() {
        E = new LinkedHashSet<>(); // TODO: Choose your own Set implementation
        G = new LinkedHashMap<>(); // TODO: Choose your own Map implementation
        V = G.keySet();

        validFilter = new Predicate<Edge>() {
            public boolean apply(Edge e) {
                return e.isValid();
            }
        };
    }

    /**
     * Returns the order i.e number of nodes of the graph
     * @return the order of the graph.
     */
    public int getOrder() {
        return V.size();
    }

    /**
     * Returns the size i.e number of edges of the graph.
     * @return the number of edges of the graph.
     */
    public int getEdgeCount() {
        return E.size();
    }

    /**
     * Returns the number of valid edges of the graph.
     * @return the number of valid edges of the graph.
     */
    public int getValidEdgeCount() {
        return getValidEdges().size();
    }

    /**
     * Returns a <strong>non-modifiable</strong> collection with the nodes
     * that belong to the graph.
     * @return a non-modifiable collection of the nodes in the graph.
     */
    public Collection<Node> getNodes() {
        return Collections.unmodifiableCollection(V);
    }

    /**
     * Returns a <strong>non-modifiable</strong> collection with the edges
     * that belong to the graph.
     * @return a non-modifiable collection with the edges in the graph.
     */
    public Collection<Edge> getEdges() {
        return Collections.unmodifiableCollection(E);
    }

    /**
     * Returns a <strong>non-modifiable</strong> collection with the valid
     * edges that belong to the graph.
     * @return a non-modifiable collection with the valid edges in the graph.
     */
    public Collection<Edge> getValidEdges() {
        return Collections.unmodifiableCollection(
                Collections2.filter(E, validFilter));
    }

    /**
     * Returns a <strong>non-modifiable</strong> collection with the graph
     * edges having n as one of its nodes, i.e the adjacency list
     * of n.
     * @param n the node whose adjacency list is requested.
     * @return a non-modifiable collection with the adjacency list of
     * the node n' such that n' belongs to the graph and n.equals(n').
     * @throws IllegalArgumentException if the node n doesn't belong
     * to the graph.
     * @throws NullPointerException if the node n is null.
     */
    public Collection<Edge> getAdjacencyList(Node n) {
        return Collections.unmodifiableCollection(getAdjacencyMap(n).values());
    }

    /**
     * Returns a <strong>non-modifiable</strong> collection with the valid graph
     * edges having n as one of its nodes, i.e the valid adjacency list
     * of n
     * @param n the node whose adjacency list is requested
     * @return a non-modifiable collection with the valid adjacency list of
     * the node n' such that n' belongs to the graph and n.equals(n').
     * @throws IllegalArgumentException if the node n doesn't belong
     * to the graph.
     * @throws NullPointerException if the node n is null.
     */
    public Collection<Edge> getValidAdjacencyList(Node n) {
        return Collections2.filter(getAdjacencyList(n), validFilter);
    }

    private Map<Node, Edge> getAdjacencyMap(Node n) {
        if (n == null) throw new NullPointerException();

        final Map<Node, Edge> adjMap = G.get(n);

        if (adjMap == null) throw new IllegalArgumentException(ERR_INEX_NODE);

        return adjMap;
    }

	/**
     * Returns whether the node n belongs to the graph.
	 * @param n the node to test for belonging to the graph.
	 * @return true if there exists a node n' such that it belongs to the graph
     * and n.equals(n').
     * @throws NullPointerException if n is null
	 */
    public boolean hasNode(Node n) {
        if (n == null) throw new NullPointerException();

        return V.contains(n);
    }

    /**
     * Returns the degree i.e number of adjacent nodes to a given node of the
     * graph.
     * @param n the node from which to get the degree.
     * @return the degree of the node n' such that n' belongs to the graph
     * and n.equals(n').
     * @throws NullPointerException if n is null.
     * @throws IllegalArgumentException if the node n doesn't belong to the
     * graph.
     */
    public int getNodeDegree(Node n) {
        return getAdjacencyList(n).size();
    }

    /**
     * Returns the valid degree i.e number of nodes connected by a valid edge
     * to a given node of the graph.
     * @param n the node from which to get the degree.
     * @return the valid degree of the node n' such that n' belongs to the graph
     * and n.equals(n').
     * @throws NullPointerException if n is null.
     * @throws IllegalArgumentException if the node n doesn't belong to the
     * graph.
     */
    public int getValidNodeDegree(Node n) {
        return getValidAdjacencyList(n).size();
    }

    /**
     * Returns whether the graph contains a given edge <strong>formed by two
     * nodes belonging to the graph</strong>.
     * @param n1 The pair of nodes of the edge to test for belonging.
     * @return true if there exists an edge e in the graph such that
     * it contains the two given nodes (no matter the order in which they're
     * given).
     * @throws NullPointerException if n1 is null or n2 is null.
     * @throws IllegalArgumentException if either n1 or n2 don't belong to
     * the graph.
     */
    public boolean hasEdge(Node n1, Node n2) {
        if (!hasNode(n2)) throw new IllegalArgumentException(ERR_INEX_NODE);
        return getAdjacencyMap(n1).containsKey(n2);
    }

    /**
     * Returns whether the graph contains a given valid edge <strong>formed by
     * two nodes belonging to the graph</strong>.
     * @param n1 The pair of nodes of the valid edge to test for belonging.
     * @return true if there exists a valid edge e in the graph such that
     * it contains the two given nodes (no matter the order in which they're
     * given).
     * @throws NullPointerException if n1 is null or n2 is null.
     * @throws IllegalArgumentException if either n1 or n2 don't belong to
     * the graph.
     */
    public boolean hasValidEdge(Node n1, Node n2) {
        if (!hasNode(n2)) throw new IllegalArgumentException(ERR_INEX_NODE);
        final Edge e = getAdjacencyMap(n1).get(n2);
        return e != null && e.isValid();
    }

    /**
     * Returns an edge given by two nodes belonging to the graph. Note that
     * all modifications made to the returned edge will affect the edge
     * contained in the graph
     * @param n1 one of the nodes of the edge.
     * @param n2 the other node of the edge.
     * @return the edge which contains n1 and n2 as its nodes (no matter the
     * order in which they're given) or null if the edge doesn't exist.
     * @throws NullPointerException if n1 is null or n2 is null
     * @throws IllegalArgumentException if either n1 or n2 don't belong to the
     * graph
     */
    public Edge getEdge(Node n1, Node n2) {
        if (!hasNode(n2)) throw new IllegalArgumentException(ERR_INEX_NODE);
        return getAdjacencyMap(n1).get(n2);
    }

    /**
     * Returns a string representation of the graph. First the Graph
     * order and number of edges are printed,
     * followed by the list of nodes of the graph, followed by the list of
     * edges of the graph. The string ends with a new line.
     * @return a string representation of the graph.
     */
    @Override public String toString() {
        final String NEW_LINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();

        s.append("Order: ").append(getOrder()).append(NEW_LINE)
                .append("Number of edges: ").append(getEdgeCount())
                .append(NEW_LINE).append(NEW_LINE)
                .append("Nodes:").append(NEW_LINE);

        s.append("    ").append(getNodes()).append(NEW_LINE);

        s.append(NEW_LINE).append("Edges:").append(NEW_LINE);

        s.append("    ").append(getEdges()).append(NEW_LINE);

        return s.toString();
    }

    /**
     * Adds a node to the graph in case it didn't already belong to it.
     * The return value indicates whether it didn't already belong to the graph.
     * @param n the node to add to the graph.
     * @throws NullPointerException if n is null.
     * @return true if the node n didn't already belong to the graph.
     */
    public boolean addNode(Node n) {
        if (n == null) throw new NullPointerException();
        final boolean existed = V.contains(n);
        if (!existed) G.put(n, new LinkedHashMap<>());
        return !existed;
    }

    /**
     * Removes all the edges in the graph having n as one of its nodes.
     * @param n the node whose edges need to be removed.
     * @throws NullPointerException if n is null
     * @throws IllegalArgumentException if n doesn't belong to the graph
     */
    public void removeAllNodeEdges(Node n) {
        Map<Node, Edge> adjMap = getAdjacencyMap(n);
        for (Map.Entry<Node, Edge> pair : adjMap.entrySet()) {
            G.get(pair.getKey()).remove(n);
            E.remove(pair.getValue());
        }
        adjMap.clear();
    }

    /**
     * Removes a given node n' from the graph if it belonged to the graph
     * and returns whether it belonged (i.e whether the graph has been modified
     * by the call).
     * @param n the node to remove from the graph.
     * @return true if the node belonged to the graph before the removal try.
     */
    public boolean removeNode(Node n) {
        Map<Node, Edge> adjList = G.get(n);
        final boolean existed = adjList != null;
        if (existed) {
            V.remove(n);
            for (Map.Entry<Node, Edge> pair : adjList.entrySet()) {
                G.get(pair.getKey()).remove(n);
                E.remove(pair.getValue());
            }
        }
        return existed;
    }

    /**
     * Adds a given edge to the graph and returns whether it didn't already
     * belong to the graph before the call. Note that the graph will not be
     * modified if the edge already belongs to the graph, i.e there is an edge
     * e' such that e' belongs to the graph and e.equals(e')
     * @param e The edge to add to the graph
     * @return true if the edge didn't belong to the graph before the call
     * @throws NullPointerException if e is null
     * @throws IllegalArgumentException if one or both nodes of the edge
     * don't belong to the graph
     */
    public boolean addEdge(Edge e) {
        if (e == null) throw new NullPointerException();

        Node n1 = e.getNode();
        Node n2 = e.getNeighbor(n1);
        Map<Node, Edge> adjMap1 = getAdjacencyMap(n1);
        Map<Node, Edge> adjMap2 = getAdjacencyMap(n2);

        final boolean existed = !E.add(e);
        if (!existed) {
            adjMap1.put(n2, e);
            adjMap2.put(n1, e);
        }
        return !existed;
    }

    /**
     * Removes an edge identified by two nodes n1, n2 from the graph and returns
     * whether the edge was in the graph before the call. The edge to be removed
     * is such that it belongs to the graph and it contains the nodes n1 and n2
     * (no matter the order in which they're given).
     * @param n1 One of the nodes of the edge to remove
     * @param n2 The other node of the edge to remove
     * @return true if the edge belonged to the graph before the removal try.
     * @throws NullPointerException if n1 is null or n2 is null.
     * @throws IllegalArgumentException if one or both nodes of the edge
     * don't belong to the graph.
     */
    public boolean removeEdge(Node n1, Node n2) {
        Map<Node, Edge> adjMap1 = getAdjacencyMap(n1);
        Map<Node, Edge> adjMap2 = getAdjacencyMap(n2);

        final Edge e = adjMap1.remove(n2);
        final boolean existed = e != null;
        if (existed) {
            adjMap2.remove(n1);
            E.remove(e);
        }
        return existed;
    }

    /**
     * Invalidates all edges in the graph
     */
    public void invalidateAllEdges() {
        for (Edge e : E) e.setValidity(false);
    }

}
