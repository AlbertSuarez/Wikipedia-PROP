package g13;

/**
 * Edge class representing undirected weighted graph edges.
 * Created mainly by Albert Segarra with some contributions by Miquel Jubert.
 */
public abstract class Edge {

    private static final String ERR_NOT_PART_EDGE =
            "The node is not part of the edge!";
    private static final String ERR_WEIGHT =
            "Weight must be a number > 0!" ;

    private final Node n1;
    private final Node n2;
    private double weight;
    private boolean valid; // For invalidating edges without deleting them

    /**
     * Creates a weighted edge between two nodes with weight w and validity v.
     * Note that <strong>the node weight must be a strictly positive
     * number</strong>.
     * @param m1 one of the nodes of the edge.
     * @param m2 the other node of the edge.
     * @param w the weight of the edge.
     * @param valid the validity of the edge.
     * @throws NullPointerException if m1 is null or m2 is null.
     * @throws IllegalArgumentException if w is not a
     * strictly positive number.
     */
    public Edge(Node m1, Node m2, double w, boolean valid) {
        if (m1 == null || m2 == null) throw new NullPointerException();

        setWeight(w);
        setValidity(valid);

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
     * Creates a weighted edge between two nodes with weight w and validity
     * true.
     * Note that <strong>the node weight must be a strictly positive
     * number</strong>.
     * @param m1 one of the nodes of the edge.
     * @param m2 the other node of the edge.
     * @param w the weight of the edge.
     * @throws NullPointerException if m1 is null or m2 is null.
     * @throws IllegalArgumentException if w is not a
     * strictly positive number.
     */
    public Edge(Node m1, Node m2, double w) {
        this(m1, m2, w, true);
    }

    /**
     * Creates a weighted edge between two nodes with weight 1 and validity
     * true.
     * Note that <strong>the node weight must be a strictly positive
     * number</strong>.
     * @param m1 one of the nodes of the edge.
     * @param m2 the other node of the edge.
     * @throws NullPointerException if m1 is null or m2 is null.
     * @throws IllegalArgumentException if w is not a
     * strictly positive number.
     */
    public Edge(Node m1, Node m2) {
        this(m1, m2, 1, true);
    }

    /**
     * Edge's weight setter. The supplied weight value
     * <strong>must be a strictly positive number</strong>.
     * @param w the desired weight value for the edge.
     * @throws IllegalArgumentException if the weight is not a strictly positive
     * number.
     */
    public void setWeight(double w) {
        if (Double.isNaN(w) || w <= 0)
            throw new IllegalArgumentException(ERR_WEIGHT);
        else weight = w;
    }

    /**
     * Sets the edge validity to equal the parameter valid.
     * @param valid the desired validity for the edge.
     */
    public void setValidity(boolean valid) {
        this.valid = valid;
    }

	/**
     * If n is a node that belongs to the edge (i.e the edge contains a node n'
     * such that n.equals(n')), this function returns its neighbor.
     * @param n the node whose neighbor is requested.
     * @return given one of the two nodes of the edge, it returns the other one.
     * @throws NullPointerException if n is null.
     * @throws IllegalArgumentException if n is not one of the nodes of the
     * edge.
	 */
    public Node getNeighbor(Node n) {
        if (n == null) throw new NullPointerException();

        if (n.equals(n1)) return n2;
        else if (n.equals(n2)) return n1;
        else throw new IllegalArgumentException(ERR_NOT_PART_EDGE);
    }

	/**
     * Returns the smallest node of the edge.
	 * @return the smallest node of the edge.
	 */
    public Node getNode() {
        return n1;
    }

    /**
     * @param o The object to compare with the edge.
     * @return returns true if the object o is an instance of edge,
     * it is not null and it contains the same nodes as the edge o (no matter
     * the order).
     */
    @Override public boolean equals(Object o) {
        // We don't check the weight as we don't expect to have two edges
        // with different weights joining the same pair of nodes in the graph
        if (this == o) return true;
        if (o == null || !(o instanceof Edge)) return false;

        final Edge e = (Edge) o;

        return e.n1.equals(n1) && e.n2.equals(n2);
    }

    /**
     * Returns a hash code value for this edge.
     * @return returns a hash code value for this edge.
     */
    @Override public int hashCode() {
        int hash = n1.hashCode();
        hash = 31*hash + n2.hashCode();

        return hash;
    }

    /**
     * Returns a String representation of the edge. Note that the nodes of the
     * edge are printed in increasing order, so for instance if nodes were
     * integers both edge 3,2 and edge 2,3 would be printed as 2,3.
     * @return a String representation of the edge.
     */
    @Override public String toString() {
        return "{" + n1 + ", " + n2 + ", " + weight + "}";
    }

    /**
     * Returns the edge's weight.
     * @return the edge's weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns whether the edge is valid. This method is useful for
     * some algorithms that need to remove all edges and then keep adding
     * them one by one.
     * @return true if the edge is valid.
     */
    public boolean isValid() {
        return valid;
    }
}
