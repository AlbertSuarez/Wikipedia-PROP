package g13;

/**
 * Eventhough it is named 'Edge' this class models undirected weighted edges.
 */
public abstract class Edge {

    private static final String ERR_NOT_PART_EDGE =
            "The node is not part of the edge!";
    private static final String ERR_WEIGHT =
            "Weight must be a number > 0!" ;

    // Both nodes are declared final. This is a good practice
    // when they're meant to be assigned only once, plus it is needed
    // to maintain coherence when using them as set keys
    private final Node n1;
    private final Node n2;
    private double weight;

    /**
     * Returns the Edge's weight
     * @return the Edge's weight
     */
	public double getWeight() {
		return weight;
	}

    /**
     * Edge's weight setter. The supplied weight value
     * <strong>must be a strictly positive number</strong>.
     * @param w The desired weight value for the edge.
     * @throws IllegalArgumentException if the weight is not a strictly positive
     * number
     */
	public void setWeight(double w) {
        if (Double.isNaN(w) || w <= 0)
            throw new IllegalArgumentException(ERR_WEIGHT);
        else weight = w;
	}

    /**
     * Creates a weighted edge between two nodes. Note that
     * <strong>the node weight must be a strictly positive number</strong>
     * @param m1 One of the two nodes of the edge
     * @param m2 The other node of the edge
     * @param w The weight of the edge
     * @throws NullPointerException if m1 is null or m2 is null
     * @throws IllegalArgumentException if w is not a
     * strictly positive number
     */
    public Edge(Node m1, Node m2, double w) {
        if (m1 == null || m2 == null) throw new NullPointerException();

        setWeight(w);

        if (m2.isGreater(m1)) {
            n1 = m1;
            n2 = m2;
        }
        else {
            n1 = m2;
            n2 = m1;
        }
    }

	/**
     * If n is a node that belongs to the edge (i.e the edge contains a node n'
     * such that n.equals(n')), this function returns its neighbor.
     * Otherwise it will throw an exception.
     * @param n The node whose neighbor is requested
     * @return Given one of the two nodes of the Edge, it returns the other one.
     *
     * @throws NullPointerException if n is null
     * @throws IllegalArgumentException if n is not one of the nodes of the edge
	 */
    public Node getNeighbor(Node n) {
        if (n == null) throw new NullPointerException();

        if (n.equals(n1)) return n2;
        else if (n.equals(n2)) return n1;
        else throw new IllegalArgumentException(ERR_NOT_PART_EDGE);
    }

	/**
     * Returns the smallest node of the edge
	 * @return the smallest node of the edge
	 */
    public Node getNode() {
        return n1;
    }

    /**
     * @param o The object to compare with the edge
     * @return Returns true if and only if the edge e has equal nodes as this
     * Edge (no matter the order)
     */
    @Override public boolean equals(Object o) {
        // We don't check the weight as we don't expect to have two edges
        // with different weights joining the same pair of nodes in the graph
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Edge e = (Edge) o;

        return e.n1.equals(n1) && e.n2.equals(n2);
    }

    /**
     * Returns a hash code value for this edge. Note that this method is needed
     * for using Edges as HashSet or HashMap keys properly
     * @return Returns a hash code value for this edge
     */
    @Override public int hashCode() {
        int hash = n1.hashCode();
        hash = 31*hash + n2.hashCode();

        return hash;
    }

    /**
     * Returns a String representation of the Edge. Note that the nodes of the
     * edge are printed in increasing order, so for example if nodes were
     * integers both Edge 3,2 and Edge 2,3 would be printed as 2,3
     * @return a String representation of the Edge
     */
    @Override public String toString() {
        return n1 + " " + n2 + " " + weight;
    }
}
