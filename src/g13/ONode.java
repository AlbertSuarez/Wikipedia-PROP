/**
 * @file ONode.java
 * @author G13.2
 * @date 2 May 2015
 * @brief Own Node implementation
 */

package g13;

import wikipedia.domain.Element;

public class ONode extends Node
{
	private Element e;
	
	/**
	 * @brief Creates a new ONode
	 * @param e element of the ONode
	 */
	public ONode(Element e)
	{
		this.e = e;
	}
	
	/**
	 * @brief Compares the specified object with this ONode for equality.
	 * @param o object for the comparison
	 * @return true if this node is equals than o, false alternatively.
	 */
	@Override public boolean equals(Object o)
	{
		return o instanceof ONode
				&&((ONode)o).e.equals(this.e);
	}
	
	/**
	 * @brief Returns a hash code value for this Node.
	 */
	@Override public int hashCode()
	{
		if (e == null) return 0;
		return e.getTitle().hashCode();
	}
	
	/**
	 * @brief Returns a string representation of this Node.
	 */
	@Override public String toString()
	{
		return e.toString();
	}
	
	/**
	 * @brief Returns whether this Node is greater than the parameter node.
	 * @param n the node to compare.
	 * @return true if this node is greater than n, false alternatively.
	 */
	public boolean isGreater(Node n) {
		return (this.toString()).compareTo(((ONode)n).toString()) > 0;
	}

	/**
	 * @brief Returns the element of implicit ONode.
	 */
	public Element getElement()
	{
		return e;
	}
}
