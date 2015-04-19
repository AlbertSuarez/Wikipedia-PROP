package g13;

/**
 * Node class representing Graph nodes.
 * Created by Albert Segarra.
 *
 * Notes:
 *  1: This class should only contain a node identifier (key). It should not
 *  contain data like an article's content or a parliament member's information,
 *  as it could not be retrieved. A translation must be done instead in another
 *  class (graph class maybe) from node identifiers to node data.
 */
public abstract class Node {

    /**
     * Compares the specified object with this Node for equality.
     * Note that this should not be implemented as a reference {@code ==}
     * comparison, but as a data comparison. Other things to keep in mind are
     * that, because nodes are used as map keys, all the attributes used in the
     * comparison between nodes <strong>must not change</strong> during the
     * live of the object, so they should be declared final.
     * @param o object to compare with this.
     * @return true if o is a instance of Node, it's not null and is equal
     * to this node.
     */
    @Override public abstract boolean equals(Object o);

    /**
     * Returns a hash code value for this Node. It <strong>must</strong>
     * return equal hash codes for equal nodes as defined in the
     * {@link #equals(Object)} method
     * Note: Take a look at this
     * <a href=
     * "http://stackoverflow.com/questions/113511/hash-code-implementation">
     * link</a>
     * for guidelines on implementing this function.
     *
     * @return a hash code value for the Node.
     */
    @Override public abstract int hashCode();

    /**
     * Returns a string representation of this Node.
     * @return a string representation of this Node.
     */
    @Override public abstract String toString();

    /**
     * Returns whether this Node is greater than the parameter node.
     * This function is necessary for the Edge class to define edges
     * to be truly undirected (no sense of source and destination).
     * @param n the node to compare.
     * @return true if this node is greater than n.
     */
    public abstract boolean isGreater(Node n);
}
