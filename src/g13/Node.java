package g13;

/**
 * Created by miquel on 20/03/15.
 */
public interface Node {

    /**
     * Compares the specified object with this Node for equality.
     * Note that this should not be implemented as a reference {@code ==}
     * comparison, but as a data comparison. Other things to keep in mind are
     * that, because nodes are used as map keys, all the attributes used in the
     * comparison between nodes <strong>must not change</strong> during the
     * live of the object, so they should be declared final.
     * @param o Object to compare with this
     * @return true iff o is a Node, it's not null and is equal to this node
     */
    @Override boolean equals(Object o);

    /**
     * Returns a hash code value for this Node. It <strong>must</strong>
     * return equal hash codes for equal nodes as defined in the
     * {@link #equals(Object)} method
     * Note: Take a look at this
     * <a href=
     * "http://stackoverflow.com/questions/113511/hash-code-implementation">
     * link</a>
     * for guidelines on implementing this function
     *
     * @return a hash code value for the Node
     */
    @Override int hashCode();

    /**
     * Returns a string representation of this Node. It should
     * only print the key attributes of the node, not all its info.
     * @return a string representation of this Node
     */
    @Override String toString();

    /**
     * Returns whether this Node is greater than the parameter node.
     * This function is necessary for the Edge class to define edges
     * to be truly undirected (no sense of source and destination)
     * @param n The node to compare
     * @return true iff this node is greater than n
     */
    boolean isGreater(Node n);
}
