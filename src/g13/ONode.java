package g13;

import wikipedia.domain.Element;

/**
 * Own Node implementation
 * @author G13.2
 */
public class ONode extends Node
{
	/**
	 * The element of Node.
	 */
	private Element e;
	
	/**
	 * Creates a new ONode.
	 * @param e element of the ONode.
	 */
	public ONode(Element e)
	{
		this.e = e;
	}
	
	/**
	 * Compares the specified object with this ONode for equality.
	 * @param o object for the comparison.
	 * @return true if this node is equals than o, false alternatively.
	 */
	@Override public boolean equals(Object o)
	{
		return o instanceof ONode
				&&((ONode)o).e.equals(this.e);
	}
	
	/**
	 * Returns a hash code value for this Node.
	 * @return The hashCode of ONode.
	 */
	@Override public int hashCode()
	{
		if (e == null) return 0;
		return e.getTitle().hashCode();
	}
	
	/**
	 * Returns a string representation of this Node.
	 * @return The string representation of this Node.
	 */
	@Override public String toString()
	{
		return e.toString();
	}
	
	/**
	 * Returns whether this Node is greater than the parameter node.
	 * @param n the node to compare.
	 * @return true if this node is greater than n, false alternatively.
	 */
	public int compareTo(Node n) {
		return (this.toString()).compareTo(((ONode)n).toString());
	}

	/**
	 * Returns the element of implicit ONode.
	 * @return The element of implicit ONode.
	 */
	public Element getElement()
	{
		return e;
	}
}
